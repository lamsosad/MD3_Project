package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.CartController;
import Modun3.controller.ProductController;
import Modun3.model.cart.CartItem;
import Modun3.model.product.Product;

import java.util.List;


public class CartView {
    CartController cartController = new CartController();
    ProductController productController = new ProductController();
    List<CartItem> listCartItem = cartController.getListCartItem();


    public void cartNavbar() {
        System.out.println("********************************** CART ************************************");
        String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.format(alignFormat, 1, "Add to Cart", 2, "Show ListCart", 3, "Buy in ListCart");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.format(alignFormat, 4, "Edit ListCart", 5, "Show Order", 6, "Home");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.println("****************************************************************************");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                addToCart();
                cartNavbar();
                break;
            case 2:
                showListCart();
                cartNavbar();
                break;
            case 3:
                new OrderView().buyProductInCart();
                cartNavbar();
                break;
            case 4:
                editQuantityToListCart();
                cartNavbar();
                break;
            case 5:
                new OrderView().billOrder();
                cartNavbar();
                break;
            case 6:
                new ProfileView();
                break;
        }
    }

    private void editQuantityToListCart() {
        showListCart();
        System.out.println("Enter ID you want edit quantity");
        int idProductInCart = Config.scanner().nextInt();
        System.out.println("Enter quantity you want edit");
        int quantity = Config.scanner().nextInt();
        if (quantity > 0) {
            cartController.updateQuantityToListCart(idProductInCart, quantity);
        } else {
            cartController.deleteProductInListCart(idProductInCart);
        }
        System.out.println("Update Success");
    }


    public void showListCart() {
        System.out.println("********************************* SHOW LIST MY CART *********************************");
        String alignFormat = "| %-4d | %-15s | %-15s | %-15s | %-7s | %-10s |%n";
        System.out.format("+------+-----------------+-----------------+-----------------+---------+------------+%n");
        System.out.format("|  ID  |   PRODUCT NAME  |      COLOR      |      STATUS     |  PRICE  |  QUANTITY  |%n");
        System.out.format("+------+-----------------+-----------------+-----------------+---------+------------+%n");
        for (CartItem cartItem : listCartItem) {
            System.out.format(alignFormat, cartItem.getProduct().getProductId(), cartItem.getProduct().getProductName(), cartItem.getProduct().getColor(),
                    (cartItem.getProduct().isProductStatus() ? "Stocking" : "Sold out"), cartItem.getProduct().getPrice(), cartItem.getQuantity());
        }
        System.out.format("+------+-----------------+-----------------+-----------------+---------+------------+%n");
        System.out.println("*************************************************************************************");
    }

    public void addToCart() {
        int id = 0;
        if (listCartItem.size() == 0) {
            id = 1;
        } else {
            id = listCartItem.get(listCartItem.size() - 1).getId() + 1;
        }
        System.out.println("Enter ID to product you want add to your Cart ");
        Product product = productController.detailProduct(Config.scanner().nextInt());
        if (product == null) {
            System.err.println("ID not found! try again! ");
            addToCart();
        } else {
            System.out.println("Enter quantity");
            int quatity = Config.scanner().nextInt();
            for (CartItem cartItem : listCartItem) {
                if (cartItem.getProduct().getProductId() == product.getProductId()) {
                    cartItem.setQuantity(cartItem.getQuantity() + quatity);
                    cartController.updateToCart(listCartItem, id);
                    System.out.println("ADD to cart success!");
                    return;
                }
            }
            CartItem newCartItem = new CartItem(product, quatity, id);
            cartController.addToCart(newCartItem, id);
            System.out.println("ADD to cart success!");
        }

    }
}
