package ua.models;
import java.util.Objects;
public class Student {
    private int id;
    private String name;
    private int age;
    private int groupId;
    public Student() {}
    public Student(int id,String name,int age,int groupId){
        this.id=id; this.name=name; this.age=age; this.groupId=groupId;
    }
    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }
    public int getAge(){ return age; }
    public void setAge(int age){ this.age=age; }
    public int getGroupId(){ return groupId; }
    public void setGroupId(int groupId){ this.groupId=groupId; }
    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Student s=(Student)o;
        return id==s.id && age==s.age && groupId==s.groupId && Objects.equals(name,s.name);
    }
    @Override public int hashCode(){ return Objects.hash(id,name,age,groupId); }
    @Override public String toString(){ return "Student{id="+id+",name='"+name+"',age="+age+",groupId="+groupId+"}"; }
}
