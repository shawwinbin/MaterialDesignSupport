package com.wildsmith.material.utils;

public class StringUtils {

    public static boolean isEmpty(String string) {
        if (string == null || "".equals(string)) {
            return true;
        }

        return false;
    }
}