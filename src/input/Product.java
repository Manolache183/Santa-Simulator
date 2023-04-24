package input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Category;

public final class Product {

    /** Class used to implement santa's gifts **/

    private String productName;
    private double price;
    private Category category;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int quantity;

    public Product() { }

    /**
     * Copy constructor
     * @param product
     *              the object that needs to be copied
     */
    public Product(final Product product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.quantity = product.getQuantity();
    }

    /** Getters and Setters **/

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @JsonIgnore
    public int getQuantity() {
        return quantity;
    }
}
