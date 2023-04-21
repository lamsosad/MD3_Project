package Modun3.model.cart;

import Modun3.model.user.User;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private int id;
    private User user;
    private List<CartItem> cartItemList;

    public Cart() {
    }

    public Cart(int id, User user, List<CartItem> cartItemList) {
        this.id = id;
        this.user = user;
        this.cartItemList = cartItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", cartItemList=" + cartItemList +
                '}';
    }
}
