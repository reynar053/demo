package org.example.utils;

import org.example.logger.FrameworkLogger;

import java.security.SecureRandom;

public abstract class StringUtils {
    private static final String STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int len, String salt){
        FrameworkLogger.logInfo("Generating random string");
        StringBuilder sb = new StringBuilder(len);
        sb.append(salt);
        while (sb.length() < len)
            sb.append(STRING.charAt(RANDOM.nextInt(STRING.length())));
        FrameworkLogger.logInfo("Random string '{}' is generated", sb);
        return sb.toString();
    }

    public static String generateRandomString(int len){
        return generateRandomString(len, "");
    }
}

