package org.example.Class2111;

import java.sql.*;

public class Customer {

    public static void createCustomer(Connection connection, int CustomersId, String name, String email, String phone) {
        String query = "INSERT INTO Customers( ) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, CustomersId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            int result = preparedStatement.executeUpdate();
            System.out.println(result + "row was inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void UpdateCustomer(Connection connection, int CustomersId, String email, String phone) {
        String query = "UPDATE Customers SET email, phone = ? WHERE CustomersId = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, CustomersId);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            int result = preparedStatement.executeUpdate();
            System.out.println(result + "row updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayAllCustomer(Connection connection) {
        String query = "SELECT * FROM Customers;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            System.out.println("All customers: ");
            while (rs.next()) {
                System.out.printf("%-5s | %-10s | %-15s | %-15s%n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCustomer(Connection connection, int CustomersId) {
        String query = "DELETE FROM Customers WHERE CustomersId = ?;";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, CustomersId);
            int result = pst.executeUpdate();
            System.out.println(result + "row was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayCustomerRecord(Connection connection, String name, String email) {
        String query = "SELECT * FROM Customers WHERE name = ? OR email = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("data about customer: ");
            while (rs.next()) {
                System.out.printf("%-5s | %-10s | %-15s | %-15s%n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
