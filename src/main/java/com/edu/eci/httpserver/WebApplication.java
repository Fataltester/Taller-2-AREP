/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.eci.httpserver;

import static com.edu.eci.httpserver.HTTPServer.get;
import static com.edu.eci.httpserver.HTTPServer.staticfiles;
import static com.edu.eci.httpserver.HTTPServer.startServer;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author juan.mmendez
 */
public class WebApplication {
     public static void main(String[] args) throws IOException, URISyntaxException {
        staticfiles("src/main/public");
        get("/hello", (req, resp) -> "Hello " + req.getValue("name"));
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI); 
        });
        
        startServer(args);
    }
    
     
}
