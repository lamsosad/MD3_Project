package Modun3.controller;

import Modun3.config.Config;
import Modun3.dto.request.SignInDTO;
import Modun3.dto.request.SignUpDTO;
import Modun3.dto.response.ResponseMessage;
import Modun3.model.user.Role;
import Modun3.model.user.RoleName;
import Modun3.model.user.User;
import Modun3.service.role.RoleServiceIMPL;
import Modun3.service.role.iRoleService;
import Modun3.service.user.UserServiceIMPL;
import Modun3.service.user.iUserService;
import java.io.FileNotFoundException;
import java.util.*;

public class UserController {
    public iUserService userService = new UserServiceIMPL();
    public iRoleService roleService = new RoleServiceIMPL();

    public ResponseMessage register(SignUpDTO sign) {
        if (userService.existedByUsername(sign.getUsername())) {
            return new ResponseMessage("user_existed");
        }
        if (userService.existedByEmail(sign.getEmail())) {
            return new ResponseMessage("email_existed");
        }
        Set<Role> roleSet = new HashSet<>();
        Set<String> roleStr = sign.getRoleStr();
        roleStr.forEach(role -> {
            switch (role) {
                case "admin":
                    roleSet.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByName(RoleName.PM));
                    break;
                default:
                    roleSet.add(roleService.findByName(RoleName.USER));
            }
        });
        User user = new User(sign.getId(), sign.getName(), sign.getUsername(), sign.getEmail(), sign.getPassword(), sign.isStatus(), roleSet);
        userService.save(user);
        return new ResponseMessage("create_success");
    }

    public List<User> getListUser() {
        return userService.findAll();
    }

    public ResponseMessage login(SignInDTO signInDTO) {
        if (userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessage("login_success");
        } else {
            return new ResponseMessage("login_fail");
        }
    }

    public User getUserLogin() {
        return userService.getCurentUser();
    }

    public void getLogOut() {
        try {
            userService.getLogout();
            System.out.println("Good bye! See you again");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void editStatusById() {
        System.out.println("Enter ID status you want to change");
        int idStatus = Config.scanner().nextInt();
        userService.editStatusById(idStatus);
    }

    public void editPassword() {
        while (true) {
            System.out.println("Enter old password you want to change");
            String password = Config.scanner().nextLine();
            if (getUserLogin().getPassword().equals(password)) {
                String newPassword;
                while (true) {
                    System.out.println("Enter new password you want to change");
                    newPassword = Config.scanner().nextLine();
                    if (Config.setValidatePassword(newPassword)) {
                        break;
                    }
                    System.err.println(" Invalid Password, try again!");
                }
                userService.changePassword(newPassword);
                System.out.println("Change success!");
                break;
            }
        }

    }

    public List<User> searchUserByName() {
        System.out.println("Enter the username you want to search");
        String searchName = Config.scanner().nextLine();
        return userService.searchByName(searchName);
    }
}
