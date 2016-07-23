package ua.rud.testingsystem.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
//    private static final String PROPERTIES_FILE = "ua/rud/testingsystem/resource/config";
//    private static HashMap<Locale, ResourceBundle> bundles = new HashMap<>();

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("ua/rud/testingsystem/resource/config");

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
//        ResourceBundle resourceBundle;
//        if (bundles.containsKey(locale)) {
//            resourceBundle = bundles.get(locale);
//        } else {
//            resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE, locale);
//            bundles.put(locale, resourceBundle);
//        }
        return resourceBundle.getString(key);
    }
}
