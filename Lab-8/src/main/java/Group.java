import java.util.logging.Logger;

public class Group {
    private String title;
    private static final Logger log = LoggerUtil.logger;

    public Group(String t) {
        validate(t);
        title = t;
        log.info("Created Group: " + t);
    }

    private void validate(String t) {
        if (t == null || t.isBlank()) {
            log.warning("Validation failed: title invalid");
            throw new InvalidDataException("title invalid");
        }
    }

    public void setTitle(String t) {
        validate(t);
        title = t;
    }

    public String toString() { return title; }
}