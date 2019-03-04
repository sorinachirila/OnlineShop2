package ro.sda.shop.model;

public class Stock extends Entity {

    public static final String DEFAULT_LOCATION = "Oradea";
    private Product product;
    private String location;
    private Integer quantity;

/*    public Stock(Integer id, Product product, Integer quantity, String location) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.location = location;
    }*/

    public Stock() {
    }

    public Stock(Product product, Integer quantity, String location) {
        this.product = product;
        this.quantity = quantity;
        this.location = location;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
