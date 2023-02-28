package com.a1qa.api.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class JsonParser {
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
}
