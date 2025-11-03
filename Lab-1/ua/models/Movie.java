package ua.models;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {
    private String title;
    private Genre genre;
    private LocalDate releaseDate;
    private int durationMinutes;

    public enum Genre { ACTION, DRAMA, COMEDY, HORROR, DOCUMENTARY }

    public Movie(String title, Genre genre, LocalDate releaseDate, int durationMinutes) {
        setTitle(title);
        setGenre(genre);
        setReleaseDate(releaseDate);
        setDurationMinutes(durationMinutes);
    }
    public static Movie create(String title, Genre genre, LocalDate releaseDate, int durationMinutes) {
        return new Movie(title, genre, releaseDate, durationMinutes);
    }
    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Назва фільму не може бути порожньою!");
        this.title = title;
    }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes <= 0) throw new IllegalArgumentException("Тривалість повинна бути більшою за 0!");
        this.durationMinutes = durationMinutes;
    }
    @Override
    public String toString() {
        return String.format("%s (%s, %d хв)", title, genre, durationMinutes);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
