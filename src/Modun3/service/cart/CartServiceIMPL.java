package Modun3.service.cart;

import Modun3.config.Config;
import Modun3.model.cart.Cart;
import Modun3.model.cart.CartItem;
import Modun3.model.user.User;
import Modun3.service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class CartServiceIMPL implements iCartService {
    private User user = new UserServiceIMPL().getCurentUser();

    public User getUserLogin() {
        return user;
    }

    private List<Cart> cartList = new Config<Cart>().readFromFile(Config.PATH_CART);

    @Override
    public List<Cart> findAll() {

        return cartList;
    }

    @Override
    public void save(Cart cart) {
        boolean check = false;
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getUser().equals(cart.getUser())) {
                cartList.set(i, cart);
                check = true;
                break;
            }
        }
        if (!check) {
            cartList.add(cart);
        } else {
            cartList.set(cartList.indexOf(findById(cart.getUser().getId())), cart);
        }
        new Config<Cart>().writeFromFile(Config.PATH_CART, cartList);

    }

    @Override
    public Cart findById(int id) {
        for (Cart cart : cartList) {
            if (cart.getUser().getId() == id) {
                return cart;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

        Cart cart = findById(user.getId());
        cart.getCartItemList().removeIf(cartItem -> cartItem.getProduct().getProductId() == id);
        new Config<Cart>().writeFromFile(Config.PATH_CART, cartList);
    }

    @Override
    public void deleteById(int idCartItem, int userId) {
        Cart carts = findById(user.getId());
        if (carts != null) {
            carts.getCartItemList().removeIf(cartItem -> cartItem.getProduct().getProductId() == idCartItem);
        }
        new Config<Cart>().writeFromFile(Config.PATH_CART, cartList);
    }

    @Override
    public void updateToListCartById(int idProductInCartItem, int quantity) {
        Cart cart = findById(user.getId());
        for (CartItem cartItem : cart.getCartItemList()) {
            if (cartItem.getProduct().getProductId() == idProductInCartItem) {
                cartItem.setQuantity(quantity);
            }
        }
        new Config<Cart>().writeFromFile(Config.PATH_CART, cartList);
    }

    @Override
    public void updateToCart(List<CartItem> listCart, int id) {
        if (cartList.isEmpty())
            cartList.add(new Cart(id, getUserLogin(), listCart));
        else {
            int cartIndex = cartList.indexOf(findById(getUserLogin().getId()));
            Cart temp = cartList.get(cartIndex);
            temp.setCartItemList(listCart);
        }
        new Config<Cart>().writeFromFile(Config.PATH_CART, cartList);
    }

    @Override
    public boolean addToCart(CartItem cartItem, int id) {
        Cart carts = findById(user.getId());
        if (carts != null) {
            for (CartItem cart : carts.getCartItemList()) {
                if (cart.getProduct().equals(cartItem.getProduct())) {
                    cart.setQuantity(cart.getQuantity() + cartItem.getQuantity());
                    save(carts);
                    return true;
                }
            }
            List<CartItem> itemList = carts.getCartItemList();
            itemList.add(cartItem);
            carts.setCartItemList(itemList);
            save(carts);
            return true;
        } else {
            List<CartItem> cartItemList = new ArrayList<>();
            cartItemList.add(cartItem);
            Cart newCart = new Cart(id, user, cartItemList);
            save(newCart);
            return true;
        }
    }

    @Override
    public CartItem getProductFromList(List<CartItem> cartItemList, int id) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProduct().getProductId() == id) {
                return cartItem;
            }
        }
        return null;
    }


}
