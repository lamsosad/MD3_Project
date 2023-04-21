package Modun3.controller;


import Modun3.model.cart.Cart;
import Modun3.model.cart.CartItem;
import Modun3.model.user.User;
import Modun3.service.cart.CartServiceIMPL;
import Modun3.service.cart.iCartService;

import java.util.ArrayList;
import java.util.List;

public class CartController {
    iCartService cartService = new CartServiceIMPL();

    public List<CartItem> getListCartItem() {
        User user = cartService.getUserLogin();
        Cart myCart = cartService.findById(user.getId());
        if (myCart == null)
            return new ArrayList<>();
        return myCart.getCartItemList();
    }

    public void addToCart(CartItem cartItem,int id) {
        cartService.addToCart(cartItem,id);
    }

    public void updateToCart(List<CartItem> listCartItem,int id) {
        cartService.updateToCart(listCartItem,id);
    }

    public CartItem getProductFromList(List<CartItem> cartItemList, int id) {
        return cartService.getProductFromList(cartItemList, id);
    }
    public void deleteProductInListCart(int id){
        cartService.deleteById(id);
    }

    public void updateQuantityToListCart(int idCart,int quantity) {
       cartService.updateToListCartById(idCart,quantity);
    }
}
