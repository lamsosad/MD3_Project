package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.CartController;
import Modun3.controller.OderController;
import Modun3.controller.UserController;
import Modun3.dto.request.UserOrderDTO;
import Modun3.model.cart.CartItem;
import Modun3.model.oder.Order;
import Modun3.model.user.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderView {
    OderController oderController = new OderController();
    CartController cartController = new CartController();
    List<Order> oderList = oderController.getListOder();
    List<CartItem> cartItemList = cartController.getListCartItem();
    List<Order> orders = new Config<Order>().readFromFile(Config.PATH_CART_ODER);
    UserController userController = new UserController();
    User user = userController.getUserLogin();

    public void editStatusBillOder() {
        if (orders == null) {
            System.err.println("No bill! Try again ");
            new ProfileView();
        } else {
            System.out.println("Enter ID bill you want to change");
            int idOder = Config.scanner().nextInt();
            System.out.println("Enter Status bill you want to change");
            System.out.println("1. true | 2. false");
            byte selectOption = Config.scanner().nextByte();
            boolean newStatus;
            newStatus = selectOption == 1;
            oderController.editStatusBill(idOder, newStatus);
            System.out.println("Change success");
            new ProfileView();
        }

    }

    public void buyProductInCart() {
        int id = 0;
        if (orders.size() == 0) {
            id = 1;
        } else {
            id = orders.get(orders.size() - 1).getId() + 1;
        }
        boolean status = false;
        String userNameLogin = user.getUsername();
        new CartView().showListCart();
        List<CartItem> cartItems = new ArrayList<>();
        while (true) {
            if (cartItemList.isEmpty()) {
                System.out.println("No more product in your cart!");
                System.out.println("Enter anykey or Press 'back' to continue order!");
                String input = Config.scanner().nextLine();
                if (input.equalsIgnoreCase("back")) {
                    new CartView().cartNavbar();
                    break;
                }
            } else {
                System.out.println("Enter the Product in list cart");
                int idBuy = Config.scanner().nextInt();
                CartItem temp = cartController.getProductFromList(cartItemList, idBuy);
                if (temp == null) {
                    System.out.println("Not match any product in list!");
                    System.out.println("Please try again my bro!");
                } else {
                    cartItems.add(temp);
                    oderController.deleteProductToListCart(idBuy, id);
                    System.out.println("Selected Success!");
                    System.out.println("Enter anykey or Press 'back' to stop select!");
                    String input = Config.scanner().nextLine();
                    if (input.equalsIgnoreCase("back")) {
                        break;
                    }
                }
            }
        }
        System.out.println("Enter the name receiver");
        String name = user.getName();
        name(name);
        System.out.println("Enter the phone");
        String phone;
        while (true) {
            phone = Config.scanner().nextLine();
            if (Config.setValidatePhone(phone)) {
                break;
            }
            System.err.println(" Invalid Phone, try again!");
        }
        System.out.println("Enter the address receiver");
        String address = Config.scanner().nextLine();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        String nameLogin = user.getName();
        float total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        UserOrderDTO userOrderDTO = new UserOrderDTO(name, userNameLogin, phone, address);
        Order order = new Order(id, nameLogin, userOrderDTO, cartItems, status, date, total);
        List<Order> orderList = new Config<Order>().readFromFile(Config.PATH_CART_ODER);
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        orderList.add(order);
        System.out.println("Order Success! Thank you so much! Waiting shipper ");
        new Config<Order>().writeFromFile(Config.PATH_CART_ODER, orderList);
    }

    public String name(String loginUserFullName) {
        System.out.println("Do you want select this name to receiver order: " + loginUserFullName);
        while (true) {
            System.out.println("1.Yes/ 2.No");
            byte choose = Config.scanner().nextByte();
            switch (choose) {
                case 1:
                    return loginUserFullName;
                case 2:
                    System.out.print("Enter the name receiver: ");
                    return Config.scanner().nextLine();
                default:
                    System.err.println("Not match any option! Dont do that my man!");
                    break;
            }
        }
    }

    public void billToAdmin() {
        System.out.println("**************************** ORDER ADMIN ****************************");
        for (Order order : orders) {
            System.out.println("\n-------------------------- Number bill: " + order.getId() + " --------------------------");
            System.out.print("\n    NameUser: " + order.getOrderCustomer() + "        |  ");
            System.out.println("Date order: " + order.getDate());
            System.out.println("\n    Name reveicer: " + order.getUserDTO().getName());
            System.out.println("\n    Phone reveicer: " + order.getUserDTO().getPhone());
            System.out.println("\n    Address reveicer: " + order.getUserDTO().getAddress());
            for (int j = 0; j < order.getCartItemList().size(); j++) {
                System.out.print("\n    Product: " + order.getCartItemList().get(j).getProduct().getProductName() + "  | Price:" + order.getCartItemList().get(j).getProduct().getPrice() + " x");
                System.out.print(order.getCartItemList().get(j).getQuantity());
                System.out.print("    | Color " + order.getCartItemList().get(j).getProduct().getColor() + "\n");
            }
            System.out.println("\n    ---------------------------------------------------");
            System.out.println("    Total:  " + order.getTotal() + "$");
            System.out.println("    ---------------------------------------------------");
            System.out.println("\n    Status: " + (order.isStatusBuy() ? "<Delivery>" : "<Wait for confirmation>"));
        }

        System.out.println("\n********************************************************************\n");
    }

    public void billOrder() {
        List<Order> orderListOfCurrentUser = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserDTO().getUserName().equals(user.getUsername())) {
                orderListOfCurrentUser.add(order);
            }
        }
        if (orderListOfCurrentUser.isEmpty()) {
            System.err.println("You not have any order!");
        } else {
            System.out.println("**************************** BILL ORDER ****************************");
            System.out.println("                         ____   ______________                      ");
            System.out.println("                        /   |   |   APPLE    |                      ");
            System.out.println("                       /____|___|   STORE    |                      ");
            System.out.println("                      |----------------------/                      ");
            System.out.println("                      |-(   )---------(   )-|                       ");
            for (int i = 0; i < orderListOfCurrentUser.size(); i++) {
                System.out.println("\n-------------------------- Number bill: " + orderListOfCurrentUser.get(i).getId() + " --------------------------");
                System.out.print("\n    NameUser: " + orderListOfCurrentUser.get(i).getOrderCustomer() + "        |  ");
                System.out.println("Date order: " + orderListOfCurrentUser.get(i).getDate());
                System.out.println("\n    Name reveicer: " + orderListOfCurrentUser.get(i).getUserDTO().getName());
                System.out.println("\n    Phone reveicer: " + orderListOfCurrentUser.get(i).getUserDTO().getPhone());
                System.out.println("\n    Address reveicer: " + orderListOfCurrentUser.get(i).getUserDTO().getAddress());
                for (int j = 0; j < orderListOfCurrentUser.get(i).getCartItemList().size(); j++) {
                    System.out.print("\n    Product: " + orderListOfCurrentUser.get(i).getCartItemList().get(j).getProduct().getProductName() + "  | Price:" + orderListOfCurrentUser.get(i).getCartItemList().get(j).getProduct().getPrice() + " x");
                    System.out.print(orderListOfCurrentUser.get(i).getCartItemList().get(j).getQuantity());
                    System.out.print("    | Color " + orderListOfCurrentUser.get(i).getCartItemList().get(j).getProduct().getColor() + "\n");

                }
                System.out.println("\n    ---------------------------------------------------");
                System.out.println("    Total:  " + orderListOfCurrentUser.get(i).getTotal() + "$");
                System.out.println("    ---------------------------------------------------");
                System.out.println("\n    Status: " + (orderListOfCurrentUser.get(i).isStatusBuy() ? "<Delivery>" : "<Wait for confirmation>"));
            }
            System.out.println("\n********************************************************************\n");
        }

    }
}
