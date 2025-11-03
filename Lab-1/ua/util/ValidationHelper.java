package ua.util;

class ValidationHelper {
    static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");
    }
    static boolean isNonEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    static boolean isPositive(int number) {
        return number > 0;
    }
}
