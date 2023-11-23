package org.example.Class2111;

import java.sql.*;

public class Joins {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            if(connection!=null) {
                System.out.println("Connected to the database successfully");

                customerBookingJoins(connection);
                listFlightWithDepData(connection, "Berlin", null);
                listFlightWithDepData(connection, null, "2023-11-20 12:00:00");
                bookingByCustomer(connection, "Anton");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void customerBookingJoins(Connection connection) {
        String query = "SELECT " +
                "    Customers.name AS CustomerName," +
                "    Booking.BookingId," +
                "    Booking.BookingDate," +
                "    Booking.NumberOfPassengers," +
                "    Booking.Status FROM Customers JOIN Booking ON Customers.CustomersId = Booking.CustomerId;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            System.out.println("Customers/ bookings ");
            while (rs.next()) {
                System.out.printf("%-15s | %-5s | %-15s | %-15s%n",
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Task 2: Develop a Query to List Flights Based on Specific Criteria:
//Subtask 2.1:
//● Create a SQL query to list flights. For instance, list all flights departing from
//a specific airport or on a specific date.
    public static void listFlightWithDepData(Connection connection, String origin, String depTime) {
        String query = "SELECT * FROM Flight WHERE origin =? OR depTime =?;";
        try (PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, origin);
            pst.setString(2, depTime);

            ResultSet rs = pst.executeQuery();
            System.out.println("data about flight: ");
            while (rs.next()) {
                System.out.printf("%-3s | %-10s | %-10s | %-10s | %-15s | %-10s | %-5s | %-5s%n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Query to Retrieve All Bookings for a Given Customer:
    public static void bookingByCustomer(Connection connection, String name) {
        String query = "SELECT Customers.name, Booking.BookingDate, Booking.NumberOfPassengers, " +
                "Flight.origin, Flight.destination " +
                "FROM Customers JOIN Booking ON Customers.CustomersId = Booking.CustomerId JOIN Flight ON " +
                "Flight.FlightId = Booking.FlightId WHERE " +
                "Customers.name = ? ";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, name);

            ResultSet rs = pst.executeQuery();
            System.out.println("Booking#s information customer:  " + name);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | "
                        + rs.getString(2) + " | "
                        + rs.getInt(3)+" | "
                        + rs.getString(4) + " | "
                        + rs.getString(5));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
