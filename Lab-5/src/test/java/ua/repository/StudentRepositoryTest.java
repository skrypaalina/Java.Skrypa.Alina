package ua.repository;
import org.junit.jupiter.api.*;
import ua.models.*;
import java.util.*;
public class StudentRepositoryTest {
    @Test void sortByNameAgeGroup(){
        StudentRepository r=new StudentRepository();
        r.add(new Student(1,"Zed",23,200));
        r.add(new Student(2,"Anna",20,100));
        List<Student> byName=r.sortByName();
        List<Student> byAge=r.sortByAge();
        List<Student> byGroup=r.sortByGroup();
        Assertions.assertEquals("Anna", byName.get(0).getName());
        Assertions.assertEquals(20, byAge.get(0).getAge());
        Assertions.assertEquals(100, byGroup.get(0).getGroupId());
    }
}
