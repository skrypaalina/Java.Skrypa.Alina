package ua.repository;

@FunctionalInterface
public interface IdentityExtractor<T, ID> {
    ID getIdentity(T obj);
}
