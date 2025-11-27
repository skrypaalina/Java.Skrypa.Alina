package ua.web;
import org.junit.jupiter.api.*;
import java.net.http.*;
import java.net.*;
import static org.junit.jupiter.api.Assertions.*;
import ua.ServerMain;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.*;
public class ServletIntegrationTest {
    private static ExecutorService svc;
    @BeforeAll static void startServer() throws Exception {
        svc = Executors.newSingleThreadExecutor();
        svc.submit(() -> {
            try { ServerMain.main(new String[0]); } catch(Exception e) {}
        });
        Thread.sleep(2000);
    }
    @AfterAll static void stopServer() throws Exception {
        svc.shutdownNow();
    }
    @Test void testStudentCrud() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String base = "http://localhost:8080/api/students";
        String json = "{"id":999,"name":"Test","age":30,"departmentId":1}";
        HttpRequest post = HttpRequest.newBuilder().uri(URI.create(base)).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse<String> r1 = client.send(post, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, r1.statusCode());
        HttpRequest getAll = HttpRequest.newBuilder().uri(URI.create(base)).GET().build();
        HttpResponse<String> r2 = client.send(getAll, HttpResponse.BodyHandlers.ofString());
        assertTrue(r2.body().contains(""Test""));
        HttpRequest del = HttpRequest.newBuilder().uri(URI.create(base + "/999")).DELETE().build();
        HttpResponse<String> r3 = client.send(del, HttpResponse.BodyHandlers.ofString());
        assertEquals(204, r3.statusCode());
    }
}
