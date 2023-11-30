package Class2911ECommercePlatform;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static void main(String[] args) {
       String BASE_URI = "http://localhost:8000/";
       ResourceConfig resourceConfig = new ResourceConfig(ProductResourse.class);
       GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
       System.out.println("Server started at: " + BASE_URI);

       //ProductDao pd = new ProductDao(new Product(0,"sweatshort", 12.99,"clothes", 50));
       //pd.addProduct();


       // Product product = new Product(0, "sweatshort", 12.99, "clothes", 50);

      // ProductDao pd = new ProductDao();
        //pd.addProduct(product);
        //pd.getAllProductByCategoryAndPrice("clothes", 5.00,15.00);

    }

}
