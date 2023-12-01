package Class2911ECommercePlatform;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public void addOrder(Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            if (isProductAvailable(connection, order.getProductId(), order.getQuantity())) {

                try (PreparedStatement ps = connection.prepareStatement
                     ("INSERT INTO orders (id, productId, quantity, customerDetail) VALUES (?, ?, ?, ?);")) {

                    ps.setInt(1, order.getId());
                    ps.setInt(2, order.getProductId());
                    ps.setInt(3, order.getQuantity());
                    ps.setString(4, order.getCustomerDetails());

                    ps.executeUpdate();
                }
            }else{
                System.out.println("not enough products or productId doesnt exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isProductAvailable(Connection connection, int productId, int requestedQuantity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT stockQuantity FROM products WHERE id = ?")) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int stockQuantity = rs.getInt("stockQuantity");
                    return stockQuantity >= requestedQuantity;
                }
                return false;
            }
        }
    }

    public Order getOrder(int id){
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getString("customerDetails"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> ordersList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                Order order = new Order(rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getString("customerDetail"));

                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public void deleteOrder(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOrder(Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE orders SET quantity = ?, WHERE id = ?;");

            ps.setDouble(1, order.getQuantity());
            ps.setInt(2, order.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
