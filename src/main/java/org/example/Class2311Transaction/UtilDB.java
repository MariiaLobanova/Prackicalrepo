package org.example.Class2311Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDB {
    private static final String url = "jdbc:mysql://localhost:3306/ Course";
    private static final String user = "root";
    private static final String password = "Smetana11!!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
