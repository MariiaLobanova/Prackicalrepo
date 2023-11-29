package Class2911ECmercePlatform;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductResourse {

        ProductDao productDao = new ProductDao();
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Product> getUsers() {
            return productDao.getAllProduct();
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Product getProduct(@PathParam("id") int id) {
            return productDao.getProduct(id);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addProduct(Product product) {
            productDao.addProduct(product);
        }

        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateUser(@PathParam("id") int id, Product product) {
            product.setId(id);
            productDao.updateProduct(product);
        }

        @DELETE
        @Path("/{id}")
        public void deleteProduct(@PathParam("id") int id) {
            productDao.deleteProduct(id);
        }

}
