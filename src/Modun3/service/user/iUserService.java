package Modun3.service.user;

import Modun3.model.user.User;
import Modun3.service.iGenericService;

import java.io.FileNotFoundException;
import java.util.List;

public interface iUserService extends iGenericService<User> {
    boolean existedByUsername(String username);

    boolean existedByEmail(String email);

    boolean checkLogin(String username, String password);

    User getCurentUser();

    void getLogout() throws FileNotFoundException;

    List<User> searchByName(String name);

    void editStatusById(int id);

    void changePassword(String password);


}
