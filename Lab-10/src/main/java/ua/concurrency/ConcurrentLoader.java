package ua.concurrency;
import ua.serialization.Serializer;
import ua.model.Student;
import ua.model.Department;
import ua.model.Course;
import ua.repository.*;
import ua.util.LoggerUtil;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
public class ConcurrentLoader {
    private final Serializer serializer;
    private final ExecutorService executor;
    public ConcurrentLoader(Serializer serializer, ExecutorService executor){
        this.serializer = serializer;
        this.executor = executor;
    }
    public CompletableFuture<Void> loadStudents(Path json, Path yaml, StudentRepository repo){
        CompletableFuture<List<Student>> j = CompletableFuture.supplyAsync(() -> serializer.loadJson(json, Student[].class), executor);
        CompletableFuture<List<Student>> y = CompletableFuture.supplyAsync(() -> serializer.loadYaml(yaml, Student[].class), executor);
        return j.thenCombineAsync(y, (jl, yl) -> {
            List<Student> all = new ArrayList<>();
            if(jl!=null) all.addAll(jl);
            if(yl!=null) all.addAll(yl);
            repo.addAll(all);
            LoggerUtil.info("Loaded students total: " + repo.size());
            return null;
        }, executor);
    }
    public CompletableFuture<Void> loadDepartments(Path json, Path yaml, DepartmentRepository repo){
        CompletableFuture<List<Department>> j = CompletableFuture.supplyAsync(() -> serializer.loadJson(json, Department[].class), executor);
        CompletableFuture<List<Department>> y = CompletableFuture.supplyAsync(() -> serializer.loadYaml(yaml, Department[].class), executor);
        return j.thenCombineAsync(y, (jl, yl) -> {
            List<Department> all = new ArrayList<>();
            if(jl!=null) all.addAll(jl);
            if(yl!=null) all.addAll(yl);
            repo.addAll(all);
            LoggerUtil.info("Loaded departments total: " + repo.size());
            return null;
        }, executor);
    }
    public CompletableFuture<Void> loadCourses(Path json, Path yaml, CourseRepository repo){
        CompletableFuture<List<Course>> j = CompletableFuture.supplyAsync(() -> serializer.loadJson(json, Course[].class), executor);
        CompletableFuture<List<Course>> y = CompletableFuture.supplyAsync(() -> serializer.loadYaml(yaml, Course[].class), executor);
        return j.thenCombineAsync(y, (jl, yl) -> {
            List<Course> all = new ArrayList<>();
            if(jl!=null) all.addAll(jl);
            if(yl!=null) all.addAll(yl);
            repo.addAll(all);
            LoggerUtil.info("Loaded courses total: " + repo.size());
            return null;
        }, executor);
    }
}
