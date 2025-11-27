package ua.web;
import ua.model.Department;
import ua.repository.DepartmentRepository;
import ua.serialization.Serializer;
import ua.util.LoggerUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
public class DepartmentServlet extends AbstractEntityServlet<Department> {
    private DepartmentRepository repo;
    private Serializer serializer;
    private Path jsonPath;
    private Path yamlPath;
    @Override public void init() throws ServletException {
        repo = (DepartmentRepository) getServletContext().getAttribute("departmentsRepo");
        serializer = (Serializer) getServletContext().getAttribute("serializer");
        jsonPath = Path.of(getServletContext().getInitParameter("json.departments.path"));
        yamlPath = Path.of(getServletContext().getInitParameter("yaml.departments.path"));
    }
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null||path.equals("/")){
            Collection<Department> all = repo.getAll();
            writeJson(resp, all);
            LoggerUtil.info("GET departments: " + all.size());
            return;
        }
        try {
            int id = Integer.parseInt(path.substring(1));
            repo.get(id).ifPresentOrElse(d -> {
                try { writeJson(resp, d); } catch(IOException e) {}
            }, () -> {
                List<Department> loaded = serializer.loadJson(jsonPath, Department[].class);
                for(Department d: loaded) repo.add(d);
                repo.get(id).ifPresent(d -> { try { writeJson(resp, d); } catch(IOException e) {} });
            });
        } catch(NumberFormatException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(resp, Map.of("error","invalid id"));
        }
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department d = readEntity(req, Department.class);
        repo.add(d);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        writeJson(resp, d);
        LoggerUtil.info("POST department: " + d.getId());
    }
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null){ resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); return; }
        int id = Integer.parseInt(path.substring(1));
        Department incoming = readEntity(req, Department.class);
        repo.add(incoming);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        writeJson(resp, incoming);
        LoggerUtil.info("PUT department: " + id);
    }
    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path==null){ resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); return; }
        int id = Integer.parseInt(path.substring(1));
        repo.remove(id);
        serializer.saveJson(repo.getAll(), jsonPath);
        serializer.saveYaml(repo.getAll(), yamlPath);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        LoggerUtil.info("DELETE department: " + id);
    }
}
