package com.vehiclerental.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonUtils {

    private CommonUtils() {
        /*
            utility classes with static methods are not meant to be instantiated
         */
    }

    public static List<String> splitIntoList(String input, String delimiter) {
        List<String> res = new ArrayList<>();

        String[] rawArgs = input.trim().split(delimiter);

        for (String rawArg: rawArgs) {
            String trimmedArg = rawArg.trim();

            if (trimmedArg.length() > 0) {
                res.add(trimmedArg);
            }
        }

        return res;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidTime(int time) {
        return time >= 0 && time <= 24;
    }

    public static boolean areSetsEqual(Set<String> s1, Set<String> s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.size() != s2.size()) {
            return false;
        }

        return s1.containsAll(s2);
    }
}
