package ua.models;

import java.time.LocalDate;
import java.util.Objects;

public class Subscription {
    protected User user;
    protected LocalDate startDate;
    protected LocalDate endDate;
    private SubscriptionType type;

    public enum SubscriptionType { BASIC, STANDARD, PREMIUM }

    public Subscription(User user, LocalDate startDate, LocalDate endDate, SubscriptionType type) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }
    public static Subscription create(User user, LocalDate start, LocalDate end, SubscriptionType type) {
        return new Subscription(user, start, end, type);
    }
    public User getUser() { return user; }
    public SubscriptionType getType() { return type; }
    @Override
    public String toString() {
        return String.format("Підписка %s з %s до %s (%s)", user.getFirstName(), startDate, endDate, type);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(user, that.user) && type == that.type;
    }
    @Override
    public int hashCode() {
        return Objects.hash(user, type);
    }
}
