package ua.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.models.User;

public class GenericRepositoryTest {

    @Test
    public void testAddAndFind() {
        GenericRepository<User, String> repo =
                new GenericRepository<>(User::email);

        User u = new User("Alina", "Skrypa", "alina@example.com");

        Assertions.assertTrue(repo.add(u));
        Assertions.assertEquals(1, repo.size());
        Assertions.assertTrue(repo.findByIdentity("alina@example.com").isPresent());
    }

    @Test
    public void testDuplicateNotAdded() {
        GenericRepository<User, String> repo =
                new GenericRepository<>(User::email);

        User u = new User("Alina", "Skrypa", "alina@example.com");

        repo.add(u);
        Assertions.assertFalse(repo.add(u));
        Assertions.assertEquals(1, repo.size());
    }

    @Test
    public void testRemove() {
        GenericRepository<User, String> repo =
                new GenericRepository<>(User::email);

        User u = new User("Alina", "Skrypa", "alina@example.com");

        repo.add(u);
        Assertions.assertTrue(repo.remove("alina@example.com"));
        Assertions.assertEquals(0, repo.size());
    }
}
