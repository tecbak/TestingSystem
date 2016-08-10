package ua.rud.testingsystem.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains common util methods
 */
public final class CommonUtils {
    private CommonUtils() {
    }

    /**
     * Convert string array to a list of ints
     *
     * @param stringArray string array to be converted
     * @return a {@link List} of ints
     * @throws NumberFormatException if any value of string array is not a number
     */
    public static List<Integer> stringArrayToIntList(String[] stringArray) {
        List<Integer> array = new ArrayList<>();

        for (String s : stringArray) {
            array.add(Integer.parseInt(s));
        }

        return array;
    }

    /**
     * Check if al items of string array is empty
     *
     * @param strings array of strings to be checked
     * @return {@code true} if strings array is {@code null},
     * strings array's length is 0 or all stings are empty
     * and {@code false} otherwise
     */
    public static boolean allEmpty(String[] strings) {
        if (strings == null || strings.length == 0) {
            return true;
        }

        for (String string : strings) {
            if (string != null && !string.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all strings aren't empty
     *
     * @param strings array of strings to check
     * @return {@code true} if there isn't any empty string, and {@code false} otherwise
     */
    public static boolean isFilled(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }

        for (String value : strings) {
            if (value == null || value.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
