package ua;

import ua.models.Movie;
import ua.models.User;
import ua.util.FileReaderUtil;
import ua.util.LoggerUtil;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LoggerUtil.setupLogger();
        String moviesPath = "data/movies.csv";
        String usersPath = "data/users.csv";
        try {
            List<Movie> movies = FileReaderUtil.readMoviesFromFile(moviesPath);
            System.out.println("=== Успішно зчитані фільми ===");
            for (Movie m : movies) {
                System.out.println(m);
            }

            List<User> users = FileReaderUtil.readUsersFromFile(usersPath);
            System.out.println("\n=== Успішно зчитані користувачі ===");
            for (User u : users) {
                System.out.println(u);
            }

        } catch (Exception e) {
            logger.severe("Помилка у головному методі: " + e.getMessage());
        } finally {
            logger.info("Програма завершила роботу.");
        }

        runTests();
    }

    private static void runTests() {
        System.out.println("\n=== Тестування ===");
        // test movie creation valid
        try {
            Movie m = new Movie("Test", Movie.Genre.ACTION, java.time.LocalDate.now(), 120);
            assert m.durationMinutes() == 120;
            System.out.println("✔ Тест створення фільму успішний");
        } catch (Exception e) {
            System.out.println("✖ Тест створення фільму провалено: " + e.getMessage());
        }
        // test movie invalid
        try {
            new Movie("", Movie.Genre.COMEDY, java.time.LocalDate.now(), 90);
            System.out.println("✖ Тест некоректних даних (movie) провалено");
        } catch (Exception e) {
            System.out.println("✔ Тест обробки некоректних даних (movie) успішний: " + e.getMessage());
        }
        // test reading movies file contains expected valid count (should be 3 valid from sample)
        try {
            List<Movie> movies = FileReaderUtil.readMoviesFromFile("data/movies.csv");
            if (movies.size() >= 3) {
                System.out.println("✔ Тест читання файлу (movies) успішний, валідних записів: " + movies.size());
            } else {
                System.out.println("✖ Тест читання файлу (movies) провалено, валідних записів: " + movies.size());
            }
        } catch (Exception e) {
            System.out.println("✖ Тест читання файлу (movies) провалено з помилкою: " + e.getMessage());
        }
        // test reading users file
        try {
            List<User> users = FileReaderUtil.readUsersFromFile("data/users.csv");
            if (users.size() >= 1) {
                System.out.println("✔ Тест читання файлу (users) успішний, валідних записів: " + users.size());
            } else {
                System.out.println("✖ Тест читання файлу (users) провалено, валідних записів: " + users.size());
            }
        } catch (Exception e) {
            System.out.println("✖ Тест читання файлу (users) провалено з помилкою: " + e.getMessage());
        }
    }
}
