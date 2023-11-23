package org.example;

import java.sql.*;

public class SQLschoolDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/School";
        String user = "root";
        String password = "Smetana11!!";

        try (Connection conn = DriverManager.getConnection(url, user,
                password)) {
            System.out.println("Connected to the database successfully");

            String query = "SELECT * FROM Courses";
            try (PreparedStatement pst = conn.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    System.out.println( rs.getString("courseName") +" "+ rs.getString("courseId"));
                }
             /*   try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }*/

            }
            String query2 = "SELECT name, age FROM Users WHERE age > 30 AND name = 'Victor'";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs2 = stmt.executeQuery(query2)) {

                System.out.println("Results for Query 2:");
                while (rs2.next()) {
                    String name = rs2.getString("name");
                    int age = rs2.getInt("age");
                    System.out.println(name + " " + age);
                }

                try {
                    if (rs2 != null) rs2.close();
                 //   if (pst != null) pst.close();
                    if (conn != null) conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }

    }
}
