package ua.concurrency;
import ua.serialization.Serializer;
import ua.models.Student;
import ua.models.Group;
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
    public CompletableFuture<Void> loadStudentsParallel(Path json, Path yaml, StudentRepository repo){
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
    public CompletableFuture<Void> loadGroupsParallel(Path json, Path yaml, GroupRepository repo){
        CompletableFuture<List<Group>> j = CompletableFuture.supplyAsync(() -> serializer.loadJson(json, Group[].class), executor);
        CompletableFuture<List<Group>> y = CompletableFuture.supplyAsync(() -> serializer.loadYaml(yaml, Group[].class), executor);
        return j.thenCombineAsync(y, (jl, yl) -> {
            List<Group> all = new ArrayList<>();
            if(jl!=null) all.addAll(jl);
            if(yl!=null) all.addAll(yl);
            repo.addAll(all);
            LoggerUtil.info("Loaded groups total: " + repo.size());
            return null;
        }, executor);
    }
}
