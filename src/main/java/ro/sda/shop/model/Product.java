package ro.sda.shop.model;

public class Product extends Entity {
    //test purpose
    private String name;
    private String description;
    private Double price;

   /* public Product(Integer id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }*/

    public Product() {
    }

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
