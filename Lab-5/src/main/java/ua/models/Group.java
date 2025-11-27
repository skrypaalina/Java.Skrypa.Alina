package ua.models;
public class Group implements Comparable<Group>{
    private int id;
    private String title;
    private int course;
    public Group(int id, String title, int course){
        this.id = id; this.title = title; this.course = course;
    }
    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public int getCourse(){ return course; }
    @Override public int compareTo(Group o){ return Integer.compare(this.id, o.id); }
    public static java.util.Comparator<Group> byTitle = java.util.Comparator.comparing(Group::getTitle);
    public static java.util.Comparator<Group> byCourse = java.util.Comparator.comparingInt(Group::getCourse);
    @Override public String toString(){ return "Group{id="+id+", title='"+title+"', course="+course+"}"; }
}
