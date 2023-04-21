package Modun3.model.cart;

import Modun3.model.product.Product;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private Product product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity, int id) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ""+ product;
    }
}
