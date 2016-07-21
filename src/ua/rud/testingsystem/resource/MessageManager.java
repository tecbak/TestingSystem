package ua.rud.testingsystem.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resource/messages");

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
