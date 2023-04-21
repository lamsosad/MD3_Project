package Modun3.service.order;

import Modun3.config.Config;
import Modun3.model.oder.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceIMPL implements iOrderService {
    List<Order> orderList = new ArrayList<>();
    List<Order> orders = new Config<Order>().readFromFile(Config.PATH_CART_ODER);


    @Override
    public List<Order> findAll() {
        return orderList;
    }

    @Override
    public void save(Order oder) {
        Order temp = findById(oder.getId());
        if (temp == null) {
            orderList.add(oder);
        } else {
            int orderIndex = orderList.indexOf(temp);
            orderList.set(orderIndex, oder);
        }
        new Config<Order>().writeFromFile(Config.PATH_CART_ODER, orderList);
    }

    @Override
    public Order findById(int id) {
        orders = new Config<Order>().readFromFile(Config.PATH_CART_ODER);
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                return orders.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == id) {
                orderList.remove(orderList.get(i));
            }
        }
    }
    @Override
    public void editStatusBillById(int idBill, boolean status) {
        for (Order order : orders) {
            if (order.getId() == idBill) {
                order.setStatusBuy(status);
            }
        }
        new Config<Order>().writeFromFile(Config.PATH_CART_ODER, orders);
    }
}
