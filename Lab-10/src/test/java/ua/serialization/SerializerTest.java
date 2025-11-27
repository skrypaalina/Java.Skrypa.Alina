package ua.serialization;
import org.junit.jupiter.api.*;
import ua.model.Student;
import java.nio.file.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
public class SerializerTest {
    private Serializer s = new Serializer();
    @Test void jsonRoundtrip() throws Exception {
        Path tmp = Paths.get("target/test-students.json").toAbsolutePath();
        List<Student> list = Arrays.asList(new Student(1,"A",20,100), new Student(2,"B",21,100));
        s.saveJson(list, tmp);
        List<Student> loaded = s.loadJson(tmp, Student[].class);
        assertEquals(list.size(), loaded.size());
        assertTrue(loaded.containsAll(list));
        Files.deleteIfExists(tmp);
    }
}
