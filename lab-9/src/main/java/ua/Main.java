package ua;
import ua.serialization.Serializer;
import ua.repository.*;
import ua.concurrency.*;
import ua.util.LoggerUtil;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        cfg.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));
        Path jsonStudents = Paths.get(cfg.getProperty("json.students.path"));
        Path yamlStudents = Paths.get(cfg.getProperty("yaml.students.path"));
        Path jsonGroups = Paths.get(cfg.getProperty("json.groups.path"));
        Path yamlGroups = Paths.get(cfg.getProperty("yaml.groups.path"));
        int n = Integer.parseInt(cfg.getProperty("test.data.size","1000"));
        Serializer ser = new Serializer();
        StudentRepository sr = new StudentRepository();
        GroupRepository gr = new GroupRepository();
        ExecutorService ex = Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()));
        ConcurrentLoader loader = new ConcurrentLoader(ser, ex);
        ConcurrentLoader cleaner = loader;
        if(!Files.exists(jsonStudents) || !Files.exists(jsonGroups) || !Files.exists(yamlStudents) || !Files.exists(yamlGroups)){
            List<ua.models.Student> studs = new ArrayList<>();
            for(int i=1;i<=n;i++) studs.add(new ua.models.Student(i, "S"+i, 18 + (i%10), 100 + (i%5)));
            List<ua.models.Group> gs = new ArrayList<>();
            for(int i=1;i<=5;i++) gs.add(new ua.models.Group(i, "G"+i, i));
            ser.saveJson(studs, jsonStudents);
            ser.saveYaml(studs, yamlStudents);
            ser.saveJson(gs, jsonGroups);
            ser.saveYaml(gs, yamlGroups);
        }
        CompletableFuture<Void> f1 = loader.loadStudentsParallel(jsonStudents, yamlStudents, sr);
        CompletableFuture<Void> f2 = loader.loadGroupsParallel(jsonGroups, yamlGroups, gr);
        CompletableFuture.allOf(f1,f2).join();
        int threshold = 20;
        long ps = ConcurrentProcessing.countAgeParallelStream(sr.getAll(), threshold);
        long es = ConcurrentProcessing.countWithExecutor(sr.getAll(), threshold, ex);
        long cf = ConcurrentProcessing.countWithCompletable(sr.getAll(), threshold, ex).join();
        LoggerUtil.info("Results: parallelStream="+ps+" executor="+es+" completable="+cf);
        ex.shutdown();
    }
}
