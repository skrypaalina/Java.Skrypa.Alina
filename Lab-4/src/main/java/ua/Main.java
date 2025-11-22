package ua;

import ua.models.Movie;
import ua.models.User;
import ua.repository.GenericRepository;
import ua.util.FileReaderUtil;
import ua.util.LoggerUtil;

import java.util.List;

public class Main {
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

            System.out.println("\n=== Демонстрація GenericRepository ===");
            GenericRepository<Movie, String> movieRepo = new GenericRepository<>(Movie::title);
            for (Movie m : movies) movieRepo.add(m);
            movieRepo.add(movies.get(0)); // duplicate attempt
            System.out.println("Фільмів в репозиторії: " + movieRepo.size());
            movieRepo.findByIdentity("Inception").ifPresent(System.out::println);

            GenericRepository<User, String> userRepo = new GenericRepository<>(User::email);
            for (User u : users) userRepo.add(u);
            userRepo.add(users.get(0)); // duplicate attempt
            System.out.println("Користувачів в репозиторії: " + userRepo.size());
            userRepo.findByIdentity("alina@example.com").ifPresent(System.out::println);

        } catch (Exception e) {
            System.err.println("Помилка у головному методі: " + e.getMessage());
        }
    }
}
