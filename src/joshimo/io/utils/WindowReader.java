package joshimo.io.utils;

import javax.swing.*;
import java.io.IOException;

import static joshimo.io.utils.NumbersValidator.*;

public class WindowReader {

    public static <T extends Number> T read(Class T) throws IOException {
        return read(T, "Input " + T.getSimpleName() + " value:");
    }

    public static <T extends Number> T read(Class T, String message) throws IOException {
        String value = JOptionPane.showInputDialog(null, message)
                .replace(",", ".")
                .replace(" ", "");
        value = value.isEmpty() || value.equals(".") || value.equals("+.") || value.equals("-.") ? "0.0" : value;
        if (T == Byte.class) {
            if (!isValidByte(value)) {
                showErrorMessage(Byte.class);
                return (T) read(Byte.class);
            }
            return (T) (Byte) Byte.parseByte(value);
        }
        if (T == Short.class) {
            if (!isValidShort(value)) {
                showErrorMessage(Short.class);
                return (T) read(Short.class);
            }
            return (T) (Short) Short.parseShort(value);
        }
        if (T == Integer.class) {
            if (!isValidInteger(value)) {
                showErrorMessage(Integer.class);
                return (T) read(Integer.class);
            }
            return (T) (Integer) Integer.parseInt(value);
        }
        if (T == Long.class) {
            if (!isValidLong(value)) {
                showErrorMessage(Long.class);
                return (T) read(Long.class);
            }
            return (T) (Long) Long.parseLong(value);
        }
        if (T == Float.class) {
            if (!isValidFloat(value)) {
                showErrorMessage(Float.class);
                return (T) read(Float.class);
            }
            return (T) (Float) Float.parseFloat(value);
        }
        if (T == Double.class) {
            if (!isValidDouble(value)) {
                showErrorMessage(Double.class);
                return (T) read(Double.class);
            }
            return (T) (Double) Double.parseDouble(value);
        }
        throw new IOException("Unsupported type!");
    }

    public static String read() throws IOException {
        return read("Input String Value");
    }

    public static String read(String message) throws IOException {
        return JOptionPane.showInputDialog(null, message);
    }

    private static void showErrorMessage(Class T) {
        JOptionPane.showMessageDialog(
                null,
                "Wrong " + T.getSimpleName() + " value!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

}