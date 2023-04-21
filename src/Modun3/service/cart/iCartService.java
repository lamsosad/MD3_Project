package Modun3.service.cart;

import Modun3.model.cart.Cart;
import Modun3.model.cart.CartItem;
import Modun3.model.user.User;
import Modun3.service.iGenericService;

import java.util.List;

public interface iCartService extends iGenericService<Cart> {
    boolean addToCart(CartItem cartItem, int id);

    User getUserLogin();

    void updateToCart(List<CartItem> cartItems, int id);

    CartItem getProductFromList(List<CartItem> cartItemList, int id);

    void deleteById(int idCartItem, int userId);

    void updateToListCartById(int idProductInCartItem, int quantity);
}
