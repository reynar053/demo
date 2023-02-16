package common.configmanager;

import java.time.Duration;

public class Config {
    /**
     * Explicit wait time in seconds.
     */
    long EXPLICIT_WAIT_TIME;
    /**
     * Implicit wait time in seconds.
     */
    long IMPLICIT_WAIT_TIME;
    String URL;
    int RANDOM_STRING_LENGTH;

    Config() {
    }

    public Duration getEXPLICIT_WAIT_TIME() {
        return Duration.ofSeconds(EXPLICIT_WAIT_TIME);
    }

    public Duration getIMPLICIT_WAIT_TIME() {
        return Duration.ofSeconds(IMPLICIT_WAIT_TIME);
    }

    public String getURL() {
        return URL;
    }

    public int getRANDOM_STRING_LENGTH() {
        return RANDOM_STRING_LENGTH;
    }
}
