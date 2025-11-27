package ua.concurrency;
import ua.models.Student;
import ua.repository.StudentRepository;
import ua.util.LoggerUtil;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
public class ConcurrentProcessing {
    public static long countAgeParallelStream(Collection<Student> students, int threshold){
        long t0 = System.nanoTime();
        long c = students.parallelStream().filter(s->s.getAge()>=threshold).count();
        long t1 = System.nanoTime();
        LoggerUtil.info("parallelStream count="+c+" timeMs="+((t1-t0)/1_000_000));
        return c;
    }
    public static long countWithExecutor(Collection<Student> students, int threshold, ExecutorService ex) throws Exception {
        List<Student> list = new ArrayList<>(students);
        int cores = Math.max(1, Runtime.getRuntime().availableProcessors());
        int chunk = Math.max(1, list.size()/cores);
        List<Callable<Long>> tasks = new ArrayList<>();
        for(int i=0;i<list.size();i+=chunk){
            int from=i; int to=Math.min(list.size(), i+chunk);
            tasks.add(() -> {
                long local=0;
                for(int j=from;j<to;j++) if(list.get(j).getAge()>=threshold) local++;
                LoggerUtil.info("Task processed chunk size=" + (to-from));
                return local;
            });
        }
        long t0 = System.nanoTime();
        List<Future<Long>> fut = ex.invokeAll(tasks);
        long sum=0;
        for(Future<Long> f: fut) sum += f.get();
        long t1 = System.nanoTime();
        LoggerUtil.info("executor count="+sum+" timeMs="+((t1-t0)/1_000_000));
        return sum;
    }
    public static CompletableFuture<Long> countWithCompletable(Collection<Student> students, int threshold, ExecutorService ex){
        List<Student> list = new ArrayList<>(students);
        int parts = Math.max(1, Math.min(list.size(), Runtime.getRuntime().availableProcessors()));
        List<CompletableFuture<Long>> futures = new ArrayList<>();
        int chunk = Math.max(1, list.size()/parts);
        for(int i=0;i<list.size();i+=chunk){
            int from=i; int to=Math.min(list.size(), i+chunk);
            futures.add(CompletableFuture.supplyAsync(() -> {
                long local=0;
                for(int j=from;j<to;j++) if(list.get(j).getAge()>=threshold) local++;
                return local;
            }, ex));
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream().mapToLong(f -> f.join()).sum());
    }
}
