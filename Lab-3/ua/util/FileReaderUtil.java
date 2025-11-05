package ua.util;

import ua.models.Movie;
import ua.models.Movie.Genre;
import ua.models.User;
import ua.exceptions.InvalidDataException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileReaderUtil {
    private static final Logger logger = Logger.getLogger(FileReaderUtil.class.getName());

    public static List<Movie> readMoviesFromFile(String filePath) throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNo = 0;
            while ((line = reader.readLine()) != null) {
                lineNo++;
                try {
                    String[] parts = line.split(",");
                    if (parts.length < 4) throw new InvalidDataException("Недостатньо даних у рядку " + lineNo + ": " + line);

                    String title = parts[0].trim();
                    String genreStr = parts[1].trim();
                    if (genreStr.isEmpty()) throw new InvalidDataException("Порожній жанр у рядку " + lineNo);
                    Genre genre = Genre.valueOf(genreStr);
                    LocalDate releaseDate = LocalDate.parse(parts[2].trim());
                    int duration = Integer.parseInt(parts[3].trim());
                    if (duration <= 0) throw new InvalidDataException("Невірна тривалість у рядку " + lineNo);

                    movies.add(new Movie(title, genre, releaseDate, duration));
                    logger.info("Успішно створено фільм: " + title);
                } catch (InvalidDataException | IllegalArgumentException e) {
                    logger.warning("Помилка у даних (рядок " + lineNo + "): " + e.getMessage());
                } catch (Exception e) {
                    logger.severe("Невідома помилка при читанні рядка " + lineNo + ": " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            logger.severe("Файл не знайдено: " + filePath);
            throw e;
        } catch (IOException e) {
            logger.severe("Помилка при читанні файлу: " + e.getMessage());
            throw e;
        } finally {
            logger.info("Читання файлу (movies) завершено.");
        }
        return movies;
    }

    public static List<User> readUsersFromFile(String filePath) throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNo = 0;
            while ((line = reader.readLine()) != null) {
                lineNo++;
                try {
                    String[] parts = line.split(",");
                    if (parts.length < 3) throw new InvalidDataException("Недостатньо даних у рядку " + lineNo + ": " + line);
                    String first = parts[0].trim();
                    String last = parts[1].trim();
                    String email = parts[2].trim();
                    if (email.isEmpty() || !email.contains("@")) throw new InvalidDataException("Некоректний email у рядку " + lineNo);
                    users.add(new User(first, last, email));
                    logger.info("Успішно створено користувача: " + email);
                } catch (InvalidDataException | IllegalArgumentException e) {
                    logger.warning("Помилка у даних (рядок " + lineNo + "): " + e.getMessage());
                } catch (Exception e) {
                    logger.severe("Невідома помилка при читанні рядка " + lineNo + ": " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            logger.severe("Файл не знайдено: " + filePath);
            throw e;
        } catch (IOException e) {
            logger.severe("Помилка при читанні файлу: " + e.getMessage());
            throw e;
        } finally {
            logger.info("Читання файлу (users) завершено.");
        }
        return users;
    }
}
