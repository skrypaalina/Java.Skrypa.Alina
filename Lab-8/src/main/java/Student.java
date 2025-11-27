import java.util.logging.Logger;

public class Student {
    private String name;
    private int age;
    private static final Logger log = LoggerUtil.logger;

    public Student(String name, int age) {
        validate(name, age);
        this.name = name;
        this.age = age;
        log.info("Created Student: " + this);
    }

    private void validate(String n, int a) {
        StringBuilder sb = new StringBuilder();
        if (n == null || n.isBlank()) sb.append("name invalid; ");
        if (a <= 0) sb.append("age invalid");
        if (sb.length() > 0) {
            log.warning("Validation failed: " + sb);
            throw new InvalidDataException(sb.toString());
        }
    }

    public void setName(String n) {
        validate(n, age);
        name = n;
    }

    public void setAge(int a) {
        validate(name, a);
        age = a;
    }

    public String toString() { return name + " (" + age + ")"; }
}