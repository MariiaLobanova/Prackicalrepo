package Class2911ECommercePlatform;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
public class OrderResourse {
    OrderDao orderDao = new OrderDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrder() {
        return orderDao.getAllOrders();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("id") int id) {
        return orderDao.getOrder(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrder(@PathParam("id") int id, Order order) {
        order.setId(id);
        orderDao.updateOrder(order);
    }

    @DELETE
    @Path("/{id}")
    public void deleteOrder(@PathParam("id") int id) {
        orderDao.deleteOrder(id);
    }
}
