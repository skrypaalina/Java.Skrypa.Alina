package ua.model;
import java.util.Objects;
public class Student {
    private int id;
    private String name;
    private int age;
    private int departmentId;
    public Student() {}
    public Student(int id,String name,int age,int departmentId){
        this.id=id; this.name=name; this.age=age; this.departmentId=departmentId;
    }
    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }
    public int getAge(){ return age; }
    public void setAge(int age){ this.age=age; }
    public int getDepartmentId(){ return departmentId; }
    public void setDepartmentId(int departmentId){ this.departmentId=departmentId; }
    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Student s=(Student)o;
        return id==s.id && age==s.age && departmentId==s.departmentId && Objects.equals(name,s.name);
    }
    @Override public int hashCode(){ return Objects.hash(id,name,age,departmentId); }
    @Override public String toString(){ return "Student{id="+id+",name='"+name+"',age="+age+",departmentId="+departmentId+"}"; }
}
