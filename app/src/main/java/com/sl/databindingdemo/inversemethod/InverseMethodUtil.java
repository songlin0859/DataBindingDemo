package com.sl.databindingdemo.inversemethod;

import androidx.databinding.InverseMethod;

public class InverseMethodUtil {
    @InverseMethod("convertIntToString")
    public static int convertStringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String convertIntToString(int value) {
        return String.valueOf(value);
    }
}
