package utilities;

import common.logger.LoggerSingleton;

import java.util.Random;

public abstract class StringUtilities {
    private StringUtilities() {

    }

    public static String getLastWordInString(String str) {
        String[] result = str.split("\\s+");
        return result[result.length - 1];
    }

    public static String generateRandomString(int stringLength) {
        LoggerSingleton.info("Generating random string");
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String randomString = salt.toString();
        LoggerSingleton.info("Random string '{}' has generated", randomString);
        return randomString;
    }
}
