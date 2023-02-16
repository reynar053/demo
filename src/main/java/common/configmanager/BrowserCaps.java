package common.configmanager;

import common.driver.DriverType;

public class BrowserCaps {
    String DRIVER_TO_USE;
    String[] CHROME_OPTIONS;
    String[] FIREFOX_OPTIONS;
    String[] EDGE_OPTIONS;
    String[] IE_OPTIONS;

    BrowserCaps() {
    }

    public DriverType getDRIVER_TO_USE() {
        return DriverType.valueOf(DRIVER_TO_USE);
    }

    public String[] getCHROME_OPTIONS() {
        return CHROME_OPTIONS;
    }

    public String[] getFIREFOX_OPTIONS() {
        return FIREFOX_OPTIONS;
    }

    public String[] getEDGE_OPTIONS() {
        return EDGE_OPTIONS;
    }

    public String[] getIE_OPTIONS() {
        return IE_OPTIONS;
    }
}
