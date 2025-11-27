package ua.repository;
import java.util.*;
import java.util.function.Function;
public class GenericRepository<T> {
    protected final Map<Integer,T> storage = new LinkedHashMap<>();
    private final Function<T,Integer> idExtractor;
    public GenericRepository(Function<T,Integer> idExtractor){ this.idExtractor = idExtractor; }
    public void add(T e){ storage.put(idExtractor.apply(e), e); }
    public Optional<T> get(int id){ return Optional.ofNullable(storage.get(id)); }
    public Collection<T> getAll(){ return new ArrayList<>(storage.values()); }
    public void clear(){ storage.clear(); }
}
