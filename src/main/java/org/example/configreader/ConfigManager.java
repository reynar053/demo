package org.example.configreader;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.common.JsonParser;
import org.example.logger.FrameworkLogger;

import java.io.File;

public abstract class ConfigManager {
    protected static final String PATH_TO_CONFIG = "src/test/resources/config.json";
    protected static final String PATH_TO_TEST_DATA = "src/test/resources/testdata.json";

    public static JsonNode getTestDataByPath(String jsonPtrExpr) {
        FrameworkLogger.logInfo("Getting value from test data by the '{}' path", jsonPtrExpr);
        return getJsonFromFile(PATH_TO_TEST_DATA, jsonPtrExpr);
    }

    public static JsonNode getConfigDataByPath(String jsonPtrExpr) {
        FrameworkLogger.logInfo("Getting value from config data by the '{}' path", jsonPtrExpr);
        return getJsonFromFile(PATH_TO_CONFIG, jsonPtrExpr);
    }

    private static JsonNode getJsonFromFile(String path, String jsonPtrExpr){
        File file = new File(path);
        return JsonParser.fileToJson(file).at(jsonPtrExpr);
    }
}
