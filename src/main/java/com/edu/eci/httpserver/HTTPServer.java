/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.eci.httpserver;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class HTTPServer {
    public static Map<String,Service> services = new HashMap<String,Service>();
    private static String staticFilesDir = "webroot";
    
    
    public static void startServer(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        boolean running = true;
        while (running){
            try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        boolean isFirstLine = true;
        URI requestUri = null;
        while ((inputLine = in.readLine()) != null) {
            if(isFirstLine){
                requestUri = new URI(inputLine.split(" ")[1]);
                System.out.println("Path: " + requestUri.getPath());
                isFirstLine = false;
            }
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }
        
        OutputStream rawOut = clientSocket.getOutputStream();

        if (services.containsKey(requestUri.getPath())) {
            String response = processRequest(requestUri);
            rawOut.write(response.getBytes());
            rawOut.flush();
        } else {
            sendStaticFile(rawOut, requestUri.getPath());
        }

        out.close();
        in.close();
        clientSocket.close();
        
        }
        serverSocket.close();
    }
    public static void staticfiles(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        staticFilesDir = path;
    }

    private static String processRequest(URI requestUri) {
        String serviceRoute = requestUri.getPath();  // <-- sin substring
        Service service = services.get(serviceRoute);
        HttpRequest req = new HttpRequest(requestUri);
        HttpResponse res = new HttpResponse();

        if (service == null) {
            return "HTTP/1.1 404 Not Found\r\n\r\nRoute not found";
        }

        String header = "HTTP/1.1 200 OK\r\n"
                  + "content-type: " + res.getContentType() + "\r\n\r\n";
        return header + service.executeService(req,res);
    }


    private static void sendStaticFile(OutputStream out, String requestPath) throws IOException {
        String relativePath = requestPath.startsWith("/") ? requestPath.substring(1) : requestPath;
        File file = new File(staticFilesDir, relativePath);

        if (file.exists() && !file.isDirectory()) {
            String mimeType = guessMimeType(file.getName());
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            String header = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: " + mimeType + "\r\n"
                        + "Content-Length: " + fileBytes.length + "\r\n\r\n";

            out.write(header.getBytes());
            out.write(fileBytes);   // aquí mandas el binario tal cual
            out.flush();
        } else {
            String error = "HTTP/1.1 404 Not Found\r\n\r\nFile not found";
            out.write(error.getBytes());
            out.flush();
        }
    }



    private static String guessMimeType(String filename) {
        if (filename.endsWith(".html")) return "text/html";
        if (filename.endsWith(".css")) return "text/css";
        if (filename.endsWith(".js")) return "application/javascript";
        if (filename.endsWith(".png")) return "image/png";
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return "image/jpeg";
        return "text/plain";
    }
    
    public static void get(String route, Service s){ 
        services.put(route, s);
    }
    
    

    
}
