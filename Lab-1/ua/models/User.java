package ua.models;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
    public static User create(String firstName, String lastName, String email) {
        return new User(firstName, lastName, email);
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("Ім'я не може бути порожнім!");
        this.firstName = firstName;
    }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Прізвище не може бути порожнім!");
        this.lastName = lastName;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Некоректний email!");
        this.email = email;
    }
    @Override
    public String toString() {
        return firstName + " " + lastName + " <" + email + ">";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
