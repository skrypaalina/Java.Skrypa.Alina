package ua.models;

public record Review(Movie movie, User user, int rating, String comment) {
    public Review {
        if (rating < 1 || rating > 10) throw new IllegalArgumentException("Рейтинг має бути від 1 до 10");
        if (comment == null) comment = "";
    }

    public String summary() {
        return "%s поставив %d★ фільму '%s': %s".formatted(user.firstName(), rating, movie.title(), comment);
    }
}
