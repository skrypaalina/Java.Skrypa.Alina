package ua.models;
public class Student implements Comparable<Student>{
    private int id;
    private String name;
    private int age;
    private int groupId;
    public Student(int id, String name, int age, int groupId){
        this.id = id; this.name = name; this.age = age; this.groupId = groupId;
    }
    public int getId(){ return id; }
    public String getName(){ return name; }
    public int getAge(){ return age; }
    public int getGroupId(){ return groupId; }
    @Override public int compareTo(Student o){ return Integer.compare(this.id, o.id); }
    public static java.util.Comparator<Student> byName = java.util.Comparator.comparing(Student::getName);
    public static java.util.Comparator<Student> byAge = java.util.Comparator.comparingInt(Student::getAge);
    public static java.util.Comparator<Student> byGroup = java.util.Comparator.comparingInt(Student::getGroupId);
    @Override public String toString(){ return "Student{id="+id+", name='"+name+"', age="+age+", groupId="+groupId+"}"; }
}
