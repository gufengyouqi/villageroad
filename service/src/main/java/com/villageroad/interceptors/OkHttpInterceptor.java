package com.villageroad.interceptors;


import com.villageroad.exception.BizException;
import com.villageroad.model.BizError;
import com.villageroad.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
@Slf4j
public class OkHttpInterceptor implements HandlerInterceptor, Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        log.info("进入okhttp拦截器");
        //编码设为UTF-8
        Charset charset = StandardCharsets.UTF_8;
        try {
            HashMap<String, Object> esCmdLogMap = new HashMap<>();
            Request request = chain.request();
            HashMap<Object, Object> requestMap = this.getRequestBody(request, charset);
            Response response = chain.proceed(request);
            if (request.url().toString().contains("manage/command")) {
                log.info(request.url().toString());
                HashMap<Object, Object> responseMap = this.getResponseBody(response, charset);
                esCmdLogMap.put("requestUrl", request.url().toString());
                esCmdLogMap.put("request", requestMap);
                esCmdLogMap.put("deviceId", requestMap.get("deviceId"));
                esCmdLogMap.put("productCode", requestMap.get("productCode"));
                esCmdLogMap.put("response", responseMap);
                long startTime = System.currentTimeMillis();
                esCmdLogMap.put("cmdTime", startTime);
                log.info("esCmdLogMap:{}", esCmdLogMap);
            }
            return response;
        } catch (Exception e) {
            log.error("okhttp拦截器异常", e);
            throw e;
        }
    }

    private HashMap getResponseBody(Response response, Charset charset) throws IOException {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        MediaType mediaType = responseBody.contentType();
        if (isPlaintext(buffer)) {
            String result = buffer.clone().readString(mediaType.charset(charset));
            HashMap resultMap = JsonUtil.parseObject(result, HashMap.class);
            log.info("result:" + result);
            return resultMap;
        }
        return null;
    }

    private HashMap getRequestBody(Request request, Charset charset) {
        RequestBody requestBody = request.body();
        Buffer reqbuffer = new Buffer();
        try {
            requestBody.writeTo(reqbuffer);

            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(StandardCharsets.UTF_8);
            }
            //拿到request
            return JsonUtil.parseObject(reqbuffer.readString(charset), HashMap.class);
        } catch (IOException e) {
            log.error("okhttp拦截器异常", e);
            throw new BizException(BizError.ERROR_INTERNAL, "okhttp拦截器异常");
        }
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
