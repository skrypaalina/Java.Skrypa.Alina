package ua.repository;

import ua.util.LoggerUtil;

import java.util.*;
import java.util.logging.Logger;

public class GenericRepository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();
    private final IdentityExtractor<T, ID> extractor;
    private static final Logger logger = Logger.getLogger(GenericRepository.class.getName());

    public GenericRepository(IdentityExtractor<T, ID> extractor) {
        LoggerUtil.setupLogger();
        this.extractor = extractor;
    }

    public boolean add(T obj) {
        ID id = extractor.getIdentity(obj);
        if (storage.containsKey(id)) {
            logger.warning("Спроба додати дубльований об’єкт з ID: " + id);
            return false;
        }
        storage.put(id, obj);
        logger.info("Об’єкт додано: " + id);
        return true;
    }

    public boolean remove(ID id) {
        if (storage.remove(id) != null) {
            logger.info("Об’єкт видалено: " + id);
            return true;
        }
        logger.warning("Об’єкт не знайдено для видалення: " + id);
        return false;
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<T> findByIdentity(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public int size() {
        return storage.size();
    }
}
