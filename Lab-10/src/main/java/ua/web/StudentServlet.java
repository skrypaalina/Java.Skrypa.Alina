package ua.web;
import ua.model.Student;
import ua.repository.StudentRepository;
import ua.serialization.Serializer;
import ua.util.LoggerUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
public class StudentServlet extends AbstractEntityServlet<Student> {
    private StudentRepository repo;
    private Serializer serializer;
    private Path jsonPath;
    private Path yamlPath;
    @Override public void init() throws ServletException {
        repo = (StudentRepository) getServletContext().getAttribute("studentsRepo");
        serializer = (Serializer) getServletContext().getAttribute("serializer");
        jsonPath = Path.of(getServletContext().getInitParameter("json.students.path"));
        yamlPath = Path.of(getServletContext().getInitParameter("yaml.students.path"));
    }
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null||path.equals("/")){
            Collection<Student> all = repo.getAll();
            writeJson(resp, all);
            LoggerUtil.info("GET students: " + all.size());
            return;
        }
        try {
            int id = Integer.parseInt(path.substring(1));
            repo.get(id).ifPresentOrElse(s -> {
                try { writeJson(resp, s); } catch(IOException e) {}
            }, () -> {
                List<Student> loaded = serializer.loadJson(jsonPath, Student[].class);
                for(Student s: loaded) repo.add(s);
                repo.get(id).ifPresent(s -> { try { writeJson(resp, s); } catch(IOException e) {} });
            });
        } catch(NumberFormatException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(resp, Map.of("error","invalid id"));
        }
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student s = readEntity(req, Student.class);
        repo.add(s);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        writeJson(resp, s);
        LoggerUtil.info("POST student: " + s.getId());
    }
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null){ resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); return; }
        int id = Integer.parseInt(path.substring(1));
        Student incoming = readEntity(req, Student.class);
        repo.add(incoming);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        writeJson(resp, incoming);
        LoggerUtil.info("PUT student: " + id);
    }
    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null){ resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); return; }
        int id = Integer.parseInt(path.substring(1));
        repo.remove(id);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        LoggerUtil.info("DELETE student: " + id);
    }
}
