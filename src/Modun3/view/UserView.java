package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.UserController;
import Modun3.dto.request.SignInDTO;
import Modun3.dto.request.SignUpDTO;
import Modun3.dto.response.ResponseMessage;
import Modun3.model.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
//    List<User> users = new Config<User>().readFromFile(Config.PATH_USER);

    public void UserViewNavbar() {
        System.out.println("*********************************************** USER ************************************************");
        String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.format(alignFormat, 1, "Show list User", 2, "Edit status", 3, "Search user", 4, "Home");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.println("*****************************************************************************************************");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                showListUser();
                break;
            case 2:
                editStatusUser();
                break;
            case 3:
                searchUserByName();
                break;
            case 4:
               new ProfileView();
                break;
        }
    }

    public void changePassword() {
        userController.editPassword();
        new ProfileView();
    }

    public void register() {
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        boolean status = true;
        System.out.println("Enter the name: ");
        String name = Config.scanner().nextLine();
        System.out.println("Enter the username: ");
        String username;
        while (true) {
            username = Config.scanner().nextLine();
            if (Config.setValidateUserName(username)) {
                break;
            }
            System.err.println(" Invalid UserName, try again!");
        }
        System.out.println("Enter the email: ");
        String email;
        while (true) {
            email = Config.scanner().nextLine();
            if (Config.setValidateEmail(email)) {
                break;
            }
            System.err.println(" Invalid Email, try again!");
        }


        System.out.println("Enter the password: ");
        String password;
        while (true) {
            password = Config.scanner().nextLine();
            if (Config.setValidatePassword(password)) {
                break;
            }
            System.err.println(" Invalid Password, try again!");
        }

        Set<String> roleStr = new HashSet<>();
        roleStr.add("user");
        SignUpDTO sign = new SignUpDTO(id, name, username, email, password, status, roleStr);

        while (true) {
            ResponseMessage responseMessage = userController.register(sign);
            if (responseMessage.getMessage().equals("user_existed")) {
                System.err.println("user name existed!");
                username = Config.scanner().nextLine();
                sign.setUsername(username);
            } else if (responseMessage.getMessage().equals("email_existed")) {
                System.err.println("email name existed!");
                email = Config.scanner().nextLine();
                sign.setEmail(email);
            } else if (responseMessage.getMessage().equals("create_success")) {
                System.out.println("<------- Create success ------->");
                new Navbar();
                break;
            }
        }
    }

    public void formLogin() {
        System.out.println("<**** Login ****>");
        System.out.println("Enter the username: ");
        String username;
        while (true) {
            username = Config.scanner().nextLine();
            if (Config.setValidateUserName(username)) {
                break;
            }
            System.err.println(" Invalid UserName, try again!");
        }
        System.out.println("Enter the password: ");
        String password;
        while (true) {
            password = Config.scanner().nextLine();
            if (Config.setValidatePassword(password)) {
                break;
            }
            System.err.println(" Invalid Password, try again!");
        }
        SignInDTO signInDTO = new SignInDTO(username, password);
//        boolean status;
//        for (User user : users
//        ) {
//            status = user.isStatus();
//        }
        userController.login(signInDTO);
        new ProfileView();
    }


    public void showListUser() {
        System.out.println("************************************* SHOW LIST USER *************************************");
        String alignFormat = "| %-4d | %-15s | %-20s | %-10s | %-25s |%n";
        System.out.format("+------+-----------------+----------------------+------------+---------------------------+%n");
        System.out.format("|  ID  |    USER NAME    |        EMAIL         |    STATUS  |            ROLE           |%n");
        System.out.format("+------+-----------------+----------------------+------------+---------------------------+%n");
        for (User user : userList) {
            System.out.format(alignFormat,
                    user.getId(), user.getName(), user.getEmail(),
                    (user.isStatus() ? "OK" : "UNLOCK"),
                    user.getRoleSet());
        }
        System.out.format("+------+-----------------+----------------------+------------+---------------------------+%n");
        System.out.println("******************************************************************************************");
        System.out.println("Enter 'back' to return Navbar");
        String back = Config.scanner().nextLine();
        if (back.equalsIgnoreCase("back"))
            UserViewNavbar();
    }

    public void searchUserByName() {
        System.out.println(userController.searchUserByName());
        UserViewNavbar();
    }

    public void editStatusUser() {
        userController.editStatusById();
        UserViewNavbar();
    }
}
