package org.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false; // Handle null input
        }

        // More rigorous password validation (adjust as needed)
        return password.length() >= 8 && // Minimum length of 8
                password.length() <= 64 && // Maximum length of 64
                password.matches(".*[a-z].*") &&   // At least one lowercase letter
                password.matches(".*[A-Z].*") &&   // At least one uppercase letter
                password.matches(".*\\d.*") &&     // At least one digit
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",\\\\|,.<>\\/?].*"); // At least one special character
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false; // Handle null or empty input
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean isNameValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.matches("^[a-zA-Z\\s]+$"); // Only letters and spaces
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches("^\\+?\\d{10,15}$"); // Basic phone number validation (10 to 15 digits, optionally with a leading +)
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isAlphanumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("^[a-zA-Z0-9]*$");
    }
}