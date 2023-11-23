package org.example.Class2111;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3306/Airplane Ticket Reservation System";
    private static final String user = "root";
    private static final String password = "Smetana11!!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
