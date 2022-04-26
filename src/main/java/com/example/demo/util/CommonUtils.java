package com.example.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommonUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
    }

    public  static <T> byte[] serialize(T object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
        return mapper.readValue(bytes, clazz);
    }

    public static <T> String format(T object){
        try {
            return mapper.writeValueAsString(object);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static <T> T parse(String json, TypeReference<T> typeReference) throws IOException {
        return mapper.readValue(json, typeReference);
    }

    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    });

    public static String formatDate(Date date) {
        return dateFormatThreadLocal.get().format(date);
    }

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();
    public static void main(String[] args) {
   //     System.out.println(pathMatcher.match("test", "test"));
  //      System.out.println(pathMatcher.match("/test", "/test"));
    //    System.out.println(pathMatcher.match("https://example.org", "https://example.org"));
  //      System.out.println(pathMatcher.match("test", "/test"));
     //   System.out.println(pathMatcher.match("/test", "test"));
    //    System.out.println(pathMatcher.match("/wechatPubAccount/show/(\\d+)", "/wechatPubAccount/show/10"));
      //  System.out.println("/wechatPubAccount/show/100".matches("/wechatPubAccount/show/(\\d+)"));
  //      System.out.println("/wechatPubAccount/show".matches("/wechatPubAccount/show/(\\d+)"));
        System.out.println("/cpm/api/projects/scene/code/12345vv".matches("/cpm/api/projects/scene/code/(\\S+)"));
    }

}
