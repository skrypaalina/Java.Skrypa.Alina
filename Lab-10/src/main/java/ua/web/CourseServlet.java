package ua.web;
import ua.model.Course;
import ua.repository.CourseRepository;
import ua.serialization.Serializer;
import ua.util.LoggerUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
public class CourseServlet extends AbstractEntityServlet<Course> {
    private CourseRepository repo;
    private Serializer serializer;
    private Path jsonPath;
    private Path yamlPath;
    @Override public void init() throws ServletException {
        repo = (CourseRepository) getServletContext().getAttribute("coursesRepo");
        serializer = (Serializer) getServletContext().getAttribute("serializer");
        jsonPath = Path.of(getServletContext().getInitParameter("json.courses.path"));
        yamlPath = Path.of(getServletContext().getInitParameter("yaml.courses.path"));
    }
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null||path.equals("/")){
            Collection<Course> all = repo.getAll();
            writeJson(resp, all);
            LoggerUtil.info("GET courses: " + all.size());
            return;
        }
        try {
            int id = Integer.parseInt(path.substring(1));
            repo.get(id).ifPresentOrElse(c -> {
                try { writeJson(resp, c); } catch(IOException e) {}
            }, () -> {
                List<Course> loaded = serializer.loadJson(jsonPath, Course[].class);
                for(Course c: loaded) repo.add(c);
                repo.get(id).ifPresent(c -> { try { writeJson(resp, c); } catch(IOException e) {} });
            });
        } catch(NumberFormatException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(resp, Map.of("error","invalid id"));
        }
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course c = readEntity(req, Course.class);
        repo.add(c);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        writeJson(resp, c);
        LoggerUtil.info("POST course: " + c.getId());
    }
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null){ resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); return; }
        int id = Integer.parseInt(path.substring(1));
        Course incoming = readEntity(req, Course.class);
        repo.add(incoming);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        writeJson(resp, incoming);
        LoggerUtil.info("PUT course: " + id);
    }
    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null){ resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); return; }
        int id = Integer.parseInt(path.substring(1));
        repo.remove(id);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        LoggerUtil.info("DELETE course: " + id);
    }
}
