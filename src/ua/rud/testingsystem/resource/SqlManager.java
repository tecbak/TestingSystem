package ua.rud.testingsystem.resource;

import java.util.ResourceBundle;

public class SqlManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resource/sql.properties");

    private SqlManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
