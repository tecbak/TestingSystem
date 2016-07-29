package ua.rud.testingsystem.resource;

import java.util.ResourceBundle;

/**
 * Class provides SQL queries
 */
public class SqlManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resource/sql");

    private SqlManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}