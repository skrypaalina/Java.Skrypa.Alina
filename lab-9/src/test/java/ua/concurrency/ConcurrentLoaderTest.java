package ua.concurrency;
import org.junit.jupiter.api.*;
import ua.serialization.Serializer;
import ua.repository.*;
import ua.models.Student;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;
public class ConcurrentLoaderTest {
    @Test
    void parallelLoadStudents() throws Exception {
        Path js = Paths.get("target/test-students.json").toAbsolutePath();
        Path ys = Paths.get("target/test-students.yaml").toAbsolutePath();
        Serializer ser = new Serializer();
        List<Student> list = new ArrayList<>();
        for(int i=1;i<=200;i++) list.add(new Student(i,"S"+i,18+(i%10),100+(i%3)));
        ser.saveJson(list, js);
        ser.saveYaml(list, ys);
        StudentRepository repo = new StudentRepository();
        ExecutorService ex = Executors.newFixedThreadPool(4);
        ConcurrentLoader loader = new ConcurrentLoader(ser, ex);
        loader.loadStudentsParallel(js, ys, repo).join();
        assertEquals(200*2, repo.size());
        Files.deleteIfExists(js);
        Files.deleteIfExists(ys);
        ex.shutdown();
    }
}
