package org.example.Class2111;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Create a transactional method for booking a flight. This should include
//inserting a record into the Bookings table and updating the SeatsAvailable
//in the Flights table.
public class Transactions {

    public static void bookingFlight(Connection connection, int numberOfPassengers, int customerId, int flightId ) throws SQLException {

        try{
            connection.setAutoCommit(false);
            String insertBooking = "INSERT INTO Booking (bookingDate, numberOfPassengers, status, customerId, flightId) " +
                    "VALUES(CURRENT_DATE(), ?, 'Confirmed', ?, ?)";

        try (PreparedStatement pst = connection.prepareStatement(insertBooking)){
            pst.setInt(1, numberOfPassengers);
            pst.setInt(2, customerId);
            pst.setInt(3, flightId);
            pst.executeUpdate();

        }
            String updateSeats = "UPDATE Flight SET seatAvailable = seatAvailable - ? WHERE flightId = ? AND SeatsAvailable >= ?;";

            try (PreparedStatement pst = connection.prepareStatement(updateSeats)){
            pst.setInt(1, numberOfPassengers);
            pst.setInt(2,flightId);
            pst.executeUpdate();

        }
        connection.commit();
            System.out.println("Flight booked successfully");
        } catch (SQLException e) {
            try {

                connection.rollback();
                System.out.println("rolled back");
            }catch (SQLException ex) {
                ex.printStackTrace();

            }finally{ try {
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
