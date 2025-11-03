package ua.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private User user;
    private List<Movie> movies = new ArrayList<>();

    public Playlist(User user) {
        this.user = user;
    }
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }
    @Override
    public String toString() {
        return "Плейлист користувача " + user.getFirstName() + ": " + movies;
    }
}
