package ua.models;

public enum SubscriptionType {
    BASIC, STANDARD, PREMIUM;

    public double getDiscount() {
        return switch (this) {
            case BASIC -> 0.0;
            case STANDARD -> 0.1;
            case PREMIUM -> 0.2;
        };
    }
}
