package org.example.Class2411;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Create a Java program using JDBC that performs multiple money transfers between multiple bank accounts within a single transaction.
//Implement a rollback mechanism to ensure data consistency in case of an error during any part of the transaction.
//To set up the database with tables and initial data, you can use the following SQL statements as an example:
//-- Create a bank_accounts table
//CREATE TABLE bank_accounts (account_id INT AUTO_INCREMENT PRIMARY KEY,balance DECIMAL(10, 2)

//-- Insert initial account data
//INSERT INTO bank_accounts (account_id, balance) VALUES
//    (0, 1000.00),
//    (0, 1500.00),
//    (0, 800.00),
//    (0, 2000.00);
public class Exercise1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/School";
        String user = "root";
        String password = "Smetana11!!";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

            connection.setAutoCommit(false);

            String insertData1 = "INSERT INTO bank_accounts (account_id, balance) VALUES (0, ?)";

            PreparedStatement pst = connection.prepareStatement(insertData1);

            pst.setInt(1, 0);
            pst.setDouble(1, 2000);
            pst.executeUpdate();

            pst.setInt(1, 0);
            pst.setDouble(1, 2500);
            pst.executeUpdate();

            pst.setInt(1, 0);
            pst.setDouble(1, 1800);
            pst.executeUpdate();

            pst.setInt(1, 0);
            pst.setDouble(1, 5000);
            pst.executeUpdate();

            connection.commit();
            System.out.println("all transaction was done succesfully");

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    System.err.println("Transaction rolled back due to an error: " + e.getMessage());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            } finally {
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
