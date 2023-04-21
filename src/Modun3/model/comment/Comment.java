package Modun3.model.comment;

import Modun3.model.oder.Order;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private String comment;
    private Order order;

    public Comment() {
    }

    public Comment(int id, String comment, Order order) {
        this.id = id;
        this.comment = comment;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", order=" + order +
                '}';
    }
}
