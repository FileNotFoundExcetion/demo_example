package com.example.demo.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
@Slf4j
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();


    static {
        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // TimeZone 在 Bootstrap 中设置为 UTC 时区
        // Jackson 内部使用 clone 的方式，避免线程安全问题
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }


    public static <T> String objToStr(T obj) {
        if (null == obj) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : mapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse Object to String error: ", e);
            return null;
        }
    }

    public static <T> String objToPrettyStr(T obj) {
        if (null == obj) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse Object to Pretty String error: ", e);
            return null;
        }
    }

    public static <T> T strToObj(String value, Class<T> clazz) {
        if (value == null || value.isEmpty() || null == clazz) {
            return null;
        }

        try {
            return clazz == String.class ? (T) value : mapper.readValue(value, clazz);
        } catch (IOException e) {
            log.warn("Parse str to Object error: ", e);
            return null;
        }
    }

    public static <T> T strToObj(String value, TypeReference<T> type) {
        if (value == null || value.isEmpty() || null == type) {
            return null;
        }

        try {
            return type.getType() == String.class ? (T) value : mapper.readValue(value, type);
        } catch (IOException e) {
            log.warn("Parse str to Object error: ", e);
            return null;
        }
    }

    public static <T> T strToObj(String value, Class<?> collectionClazz, Class<?>... elementClazz) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClazz, elementClazz);

        try {
            return mapper.readValue(value, javaType);
        } catch (IOException e) {
            log.warn("Parse str to Object error: ", e);
            return null;
        }
    }
}
