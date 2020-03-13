package com.villageroad.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.villageroad.exception.BizException;
import com.villageroad.model.BizError;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * @author houshengbin
 */
@Slf4j
@NoArgsConstructor
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper TRANSCODE_MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setDateFormat(CommonUtil.getDateFormat());

       TRANSCODE_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       TRANSCODE_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
       TRANSCODE_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
       TRANSCODE_MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
       TRANSCODE_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
       TRANSCODE_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       TRANSCODE_MAPPER.setDateFormat(CommonUtil.getDateFormat());
    }

    public static <T> List<T> parseList(String obj, TypeReference<Object> typeReference) {
        if (StringUtils.isBlank(obj)) {
            return Collections.emptyList();
        }

        try {
            return (List<T>) MAPPER.readValue(obj, typeReference);
        } catch (Exception e) {
            throw new BizException(BizError.ERROR_INTERNAL, "parse string to list failed", e);
        }
    }

    public static <T> T parseObject(final String json, final Class<T> type) throws IOException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return MAPPER.readValue(json, type);
    }

    public static <T> T parseObjectSafe(final String json, final Class<T> type) {
        try {
            return parseObject(json, type);
        } catch (Exception e) {
            log.warn("parse string to json exception! json:{}", json, e);
        }
        return null;
    }

    public static <T> T parseObject(final Object json, final Class<T> type) throws IOException {
        if (json == null) {
            return null;
        }
        if (json instanceof JsonNode) {
            return MAPPER.treeToValue((JsonNode) json, type);
        } else {
            JsonNode node = MAPPER.valueToTree(json);
            return MAPPER.treeToValue(node, type);
        }
    }

    public static <T> List<T> parseList(final Object json, String field, final Class<T> type) {
        if (json == null) {
            return new ArrayList<>();
        }

        JsonNode jsonNode;
        if (json instanceof JsonNode) {
            jsonNode = (JsonNode) json;
        } else {
            jsonNode = MAPPER.valueToTree(json);
        }

        JsonNode jsonArray = jsonNode.get(field);
        return StreamSupport.stream(jsonArray.spliterator(), false).map(node -> {
            try {
                return MAPPER.treeToValue(node, type);
            } catch (Exception e) {
                log.warn("parse json to list failed, json={}, field={}, class={}", json, field, type);
                throw new BizException(BizError.ERROR_INTERNAL, "parse json to list failed", e);
            }
        }).collect(Collectors.toList());
    }

    public static <T> T parseObject(final InputStream inputStream, final Class<T> type) throws IOException {
        return MAPPER.readValue(inputStream, type);
    }

    public static String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BizException(BizError.ERROR_INTERNAL, "convert object to json failed", e);
        }
    }

    public static JsonNode jsonStrToJsonNode(String jsonStr) throws IOException {
        try {
            return MAPPER.readTree(jsonStr);
        } catch (IOException e) {
            throw new BizException(BizError.ERROR_INTERNAL, "convert str to JsonNode failed", e);
        }
    }

    public static String writeValueAsStrOrDefault(Object object, String defaultStr) {
        try {
            String result = MAPPER.writeValueAsString(object);
            return null == result ? defaultStr : result;
        } catch (JsonProcessingException e) {
            log.warn("write object to str exception object:{} ", object, e);
            return defaultStr;
        }
    }

    public static boolean isJsonArray(Object object) {
        return object instanceof JSONArray;
    }

    public static boolean isJsonObject(Object object) {
        return object instanceof JSONObject;
    }

    public static boolean hasStringProperty(JSONObject object, String property) {
        return object.get(property) instanceof String;
    }

}
