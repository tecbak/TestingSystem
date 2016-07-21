package ua.rud.testingsystem.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resource/config");

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
