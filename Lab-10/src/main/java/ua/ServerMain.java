package ua;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.serialization.Serializer;
import ua.repository.*;
import ua.concurrency.ConcurrentLoader;
import ua.util.LoggerUtil;
import ua.web.*;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.Properties;
public class ServerMain {
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        cfg.load(ServerMain.class.getClassLoader().getResourceAsStream("config.properties"));
        int port = Integer.parseInt(cfg.getProperty("server.port","8080"));
        Serializer ser = new Serializer();
        StudentRepository students = new StudentRepository();
        DepartmentRepository departments = new DepartmentRepository();
        CourseRepository courses = new CourseRepository();
        var ex = Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()));
        ConcurrentLoader loader = new ConcurrentLoader(ser, ex);
        loader.loadStudents(Paths.get(cfg.getProperty("json.students.path")), Paths.get(cfg.getProperty("yaml.students.path")), students).join();
        loader.loadDepartments(Paths.get(cfg.getProperty("json.departments.path")), Paths.get(cfg.getProperty("yaml.departments.path")), departments).join();
        loader.loadCourses(Paths.get(cfg.getProperty("json.courses.path")), Paths.get(cfg.getProperty("yaml.courses.path")), courses).join();
        Server server = new Server(port);
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ctx.setContextPath("/");
        ctx.setAttribute("studentsRepo", students);
        ctx.setAttribute("departmentsRepo", departments);
        ctx.setAttribute("coursesRepo", courses);
        ctx.setAttribute("serializer", ser);
        ctx.setInitParameter("json.students.path", cfg.getProperty("json.students.path"));
        ctx.setInitParameter("yaml.students.path", cfg.getProperty("yaml.students.path"));
        ctx.setInitParameter("json.departments.path", cfg.getProperty("json.departments.path"));
        ctx.setInitParameter("yaml.departments.path", cfg.getProperty("yaml.departments.path"));
        ctx.setInitParameter("json.courses.path", cfg.getProperty("json.courses.path"));
        ctx.setInitParameter("yaml.courses.path", cfg.getProperty("yaml.courses.path"));
        ctx.addServlet(new ServletHolder(new StudentServlet()), "/api/students/*");
        ctx.addServlet(new ServletHolder(new DepartmentServlet()), "/api/departments/*");
        ctx.addServlet(new ServletHolder(new CourseServlet()), "/api/courses/*");
        server.setHandler(ctx);
        LoggerUtil.info("Starting server on port " + port);
        server.start();
        server.join();
    }
}
