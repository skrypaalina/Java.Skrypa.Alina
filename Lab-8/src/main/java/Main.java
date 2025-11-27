public class Main {
    public static void main(String[] args) {
        try { new Student("", -1); } catch (Exception e) { System.out.println(e.getMessage()); }
        Student s = new Student("Alex", 20);
        s.setAge(22);
        try { new Group(""); } catch (Exception e) { System.out.println(e.getMessage()); }
        Group g = new Group("KP-21");
        g.setTitle("IPZ-31");
    }
}