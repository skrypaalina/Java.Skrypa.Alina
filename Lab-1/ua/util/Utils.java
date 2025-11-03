package ua.util;

import ua.models.*;

public class Utils {
    public static void printMovie(Movie movie) {
        System.out.println("🎬 " + movie);
    }
    public static void printUser(User user) {
        System.out.println("👤 " + user);
    }
    public static boolean validateUserEmail(String email) {
        return ValidationHelper.isValidEmail(email);
    }
}
