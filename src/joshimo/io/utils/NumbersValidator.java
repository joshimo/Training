package joshimo.io.utils;

import java.util.regex.Pattern;

public class NumbersValidator {

    static boolean isValidByte(String value) {
        if (!Pattern.compile("([-+]?[1]?[0-9]{0,2})").matcher(value).matches())
            return false;
        Short val = Short.parseShort(value);
        return (val <= Byte.MAX_VALUE && val >= Byte.MIN_VALUE);
    }

    static boolean isValidShort(String value) {
        if (!Pattern.compile("([-+]?[1,3]?[0-9]{0,4})").matcher(value).matches())
            return false;
        Integer val = Integer.parseInt(value);
        return (val <= Short.MAX_VALUE && val >= Short.MIN_VALUE);
    }

    static boolean isValidInteger(String value) {
        if (!Pattern.compile("([-+]?[1,2]?[0-9]{0,9})").matcher(value).matches())
            return false;
        Long val = Long.parseLong(value);
        return (val <= Integer.MAX_VALUE && val >= Integer.MIN_VALUE);
    }

    static boolean isValidLong(String value) {
        if (!Pattern.compile("([-+]?[1,9]?[0-9]{0,18})").matcher(value).matches())
            return false;
        Double val = Double.parseDouble(value);
        return (val <= Long.MAX_VALUE && val >= Long.MIN_VALUE);
    }

    static boolean isValidFloat(String value) {
        if (!Pattern.compile("[+-]?([0-9]*[.]?[0-9]*([eE][+-])?[0-9]*)").matcher(value).matches())
            return false;
        Double val = Double.parseDouble(value);
        return (val <= Float.MAX_VALUE && val >= Float.MIN_VALUE);
    }

    static boolean isValidDouble(String value) {
        return Pattern.compile("[+-]?([0-9]*[.]?[0-9]*([eE][+-])?[0-9]*)").matcher(value).matches();
    }
}
