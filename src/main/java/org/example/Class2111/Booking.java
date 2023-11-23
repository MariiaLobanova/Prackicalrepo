package org.example.Class2111;

import java.sql.*;

public class Booking {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            if(connection!=null) {
                System.out.println("Connected to the database successfully");

                displayAllBooking(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayAllBooking(Connection connection) {
        String query = "SELECT * FROM Booking;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            System.out.println("All bookings: ");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) +
                        " | " + rs.getInt(3) + " | " + rs.getString(4) + " | "
                        + rs.getInt(5) + " | " + rs.getInt(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
