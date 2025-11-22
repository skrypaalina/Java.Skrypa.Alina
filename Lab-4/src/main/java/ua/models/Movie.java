package ua.models;

import java.time.LocalDate;

public record Movie(String title, Genre genre, LocalDate releaseDate, int durationMinutes) {
    public Movie {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Назва не може бути порожньою");
        if (durationMinutes <= 0) throw new IllegalArgumentException("Тривалість має бути більшою за 0");
    }

    public enum Genre {
        ACTION, DRAMA, COMEDY, HORROR, DOCUMENTARY, SCI_FI
    }

    @Override
    public String toString() {
        return "%s (%s, %d хв, %s)".formatted(title, genre, durationMinutes, releaseDate);
    }
}
