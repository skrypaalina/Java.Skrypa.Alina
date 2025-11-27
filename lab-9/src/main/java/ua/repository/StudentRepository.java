package ua.repository;
import ua.models.Student;
import java.util.*;
public class StudentRepository extends ConcurrentRepository<Student> {
    public StudentRepository(){ super(Student::getId); }
    public List<Student> findByAgeRange(int min,int max){
        List<Student> r = new ArrayList<>();
        for(Student s: storage.values()) if(s.getAge()>=min && s.getAge()<=max) r.add(s);
        return r;
    }
}
