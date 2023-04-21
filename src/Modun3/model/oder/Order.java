package Modun3.model.oder;

import Modun3.dto.request.UserOrderDTO;
import Modun3.model.cart.CartItem;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private String orderCustomer;
    private UserOrderDTO user;
    private List<CartItem> cartItemList;
    private boolean statusBuy;
    private String date;
    private double total;

    public Order() {
    }

    public Order(int id, String orderCustomer, UserOrderDTO user, List<CartItem> cartItemList, boolean statusBuy, String date, double total) {
        this.id = id;
        this.orderCustomer = orderCustomer;
        this.user = user;
        this.cartItemList = cartItemList;
        this.statusBuy = false;
        this.date = date;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(String orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public UserOrderDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserOrderDTO user) {
        this.user = user;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public boolean isStatusBuy() {
        return statusBuy;
    }

    public void setStatusBuy(boolean statusBuy) {
        this.statusBuy = statusBuy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ""+cartItemList;
    }
}
