package ua.util;

import ua.models.SubscriptionType;

public class Utils {
    public static double calculateFinalPrice(double basePrice, SubscriptionType type) {
        double discount = type.getDiscount();
        return basePrice * (1 - discount);
    }
    public static void printDiscountInfo(SubscriptionType type) {
        String message = switch (type) {
            case BASIC -> "Базовий тариф — без знижки.";
            case STANDARD -> "Стандарт — знижка 10%.";
            case PREMIUM -> "Преміум — знижка 20%.";
        };
        System.out.println(message);
    }
}
