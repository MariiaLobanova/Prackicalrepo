package org.example.Class2811;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserHandler implements HttpHandler {

    List<User> users = new ArrayList<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        System.out.println(exchange.getResponseBody());
        System.out.println(exchange.getRequestURI());
        System.out.println(exchange.getProtocol());


        if (exchange.getRequestMethod().equals("GET")) {
            List<User> users = handleGetRequest(exchange);
            for (User u : users) {
                response += u.toString();
            }

        } else if (exchange.getRequestMethod().equals("POST")) {
            List<User> updateUsers = handlePostRequest(exchange);
            for (User u : updateUsers) {
                response += u.toString();

            }
        } else if (exchange.getRequestMethod().equals("PUT")) {
                response = handlePutRequest(exchange);

        } else if (exchange.getRequestMethod().equals("DELETE")) {
            List<User> updatedUsers = handleDeleteRequest(exchange);
            for (User u : updatedUsers) {
                response += u.toString();
            }
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }


    private List<User> handleGetRequest(HttpExchange exchange) {
        return users;
    }

    private List<User> handlePostRequest(HttpExchange exchange) {
        User user = new User("Soroka", 1, "soroka@gmail.com");
        users.add(user);
        return users;
    }

    private String handlePutRequest(HttpExchange exchange) {
        return "put response";
    }

    private List<User> handleDeleteRequest(HttpExchange exchange){
        users.remove(0);
        return users;
    }
}

