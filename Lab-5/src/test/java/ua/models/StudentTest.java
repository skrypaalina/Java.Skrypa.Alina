package ua.models;
import org.junit.jupiter.api.*;
import java.util.*;
public class StudentTest {
    @Test void comparableById(){
        Student a=new Student(1,"A",20,10);
        Student b=new Student(2,"B",21,10);
        Assertions.assertTrue(a.compareTo(b)<0);
    }
    @Test void comparatorByName(){
        List<Student> l=Arrays.asList(new Student(2,"Bob",20,10), new Student(1,"Alice",21,11));
        l.sort(Student.byName);
        Assertions.assertEquals("Alice", l.get(0).getName());
    }
}
