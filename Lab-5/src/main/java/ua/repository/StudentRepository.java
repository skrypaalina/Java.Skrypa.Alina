package ua.repository;
import ua.models.Student;
import java.util.*;
public class StudentRepository extends GenericRepository<Student>{
    public StudentRepository(){ super(Student::getId); }
    public List<Student> sortByName(){ return sort(Student.byName); }
    public List<Student> sortByAge(){ return sort(Student.byAge); }
    public List<Student> sortByGroup(){ return sort(Student.byGroup); }
}
