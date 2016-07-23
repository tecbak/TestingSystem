package ua.rud.testingsystem.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static final String PROPERTIES_FILE = "ua/rud/testingsystem/resource/messages";
    private static HashMap<Locale, ResourceBundle> bundles = new HashMap<>();

//    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resource/messages");

    private MessageManager() {
    }

    public static String getProperty(String key, Locale locale) {
        ResourceBundle.clearCache();
        ResourceBundle resourceBundle;
        if (bundles.containsKey(locale)) {
            resourceBundle = bundles.get(locale);
        } else {
            resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE, locale);
            System.out.println(resourceBundle.getLocale());

            bundles.put(locale, resourceBundle);
        }
        return resourceBundle.getString(key);
    }
}
