package org.example.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FrameworkLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("L");

    public static void logInfo(String message, Object... args){
        LOGGER.info(message, args);
    }
}