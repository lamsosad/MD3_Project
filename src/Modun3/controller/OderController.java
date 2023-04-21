package Modun3.controller;

import Modun3.model.oder.Order;
import Modun3.service.cart.CartServiceIMPL;
import Modun3.service.cart.iCartService;
import Modun3.service.order.OrderServiceIMPL;
import Modun3.service.order.iOrderService;

import java.util.List;

public class OderController {
    iOrderService orderService = new OrderServiceIMPL();
    iCartService cartService = new CartServiceIMPL();

    public List<Order> getListOder() {
        return orderService.findAll();
    }
    public Order detailOder(int id){
        return orderService.findById(id);
    }
    public void deleteProductToListCart(int idCartItem, int idUser) {
        cartService.deleteById(idCartItem, idUser);
    }
    public void editStatusBill(int idOder,boolean status){
        orderService.editStatusBillById(idOder,status);
    }
}
