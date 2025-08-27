

import com.edu.eci.httpserver.HTTPServer;
import com.edu.eci.httpserver.HttpRequest;
import com.edu.eci.httpserver.HttpResponse;
import com.edu.eci.httpserver.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class HTTPServerTest {

    @BeforeEach
    void setUp() {
        HTTPServer.services.clear(); 
    }

    @Test
    void testHelloServiceWithQueryParam() throws Exception {
        
        HTTPServer.get("/hello", (req, res) -> "Hello " + req.getValue("name"));

        
        URI uri = new URI("/hello?name=Pedro");
        String response = invokeService(uri);

        assertTrue(response.contains("Hello Pedro"),
                "La respuesta debe contener el saludo con el nombre");
    }

    @Test
    void testPiService() throws Exception {
        HTTPServer.get("/pi", (req, res) -> String.valueOf(Math.PI));

        URI uri = new URI("/pi");
        String response = invokeService(uri);

        assertTrue(response.contains(String.valueOf(Math.PI)),
                "La respuesta debe contener el valor de PI");
    }

    @Test
    void testQueryParamExtractionMultipleValues() throws Exception {
        HttpRequest req = new HttpRequest(new URI("/test?name=Ana&age=22"));
        assertEquals("Ana", req.getValue("name"));
        assertEquals("22", req.getValue("age"));
    }

    @Test
    void testStaticFileHtml() throws Exception {
        
        URL url = new URL("http://localhost:35000/index.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());
        String body = new String(conn.getInputStream().readAllBytes());
        assertTrue(body.contains("<html")); 
    }

    @Test
    void testStaticFileNotFound() throws Exception {
        URL url = new URL("http://localhost:35000/doesnotexist.txt");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(404, conn.getResponseCode());
    }


    private String invokeService(URI uri) {
        HttpRequest req = new HttpRequest(uri);
        HttpResponse res = new HttpResponse();
        String route = uri.getPath();
        Service service = HTTPServer.services.get(route);
        String header = "HTTP/1.1 200 OK\r\n\r\n";
        return header + service.executeService(req, res);
    }
}
