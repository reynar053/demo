package common.configmanager;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import common.logger.LoggerSingleton;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public abstract class ConfigManager {
    private static Config config;
    private static BrowserCaps browserCaps;
    private static final String PATH_TO_CONFIG = "src/test/resources/configdata.json";
    private static final String PATH_TO_TEST_DATA = "src/test/resources/testdata.json";

    private ConfigManager() {
    }

    public static Config getConfigData() {
        if (config == null) {
            config = (Config) serializeJSON("Config", Config.class);
        }
        return config;
    }

    public static BrowserCaps getBrowserCaps() {
        if (browserCaps == null) {
            browserCaps = (BrowserCaps) serializeJSON("Driver capabilities", BrowserCaps.class);
        }
        return browserCaps;
    }

    public static List<Map<String, String>> getTestUserData() {
        LoggerSingleton.info("Getting the test data");
        return testUserDataToMaps();
    }

    private static Object serializeJSON(String nameOfJsonElement, Class<?> classToSerialize) {
        Gson gson = new Gson();
        LoggerSingleton.info("Serializing {} data", nameOfJsonElement);
        return gson.fromJson(parseJSON().get(nameOfJsonElement), classToSerialize);
    }

    private static List<Map<String, String>> testUserDataToMaps() {
        try {
            LoggerSingleton.info("Reading test data");
            Type type = new TypeToken<ArrayList<HashMap<String, String>>>() {
            }.getType();
            JsonReader reader = new JsonReader(new FileReader(PATH_TO_TEST_DATA));
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            LoggerSingleton.warn("Path to test data {} does not contain config file", PATH_TO_TEST_DATA);
            throw new RuntimeException(e);
        }
    }

    private static JsonObject parseJSON() {
        try {
            Gson gson = new Gson();
            JsonReader json = new JsonReader(new FileReader(PATH_TO_CONFIG));
            JsonElement jsonElementConfig = gson.fromJson(json, JsonElement.class);
            json.close();
            return jsonElementConfig.getAsJsonObject();
        } catch (IOException e) {
            LoggerSingleton.warn("Path to config {} does not contain config file", PATH_TO_CONFIG);
            throw new RuntimeException(e);
        }
    }
}
