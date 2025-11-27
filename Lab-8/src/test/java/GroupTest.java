import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

    @Test
    void validGroupCreated() {
        Group g = new Group("KP-21");
        assertNotNull(g);
    }

    @Test
    void invalidGroupThrows() {
        InvalidDataException e = assertThrows(InvalidDataException.class,
                () -> new Group(""));
        assertEquals("title invalid", e.getMessage());
    }

    @Test
    void setterValidationWorks() {
        Group g = new Group("KP-21");
        assertThrows(InvalidDataException.class, () -> g.setTitle(""));
    }
}