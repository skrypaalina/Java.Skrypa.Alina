package ua.serialization;
import org.junit.jupiter.api.*;
import ua.models.Student;
import ua.models.Group;
import java.nio.file.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
public class SerializerTest {
    private Serializer s = new Serializer();
    @Test void jsonRoundtripStudents() throws Exception {
        Path tmp = Paths.get("target/test-students.json").toAbsolutePath();
        List<Student> list = Arrays.asList(new Student(1,"A",20,100), new Student(2,"B",21,100));
        s.saveJson(list, tmp);
        List<Student> loaded = s.loadJson(tmp, Student[].class);
        assertEquals(list.size(), loaded.size());
        assertTrue(loaded.containsAll(list));
        Files.deleteIfExists(tmp);
    }
    @Test void yamlRoundtripGroups() throws Exception {
        Path tmp = Paths.get("target/test-groups.yaml").toAbsolutePath();
        List<Group> list = Arrays.asList(new Group(1,"G1",1), new Group(2,"G2",2));
        s.saveYaml(list, tmp);
        List<Group> loaded = s.loadYaml(tmp, Group[].class);
        assertEquals(list.size(), loaded.size());
        assertTrue(loaded.containsAll(list));
        Files.deleteIfExists(tmp);
    }
    @Test void missingFileThrows() {
        Path p = Paths.get("target/nonexistent.json");
        Exception ex = assertThrows(RuntimeException.class, () -> s.loadJson(p, Student[].class));
        assertTrue(ex.getMessage().contains("File not found"));
    }
}
