package org.example.Class2111;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            if(connection!=null){
                System.out.println("Connected to the database successfully");

                //Flight.insertInfoFlight(conn, 36, "airline33", "Samara", "Berlin",
                //      "2023-11-20 12:00:00","2023-11-20 16:00:00",50.99,100);
                //Flight.updateFlightAvailableseat(conn, 33,99);
                //Flight.deleteFlight(conn,35);
                Flight.displayAllFlight(connection);


                Customer.displayAllCustomer(connection);
                Customer.displayCustomerRecord(connection, "Anton", "vera@gmail.com");

                Booking.displayAllBooking(connection);

               //Transactions.bookingFlight(connection,3,12,4 ); // succesfull

                //Transactions.bookingFlight(connection,1,22,33 ); //  unsuccesfull, customerId doesn't exist
                //Transactions.bookingFlight(connection,5,12,38 ); // unsuccesfull, flightId doesn't exist

                Transactions.bookingFlight(connection,20,12,33 );



            }
        } catch (SQLException e) {
            System.out.println("Something went wrong:");
            e.printStackTrace();  // This will print the stack trace to the console
            throw new RuntimeException(e);
        }
    }
}
