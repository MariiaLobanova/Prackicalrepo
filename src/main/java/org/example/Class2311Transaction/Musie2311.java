package org.example.Class2211Transaction;
// Write a Java program that connects to the database and performs the following tasks:
//Inserts data into one table
//Updates data in another table
//Deletes data from one table
//Uses transactions to ensure that all three tasks are completed successfully or rolled back if an error occurs
//Uses try-catch blocks to handle SQLExceptions and print error messages

import org.example.Class2111.DBUtil;

import java.sql.*;

// Table 1: Courses
//Columns:ID (Primary Key)CourseName CourseDescription
//Table 2: Themes
//Columns:ID (Primary Key)ThemeName CourseID (Foreign Key referencing the ID column in the Courses table)
//This schema represents a one-to-many relationship between Courses and Themes,
public class Musie2311 {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = UtilDB.getConnection();
            if(connection!=null) {
                System.out.println("Connected to the database successfully");

                CRUDTransaction(connection,"English", "sky");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void CRUDTransaction (Connection connection, String name, String themeName) throws SQLException {
        connection.setAutoCommit(false);
        ResultSet rs = null;
        String selectCourse = "SELECT courseId FROM Course Where name = ? ;";
        String insertTheme = "INSERT INTO Themes (ThemeName, courseId) VALUES (?,?);";
        String updateTheme = "UPDATE Themes SET ThemeName = ? WHERE courseId = ?;";
        String deleteTheme = "DELETE FROM Themes WHERE courseId = ?";
        Savepoint save1 = connection.setSavepoint();

        try (PreparedStatement getCourse = connection.prepareStatement(selectCourse);
             PreparedStatement insertTh = connection.prepareStatement(insertTheme);
             PreparedStatement updateTh = connection.prepareStatement(updateTheme);
             PreparedStatement deleteTh = connection.prepareStatement(deleteTheme)) {

            getCourse.setString(1, name);
            if (getCourse.execute()) {
                rs = getCourse.getResultSet();
                if (rs.next()) {
                    int courseId = rs.getInt("courseId");
                    insertTh.setString(1, themeName);
                    insertTh.setInt(2, courseId);
                    insertTh.executeUpdate();

                    updateTh.setString(1, themeName + " edited");
                    updateTh.setInt(2, courseId);
                    updateTh.executeUpdate();

                    deleteTh.setInt(1, courseId);
                    deleteTh.executeUpdate();
                } else {
                    System.out.println("Could not find entry for course ");
                }
            } else {
                System.out.println("Error executing query for course");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback(save1);
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            connection.setAutoCommit(true);
        }
    }
}

