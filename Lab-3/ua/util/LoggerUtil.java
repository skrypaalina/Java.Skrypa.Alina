package ua.util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    public static void setupLogger() {
        Logger root = Logger.getLogger("");
        root.setLevel(Level.ALL);
        // ensure console handler exists
        boolean hasConsole = false;
        for (Handler h : root.getHandlers()) {
            if (h instanceof ConsoleHandler) { hasConsole = true; break; }
        }
        if (!hasConsole) {
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.ALL);
            root.addHandler(ch);
        }
        try {
            FileHandler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            root.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Не вдалося створити файл журналу: " + e.getMessage());
        }
    }
}
