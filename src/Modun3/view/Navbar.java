package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.UserController;
import Modun3.model.user.User;


public class Navbar {
    UserController userController = new UserController();

    public Navbar() {
        User user = userController.getUserLogin();
        if (user != null) {
            System.out.println("<Welcom " + user.getName() + " to shop>");
            String alignFormat = "| %-4d | %-15s | %-4d | %-15s |%n";
            System.out.format("+------+-----------------+------+-----------------+%n");
            System.out.format(alignFormat, 1, "   Go to shop", 2, "   Log out");
            System.out.format("+------+-----------------+------+-----------------+%n");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu) {
                case 1:
                    new ProfileView();
                    break;
                case 2:
                    userController.getLogOut();
                    new Navbar();
                    break;
                default:
                    System.err.println("Lỗi tý");

            }
        } else {
            System.out.println("******************************* APPLE STORE ********************************");
            String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
            System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
            System.out.format(alignFormat, 1, "  Register", 2, "   Login", 3, "   Product");
            System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
            System.out.println("****************************************************************************");
            int chosceMenu = Config.scanner().nextInt();
            switch (chosceMenu) {
                case 1:
                    new UserView().register();
                    break;
                case 2:
                    new UserView().formLogin();
                    break;
                case 3:
                    new ProductView().showProduct();
                    new Navbar();
                    break;
            }
        }

    }

    public static void main(String[] args) {
        new Navbar();

    }
}
