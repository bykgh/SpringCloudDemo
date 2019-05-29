package com.byk.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import java.util.*;

public abstract class JsonHelper {

    public static JsonNode toTree(String json) throws IOException {
        return MAPPER.readTree(json);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return new HashMap<String, Object>(0);
        }
        return MAPPER.readValue(json, Map.class);
    }

    public static <T> T toBean(String json, Class<T> clazz) throws IOException {
        if (json == null || "".equals(json)) return null;
        JsonParser parser = JSON_FACTORY.createParser(json);
        parser.setCodec(MAPPER);
        T t = parser.readValueAs(clazz);
        parser.close();
        return t;
    }

    public static <T> T parseJson(String json, TypeReference<T> typeOfT) throws IOException {
        if (json == null || "".equals(json)) return null;
        JsonParser parser = JSON_FACTORY.createParser(json);
        parser.setCodec(MAPPER);
        T t = parser.readValueAs(typeOfT);
        parser.close();
        return t;
    }

    public static <T> T parseJsonStr(String json, TypeReference<T> typeOfT) {
        T t = null;
        try {
            if (json == null || "".equals(json)) return null;
            JsonParser parser = JSON_FACTORY.createParser(json);
            parser.setCodec(MAPPER);
            t = parser.readValueAs(typeOfT);
            parser.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return t;
    }

    public static String toJson(Object object) throws IOException {
        return useMapper(object, MAPPER);
    }

    public static String toJsonStr(Object object) {
        try {
            return useMapper(object, MAPPER);
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }

    public static String toJsonWithoutNull(Object object) throws IOException {
        return useMapper(object, MAPPER2);
    }

    private static String useMapper(Object object, ObjectMapper mapper) throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = JSON_FACTORY.createGenerator(writer);
        generator.setCodec(mapper);
        generator.writeObject(object);
        generator.close();
        writer.close();
        return writer.toString();
    }
    /**
     * 把json内嵌的JSONObject放到map中
     * @param json
     * @param map
     */
	/*public static void jsonTOmap(JsonNode json,Map map){
		Iterator keys=
		while(keys.hasNext()){
			String  keyStr = keys.next().toString();
			Object  value  = json.get(keyStr);
			if("null".equals(value+"") || "".equals(value+"")){
				//map.put(keyStr, null);
				continue;
			}
			if (value instanceof net.sf.json.JSONObject){
				jsonTOmap((net.sf.json.JSONObject)value,map);
			}else{
				map.put(keyStr, value+"");
			}
		}
	}*/

    private static final JsonFactory JSON_FACTORY = new JsonFactory();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper MAPPER2 = new ObjectMapper();

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER2.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.setTimeZone(TimeZone.getDefault());
        MAPPER2.setTimeZone(TimeZone.getDefault());
    }

}