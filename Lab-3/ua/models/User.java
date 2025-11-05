package ua.models;

import java.util.Objects;

public record User(String firstName, String lastName, String email) {
    public User {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("Ім'я не може бути порожнім");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Прізвище не може бути порожнім");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Некоректний email");
    }

    @Override
    public String toString() {
        return "%s %s <%s>".formatted(firstName, lastName, email);
    }
}
