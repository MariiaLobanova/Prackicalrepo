package org.example.Class2111;

import java.sql.*;
// CRUD for Flight table
public class Flight {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Airplane Ticket Reservation System";
        String user = "root";
        String password = "Smetana11!!";

        try (Connection conn = DriverManager.getConnection(url, user,
                password)) {
            System.out.println("Connected to the database successfully");

            //insertInfoFlight(conn, 36, "airline33", "Samara", "Berlin",
            //      "2023-11-20 12:00:00","2023-11-20 16:00:00",50.99,100);
            //updateFlightAvailableseat(conn, 33,99);
            //deleteFlight(conn,35);
            displayAllFlight(conn);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static void insertInfoFlight (Connection connection, int FlightId, String airline,
                                         String origin, String destination, String DepTime,
                                         String ArrTime, double price, int seatAvailable){
        String query = "INSERT INTO Flight( FlightId, airline, origin, destination, DepTime, ArrTime,price, " +
                "seatAvailable) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)){
            pst.setInt(1, FlightId);
            pst.setString(2, airline);
            pst.setString(3, origin);
            pst.setString(4,destination);
            pst.setString(5, DepTime);
            pst.setString(6, ArrTime);
            pst.setDouble(7,price);
            pst.setInt(8,seatAvailable);
            int result = pst.executeUpdate();
            System.out.println(result + "row was inserted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void updateFlightAvailableseat(Connection connection, int FlightId, int seatAvailable){
        String query2 = "UPDATE Flight SET seatAvailable = ? WHERE FlightId = ?;";
        try (PreparedStatement pst = connection.prepareStatement(query2)){
            pst.setInt(1,seatAvailable);
            pst.setInt(2,FlightId);
            int result = pst.executeUpdate();
            System.out.println(result + "row are updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFlight ( Connection connection, int FlightId){
        String query3 = "DELETE FROM Flight WHERE FlightId = ?;";
        try(PreparedStatement pst = connection.prepareStatement(query3)){
            pst.setInt(1, FlightId);
            int result = pst.executeUpdate();
            System.out.println(result + "row was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayAllFlight(Connection connection){
        String query4 = "SELECT * FROM Flight;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query4);
            ResultSet rs = preparedStatement.executeQuery()){
            System.out.println("Flight: ");
            while(rs.next()){
                System.out.println(rs.getInt(1) +" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getString(3)+" "
                        +rs.getString(4)+" | "+rs.getString(5)+" | "+rs.getString(6)+" | "+
                        rs.getDouble(7)+" | "+rs.getString(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
