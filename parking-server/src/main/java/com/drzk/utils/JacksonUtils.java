package com.drzk.utils;

import com.drzk.online.onlineVo.MqttHeadVo;
import com.drzk.online.onlineVo.MqttPayloadVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 2018/9/12 cx
 */
public class JacksonUtils {

    private static Logger log = LoggerFactory.getLogger( "userLog" );

    private static ObjectMapper objectMapper = null;

    static {
        objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion( JsonInclude.Include.ALWAYS );
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
        //如果是空对象的时候,不抛异常
        objectMapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
        objectMapper.setDateFormat( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) );

    }

    public static synchronized ObjectMapper newInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString( object );
        } catch (JsonProcessingException e) {
            log.error( "objectMapper object to String convert exception:{},{}", e.getMessage(), e );
            e.printStackTrace();
            return null;
        }

    }

    public static MqttPayloadVo jsonToMqttHead(String json) {
        try {
            MqttPayloadVo vo = new MqttPayloadVo();
            JsonNode obj = objectMapper.readTree( json );
            JsonNode head = obj.get( "head" );
            if (head != null) {
                MqttHeadVo headVo = objectMapper.readValue( head.toString(), MqttHeadVo.class );
                vo.setHead( headVo );
            }
            return vo;

        } catch (Exception e) {
            log.error( "objectMapper String to object convert exception:{},{}", e.getMessage(), e );
            e.printStackTrace();
            return null;
        }
    }

    public static <T> MqttPayloadVo<T> jsonToMqttObject(String jsonStr, Class<T> type) {
        try {
            MqttPayloadVo vo = new MqttPayloadVo();
            JsonNode obj = objectMapper.readTree( jsonStr );
            JsonNode head = obj.get( "head" );
            if (head != null) {
                MqttHeadVo headVo = objectMapper.readValue( head.toString(), MqttHeadVo.class );
                vo.setHead( headVo );
            }
            JsonNode body = obj.get( "body" );
            //必须是数组类型
            T bodyVo = null;
            if (body != null) {
                bodyVo = objectMapper.readValue( body.toString(), type );
            }
            vo.setBody( bodyVo );
            return vo;

        } catch (Exception e) {
            log.error( "objectMapper String to object convert exception:{},{}", e.getMessage(), e );
            e.printStackTrace();
            return null;
        }
    }

    public static <T> MqttPayloadVo<List<T>> jsonToListMqttObject(String jsonStr, Class<T> type) {
        try {
            MqttPayloadVo vo = new MqttPayloadVo();
            JsonNode obj = objectMapper.readTree( jsonStr );
            JsonNode head = obj.get( "head" );
            if (head != null) {
                MqttHeadVo headVo = objectMapper.readValue( head.toString(), MqttHeadVo.class );
                vo.setHead( headVo );
            }
            JsonNode body = obj.get( "body" );
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType( List.class, type );
            List<T> bodyVo = new ArrayList<>();
            //必须是数组类型
            if (body != null && body.getNodeType() == JsonNodeType.ARRAY) {
                bodyVo = objectMapper.readValue( body.toString(), javaType );
            }
            vo.setBody( bodyVo );
            return vo;
        } catch (Exception e) {
            log.error( "objectMapper String to object convert exception:{},{}", e.getMessage(), e );
            e.printStackTrace();
            return null;
        }

    }


    public static <T> T jsonToObject(String jsonStr, Class<T> type) {
        try {
            return objectMapper.readValue( jsonStr, type );
            //return objectMapper.readValue( jsonStr,type );
        } catch (IOException e) {
            log.error( "objectMapper String to object convert exception:{},{}", e.getMessage(), e );
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T jsonToList(String jsonStr, Class<T> type) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType( List.class, type );
            return objectMapper.readValue( jsonStr, javaType );
        } catch (IOException e) {
            log.error( "objectMapper String to object convert exception:{},{}", e.getMessage(), e );
            e.printStackTrace();
            return null;
        }

    }

}
