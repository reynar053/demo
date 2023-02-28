package com.a1qa.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public abstract class Configs {
    protected static ISettingsFile testData = new JsonSettingsFile("testdata.json");
    protected static ISettingsFile config = new JsonSettingsFile("configs.json");
    protected static ISettingsFile credentials = new JsonSettingsFile("credentials.json");

    public static String getValueFromData(String key) {
        if (config.isValuePresent(key)) return config.getValue(key).toString();
        if (testData.isValuePresent(key)) return testData.getValue(key).toString();
        if (credentials.isValuePresent(key)) return credentials.getValue(key).toString();
        throw new IllegalArgumentException("There is no such key in the configs");
    }
}
