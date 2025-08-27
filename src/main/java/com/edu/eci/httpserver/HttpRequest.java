/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.eci.httpserver;

import java.net.URI;

/**
 *
 * @author juan.mmendez
 */
public class HttpRequest {
    URI reqUri = null;
    public HttpRequest(URI requestUri) {  
        this.reqUri = requestUri;
    }
    public String getValue(String paramName) {
    String query = reqUri.getQuery();
    if (query == null) return null;

    String[] params = query.split("&");
    for (String p : params) {
        String[] kv = p.split("=", 2); // 👈 importante: solo dividir en 2
        if (kv[0].equals(paramName)) {
            return kv.length > 1 ? kv[1] : null;
        }
    }
    return null;
}
}
