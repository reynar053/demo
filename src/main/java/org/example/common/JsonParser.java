package org.example.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public abstract class JsonParser {

    public static <T> T jsonToObject(final JsonNode jsonNode, final Class<T> tClass){
        try {
            return new ObjectMapper().treeToValue(jsonNode, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T stringJsonToObject(final TypeReference<T> type, final String jsonPacket) {
        try {
            return new ObjectMapper().readValue(jsonPacket, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode stringToJson(final String jsonPacket) {
        try {
            return new ObjectMapper().readTree(jsonPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode fileToJson(final File file) {
        try {
            return new ObjectMapper().readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
