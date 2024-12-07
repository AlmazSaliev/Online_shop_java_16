package models;

import enums.Category;
import enums.Size;

import java.math.BigDecimal;
import java.util.Arrays;

public class Product {
    private long id;
    private Category category;
    private String name;
    private BigDecimal price;
    private Size[] size;
    private String color;
    private String imageUrl;
    private int quantity;

    private static long startid = 1;

    public Product() {
        this.id = startid++;
    }

    public Product(Category category, String name, BigDecimal price, Size[] size, String color, String imageUrl, int quantity) {
        this.id = startid++;
        this.category = category;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size[] getSize() {
        return size;
    }

    public void setSize(Size[] size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", size=" + Arrays.toString(size) +
                ", color='" + color + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
