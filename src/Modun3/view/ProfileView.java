package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.UserController;
import Modun3.model.user.Role;
import Modun3.model.user.RoleName;
import Modun3.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfileView {
    UserController userController = new UserController();

    public ProfileView() {
        User user = userController.getUserLogin();
        if (user != null) {
            Set<Role> roleSet = user.getRoleSet();
            List<Role> roleList = new ArrayList<>(roleSet);
            if (roleList.get(0).getName() == RoleName.ADMIN) {
                System.out.println("****************************** ADMIN MANAGER *******************************");
                String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
                System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
                System.out.format(alignFormat, 1, "Catalog manager", 2, "Product manager", 3, "User manager");
                System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
                System.out.format(alignFormat, 4, "Order manager", 5, "Comment manager", 6, "Home");
                System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
                System.out.println("****************************************************************************");
                int chooseMenu = Config.scanner().nextInt();
                switch (chooseMenu) {
                    case 1:
                        new CatalogView().CatalogViewNavBar();
                        break;
                    case 2:
                        new ProductView().ProductNavbar();
                        break;
                    case 3:
                        new UserView().UserViewNavbar();
                        break;
                    case 4:
                        new OrderView().billToAdmin();
                        new OrderView().editStatusBillOder();
                        break;
                    case 5:
                        new CommentView().showListComment();
                        break;
                    case 6:
                        new Navbar();
                        break;
                }
            } else if (roleList.get(0).getName() == RoleName.USER) {
                System.out.println("****************************** USER PERFORM ********************************");
                String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
                System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
                System.out.format(alignFormat, 1, "Show product", 2, "Cart", 3, "Comment");
                System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
                System.out.format(alignFormat, 4, "Search product", 5, "Change Password", 6, "Home");
                System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
                System.out.println("****************************************************************************");
                int chooseMenu = Config.scanner().nextInt();
                switch (chooseMenu) {
                    case 1:
                        new ProductView().showProduct();
                        new ProfileView();
                        break;
                    case 2:
                        new CartView().cartNavbar();
                        break;
                    case 3:
                        new CommentView().writeComment();
                        break;
                    case 4:
                        new ProductView().searchProductByName();
                        new ProfileView();
                        break;
                    case 5:
                        new UserView().changePassword();
                        break;
                    case 6:
                        new Navbar();
                        break;
                    default:
                        System.err.println("no problem");
                }
            }

        }
    }
}
