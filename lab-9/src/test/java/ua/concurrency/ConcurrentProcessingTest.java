package ua.concurrency;
import org.junit.jupiter.api.*;
import ua.models.Student;
import java.util.*;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;
public class ConcurrentProcessingTest {
    @Test
    void compareCounts() throws Exception {
        List<Student> list = new ArrayList<>();
        for(int i=1;i<=1000;i++) list.add(new Student(i,"S"+i,18+(i%15),100+(i%5)));
        ExecutorService ex = Executors.newFixedThreadPool(4);
        long p = ConcurrentProcessing.countAgeParallelStream(list, 25);
        long e = ConcurrentProcessing.countWithExecutor(list, 25, ex);
        long c = ConcurrentProcessing.countWithCompletable(list, 25, ex).join();
        assertEquals(p, e);
        assertEquals(p, c);
        ex.shutdown();
    }
}
