package ua.rud.testingsystem.managers;

import java.util.ResourceBundle;

/**
 * Class provides paths of JSPs
 */
public class PageManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resources/config");

    private PageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
