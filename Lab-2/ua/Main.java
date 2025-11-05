package ua;

import ua.models.*;
import ua.models.Movie.Genre;
import ua.models.SubscriptionType;
import ua.util.Utils;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie("Inception", Genre.ACTION, LocalDate.of(2010, 7, 16), 148);
        User user = new User("Alina", "Skrypa", "alina@example.com", SubscriptionType.PREMIUM);
        Review review = new Review(movie, user, 9, "Дуже цікаво та атмосферно!");

        System.out.println("=== Демонстрація Enum та Record ===");
        System.out.println("Фільм: " + movie);
        System.out.println("Користувач: " + user);
        System.out.println("Відгук: " + review.summary());

        System.out.println("\n=== Використання switch expression ===");
        Utils.printDiscountInfo(user.subscriptionType());
        double finalPrice = Utils.calculateFinalPrice(200.0, user.subscriptionType());
        System.out.printf("Ціна підписки з урахуванням знижки: %.2f грн%n", finalPrice);

        System.out.println("\n=== Switch-case приклад ===");
        switch (movie.genre()) {
            case ACTION -> System.out.println("Це бойовик — готуйтеся до екшену!");
            case DRAMA -> System.out.println("Це драма — приготуйте серветки!");
            case COMEDY -> System.out.println("Комедія — буде весело!");
            case HORROR -> System.out.println("Жахи — ховайтеся під ковдру!");
            case DOCUMENTARY -> System.out.println("Документалка — пізнавально!");
        }
    }
}
