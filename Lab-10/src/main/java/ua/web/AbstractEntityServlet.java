package ua.web;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
public abstract class AbstractEntityServlet<T> extends HttpServlet {
    protected final ObjectMapper mapper = new ObjectMapper();
    protected T readEntity(HttpServletRequest req, Class<T> cls) throws IOException {
        return mapper.readValue(req.getInputStream(), cls);
    }
    protected void writeJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), obj);
    }
}
