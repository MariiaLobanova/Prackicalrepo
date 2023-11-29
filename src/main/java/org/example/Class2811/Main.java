package org.example.Class2811;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        System.out.println("http://localhost:"+ serverPort);

        server.createContext("/api/user", new UserHandler());
        server.setExecutor(null); // Default executor
        server.start();




    }
}
