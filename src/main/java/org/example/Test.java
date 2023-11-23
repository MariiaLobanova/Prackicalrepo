package org.example;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/School";
        String user = "root";
        String password = "Smetana11!!";
        Connection con = DriverManager.getConnection(url, user, password);
        String insertQuery = "INSERT INTO Users (name, age) VALUES (?, ?)";
        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            // Set Parameters for INSERT
            insertStmt.setString(1, "Alice");
            insertStmt.setInt(2, 25);

            // Execute INSERT Query
            int rowsAffected = insertStmt.executeUpdate();
            System.out.println("Rows affected by INSERT: " + rowsAffected);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // SELECT Query to verify the inserted data
        String selectQuery = "SELECT * FROM Users";
        try (Statement selectStmt = con.createStatement();
             ResultSet selectRs = selectStmt.executeQuery(selectQuery)) {
            System.out.println("\nResults after INSERT:");
            System.out.println("Name\t Age");
            while (selectRs.next()) {
                String name = selectRs.getString("name");
                int age = selectRs.getInt("age");
                System.out.println(name + "\t " + age);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Close the connection
        con.close();
    }
}


