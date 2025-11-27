import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void validStudentCreated() {
        Student s = new Student("Alex", 20);
        assertNotNull(s);
    }

    @Test
    void invalidStudentThrows() {
        InvalidDataException e = assertThrows(InvalidDataException.class,
                () -> new Student("", -1));
        assertTrue(e.getMessage().contains("name invalid"));
        assertTrue(e.getMessage().contains("age invalid"));
    }

    @Test
    void setterValidationWorks() {
        Student s = new Student("Alex", 20);
        assertThrows(InvalidDataException.class, () -> s.setName(""));
        assertThrows(InvalidDataException.class, () -> s.setAge(0));
    }
}