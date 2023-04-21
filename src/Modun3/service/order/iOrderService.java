package Modun3.service.order;

import Modun3.model.oder.Order;
import Modun3.service.iGenericService;

public interface iOrderService extends iGenericService<Order> {
    void editStatusBillById(int idBill, boolean status);
}
