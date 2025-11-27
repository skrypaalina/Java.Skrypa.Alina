package ua.models;
import java.util.Objects;
public class Group {
    private int id;
    private String title;
    private int course;
    public Group() {}
    public Group(int id,String title,int course){ this.id=id; this.title=title; this.course=course; }
    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title=title; }
    public int getCourse(){ return course; }
    public void setCourse(int course){ this.course=course; }
    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Group g=(Group)o;
        return id==g.id && course==g.course && Objects.equals(title,g.title);
    }
    @Override public int hashCode(){ return Objects.hash(id,title,course); }
    @Override public String toString(){ return "Group{id="+id+",title='"+title+"',course="+course+"}"; }
}
