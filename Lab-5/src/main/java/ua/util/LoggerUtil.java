package ua.util;
import java.util.logging.*;
public class LoggerUtil {
    private static final Logger LOG = Logger.getLogger("Lab5Logger");
    static {
        LOG.setLevel(Level.INFO);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        LOG.addHandler(ch);
        LOG.setUseParentHandlers(false);
    }
    public static void logInfo(String msg){ LOG.info(msg); }
    public static void logWarning(String msg){ LOG.warning(msg); }
}
