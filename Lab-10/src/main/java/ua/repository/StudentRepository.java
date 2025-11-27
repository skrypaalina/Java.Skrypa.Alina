package ua.repository;
import ua.model.Student;
public class StudentRepository extends ConcurrentRepository<Student> {
    public StudentRepository(){ super(Student::getId); }
}
