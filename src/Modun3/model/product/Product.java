package Modun3.model.product;

import Modun3.model.product.Catalog;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private String title;
    private String color;
    private double price;
    private Catalog catalog;
    private boolean productStatus;

    public Product() {
    }

    public Product(int productId, String productName, String title, String color, double price, Catalog catalog, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.title = title;
        this.color = color;
        this.price = price;
        this.catalog = catalog;
        this.productStatus = productStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return ""+ productName;
    }
}
