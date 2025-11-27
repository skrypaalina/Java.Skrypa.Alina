package ua.util;
import java.util.logging.*;
public class LoggerUtil {
    private static final Logger LOG = Logger.getLogger("Lab10Final");
    static {
        LOG.setLevel(Level.INFO);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        LOG.addHandler(ch);
        LOG.setUseParentHandlers(false);
    }
    public static void info(String m){ LOG.info(m); }
    public static void warn(String m){ LOG.warning(m); }
}
