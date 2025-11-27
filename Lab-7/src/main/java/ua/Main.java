package ua;
import ua.models.*;
import ua.repository.*;
import ua.serialization.Serializer;
import ua.util.LoggerUtil;
import java.nio.file.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        try {
            Properties cfg = new Properties();
            cfg.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));
            Path jsonStudents = Paths.get(cfg.getProperty("json.students.path"));
            Path yamlStudents = Paths.get(cfg.getProperty("yaml.students.path"));
            Path jsonGroups = Paths.get(cfg.getProperty("json.groups.path"));
            Path yamlGroups = Paths.get(cfg.getProperty("yaml.groups.path"));
            int n = Integer.parseInt(cfg.getProperty("test.data.size", "10"));
            StudentRepository sr = new StudentRepository();
            GroupRepository gr = new GroupRepository();
            for(int i=1;i<=n;i++){
                sr.add(new Student(i, "Student"+i, 18 + (i%10), 100 + (i%3)));
            }
            for(int i=1;i<=3;i++){
                gr.add(new Group(i, "Group"+i, i));
            }
            Serializer ser = new Serializer();
            ser.saveJson(sr.getAll(), jsonStudents);
            ser.saveYaml(sr.getAll(), yamlStudents);
            ser.saveJson(gr.getAll(), jsonGroups);
            ser.saveYaml(gr.getAll(), yamlGroups);
            StudentRepository sr2 = new StudentRepository();
            GroupRepository gr2 = new GroupRepository();
            List<Student> loadedStudents = ser.loadJson(jsonStudents, Student[].class);
            for(Student s: loadedStudents) sr2.add(s);
            List<Group> loadedGroups = ser.loadYaml(yamlGroups, Group[].class);
            for(Group g: loadedGroups) gr2.add(g);
            boolean studentsEqual = sr.getAll().size() == sr2.getAll().size() && sr.getAll().containsAll(sr2.getAll());
            boolean groupsEqual = gr.getAll().size() == gr2.getAll().size() && gr.getAll().containsAll(gr2.getAll());
            LoggerUtil.info("Students equal after roundtrip: " + studentsEqual);
            LoggerUtil.info("Groups equal after roundtrip: " + groupsEqual);
        } catch(Exception e){
            LoggerUtil.warn(e.getMessage());
            e.printStackTrace();
        }
    }
}
