package com.villageroad.utils;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/**
 * Created by yuanbo3 on 17/5/24.
 */
@Slf4j
public class CommonUtil {

    private static final String VERSION_REG_EXP = "(?<=__)(\\d\\.){2}\\d(?=__)";// 客户端版本号提取正则表达式
    private static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_REG_EXP);
    public static final String UTF8 = "utf-8";

    private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static ThreadLocal<CRC32> crc32ThreadLocal = ThreadLocal.withInitial(CRC32::new);

    private CommonUtil() {
    }

    public static long crc32(byte[] b) {
        CRC32 c = crc32ThreadLocal.get();
        c.reset();
        c.update(b);
        return c.getValue();
    }

    public static byte[] long2bytes(long id) {
        byte[] b = new byte[8];
        b[0] = (byte) (id >> 56);
        b[1] = (byte) (id >> 48);
        b[2] = (byte) (id >> 40);
        b[3] = (byte) (id >> 32);
        b[4] = (byte) (id >> 24);
        b[5] = (byte) (id >> 16);
        b[6] = (byte) (id >> 8);
        b[7] = (byte) (id);
        return b;
    }

    public static Date strToDateLong(String strDate) {
        ParsePosition pos = new ParsePosition(0);
        return dateFormatThreadLocal.get().parse(strDate, pos);
    }

    /**
     * 转换unsigned整型到长整型
     * <p>
     * <pre>
     * 		比如 : number = -1 (int), 转换后为 4294967295
     * 			  number = 1 (int),  转换后为 1
     * </pre>
     *
     * @param number
     * @return
     */
    public static long unsignedIntToLong(int number) {
        return 4294967295L & (long) number;
    }


    public static DateFormat getDateFormat() {
        return dateFormatThreadLocal.get();
    }

    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("internal error");
        }
    }

    public static String urlEncodeSafe(String url) {
        try {
            return URLEncoder.encode(url, UTF8);
        } catch (UnsupportedEncodingException e) {
            log.warn("encoding message error. message={}", url);
            return "";
        }
    }

    public static String sha256(String data, String secret) {
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKey);
            byte[] array = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            log.warn("sha256 exception. data:{} secret:{}", data, secret, e);
        }
        return "";
    }


    /**
     * 判断两个 list 是否有交集
     *
     * @param list1
     * @param list2
     * @return true: 有交集, false: 无交集
     */
    public static boolean isListHasIntersection(List list1, List list2) {
        if (CollectionUtils.isEmpty(list1) || CollectionUtils.isEmpty(list2)) {
            return false;
        }
        Predicate inList2 = Predicates.in(list2);
        UnmodifiableIterator filter = Iterators.filter(list1.iterator(), inList2);
        return filter.hasNext();

    }
}
