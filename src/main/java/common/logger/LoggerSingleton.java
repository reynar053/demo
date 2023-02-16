package common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoggerSingleton {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerSingleton.class);

    private LoggerSingleton() {
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static void info(String message, Object... messageVariables){
        LOGGER.info(message, messageVariables);
    }

    public static void warn(String message, Object... messageVariables){
        LOGGER.warn(message, messageVariables);
    }
}
