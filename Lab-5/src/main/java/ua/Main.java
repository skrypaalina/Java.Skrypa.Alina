package ua;
import ua.models.*;
import ua.repository.*;
import ua.util.LoggerUtil;
import java.util.*;
public class Main {
    public static void main(String[] args){
        StudentRepository students = new StudentRepository();
        students.add(new Student(2,"Alice",20,101));
        students.add(new Student(1,"Bob",22,101));
        students.add(new Student(3,"Charlie",19,102));
        LoggerUtil.logInfo("All students: " + students.getAll());
        LoggerUtil.logInfo("Students sorted by id asc: " + students.sortByIdentity("asc"));
        LoggerUtil.logInfo("Students sorted by name: " + students.sortByName());
        LoggerUtil.logInfo("Students sorted by age: " + students.sortByAge());

        GroupRepository groups = new GroupRepository();
        groups.add(new Group(10,"CS",1));
        groups.add(new Group(11,"Math",2));
        LoggerUtil.logInfo("Groups sorted by id desc: " + groups.sortByIdentity("desc"));
        LoggerUtil.logInfo("Groups sorted by title: " + groups.sortByTitle());

        // Movie demo
        GenericRepository<Movie> movies = new GenericRepository<>(Movie::getId);
        movies.add(new Movie(5,"Inception",2010));
        movies.add(new Movie(4,"Avatar",2009));
        LoggerUtil.logInfo("Movies by title: " + movies.sort(Movie.byTitle));
    }
}
