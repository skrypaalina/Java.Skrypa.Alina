package ua.repository;
import org.junit.jupiter.api.*;
import ua.models.*;
import java.util.*;
public class GenericRepositoryTest {
    @Test void sortByIdentityAscDesc(){
        GenericRepository<Student> repo=new GenericRepository<>(Student::getId);
        repo.add(new Student(2,"B",20,10));
        repo.add(new Student(1,"A",21,10));
        List<Student> asc=repo.sortByIdentity("asc");
        List<Student> desc=repo.sortByIdentity("desc");
        Assertions.assertEquals(1, asc.get(0).getId());
        Assertions.assertEquals(2, desc.get(0).getId());
    }
}
