package ua.repository;
import java.util.*;
import ua.util.LoggerUtil;
public class GenericRepository<T extends Comparable<T>> {
    protected final Map<Integer, T> storage = new LinkedHashMap<>();
    private final java.util.function.Function<T,Integer> idExtractor;
    public GenericRepository(java.util.function.Function<T,Integer> idExtractor){
        this.idExtractor = idExtractor;
    }
    public void add(T entity){
        storage.put(idExtractor.apply(entity), entity);
        LoggerUtil.logInfo("Added entity: " + entity);
    }
    public Optional<T> get(int id){ return Optional.ofNullable(storage.get(id)); }
    public Collection<T> getAll(){ return new ArrayList<>(storage.values()); }
    public void remove(int id){ storage.remove(id); LoggerUtil.logInfo("Removed entity id="+id); }
    public int size(){ return storage.size(); }
    // sort by natural Comparable using identity (asc/desc)
    public List<T> sortByIdentity(String order){
        List<T> list = new ArrayList<>(storage.values());
        if("desc".equalsIgnoreCase(order)){
            list.sort(Collections.reverseOrder());
            LoggerUtil.logInfo("Sorted by identity DESC");
        } else {
            Collections.sort(list);
            LoggerUtil.logInfo("Sorted by identity ASC");
        }
        return list;
    }
    public List<T> sort(Comparator<T> comparator){
        List<T> list = new ArrayList<>(storage.values());
        list.sort(comparator);
        LoggerUtil.logInfo("Sorted by provided comparator");
        return list;
    }
}
