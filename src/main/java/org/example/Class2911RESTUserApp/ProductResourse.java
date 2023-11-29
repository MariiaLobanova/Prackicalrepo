package org.example.Class2911RESTUserApp;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductResourse {
    ProductDao productDao = new ProductDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getUsers() {
        return productDao.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct (@PathParam("id") int id) {
        return productDao.getProduct(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }
}

