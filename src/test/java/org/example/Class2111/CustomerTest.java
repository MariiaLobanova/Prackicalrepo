package org.example.Class2111;

import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class CustomerTest {

    @Test
    public void testCreateCustomer() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Airplane Ticket Reservation System";
        String user = "root";
        String password = "Smetana11!!";
        Connection connection = DriverManager.getConnection(url, user, password);

        int CustomersId = 31;
        String name = "George";
        String email = "george@example.com";
        String phone = "1234567890";

        Customer.createCustomer(connection, CustomersId, name, email, phone);
        String query = "SELECT * FROM Customers WHERE CustomersId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, CustomersId);
            assertEquals(0, preparedStatement.executeQuery().getFetchSize());
        }
        connection.close();
    }

@Test
    public void testUpdateCustomer() {
    }

    public void testDisplayAllCustomer() {
    }

    public void testDeleteCustomer() {
    }

    public void testDisplayCustomerRecord() {
    }
}