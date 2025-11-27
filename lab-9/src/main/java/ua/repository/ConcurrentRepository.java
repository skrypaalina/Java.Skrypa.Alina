package ua.repository;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
public class ConcurrentRepository<T> {
    protected final ConcurrentMap<Integer,T> storage = new ConcurrentHashMap<>();
    private final Function<T,Integer> idExtractor;
    public ConcurrentRepository(Function<T,Integer> idExtractor){ this.idExtractor = idExtractor; }
    public void add(T e){ storage.put(idExtractor.apply(e), e); }
    public void addAll(Collection<T> list){ for(T e:list) add(e); }
    public Optional<T> get(int id){ return Optional.ofNullable(storage.get(id)); }
    public Collection<T> getAll(){ return storage.values(); }
    public int size(){ return storage.size(); }
    public void clear(){ storage.clear(); }
}
