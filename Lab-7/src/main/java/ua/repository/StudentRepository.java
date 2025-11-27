package ua.repository;
import ua.models.Student;
import ua.util.LoggerUtil;
import java.util.*;
import java.util.stream.Collectors;
public class StudentRepository extends GenericRepository<Student> {
    public StudentRepository(){ super(Student::getId); }
    public List<Student> findByName(String name){
        LoggerUtil.info("findByName: " + name);
        return storage.values().stream()
            .filter(s -> s.getName()!=null && s.getName().equals(name))
            .collect(Collectors.toList());
    }
    public List<Student> findByAgeRange(int min,int max){
        LoggerUtil.info("findByAgeRange: " + min + ".." + max);
        return storage.values().stream()
            .filter(s -> s.getAge()>=min && s.getAge()<=max)
            .collect(Collectors.toList());
    }
    public List<Student> findByGroupId(int gid){
        LoggerUtil.info("findByGroupId: " + gid);
        return storage.values().stream()
            .filter(s -> s.getGroupId()==gid)
            .collect(Collectors.toList());
    }
    public List<String> namesByAgeRange(int min,int max){
        return storage.values().stream()
            .filter(s->s.getAge()>=min && s.getAge()<=max)
            .map(Student::getName)
            .collect(Collectors.toList());
    }
    public int totalAge(){
        return storage.values().stream().mapToInt(Student::getAge).sum();
    }
}
