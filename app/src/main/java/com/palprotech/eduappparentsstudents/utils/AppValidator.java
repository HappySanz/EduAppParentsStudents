package com.palprotech.eduappparentsstudents.utils;

/**
 * Created by Admin on 12-04-2017.
 */

public class AppValidator {

    public static boolean checkNullString(String value) {
        if (value == null)
            return false;
        else
            return value.trim().length() > 0;
    }
}
