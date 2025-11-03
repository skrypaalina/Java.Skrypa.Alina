package ua;

import ua.models.*;
import ua.models.Movie.Genre;
import ua.models.Subscription.SubscriptionType;
import ua.util.Utils;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User user = User.create("Alina", "Skrypa", "alina@example.com");
        Utils.printUser(user);
        try {
            User badUser = new User("", "NoEmail", "wrong_email");
        } catch (Exception e) {
            System.out.println("Помилка створення користувача: " + e.getMessage());
        }
        Movie movie = Movie.create("Inception", Genre.ACTION, LocalDate.of(2010, 7, 16), 148);
        Utils.printMovie(movie);
        Subscription sub = Subscription.create(user, LocalDate.now(), LocalDate.now().plusMonths(1), SubscriptionType.PREMIUM);
        System.out.println(sub);
        Playlist playlist = new Playlist(user);
        playlist.addMovie(movie);
        System.out.println(playlist);
        System.out.println("Перевірка email: " + Utils.validateUserEmail(user.getEmail()));
    }
}
