package Class2911ECommercePlatform;
// id, productId, quantity, customer details.
public class Order {
    private int id;
    private int productId;
    private int quantity;
    private String customerDetails;

    public Order(int id, int productId, int quantity, String customerDetails) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.customerDetails = customerDetails;
    }
    public Order(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }
}
