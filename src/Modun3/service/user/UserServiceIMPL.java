package Modun3.service.user;

import Modun3.config.Config;
import Modun3.model.user.User;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements iUserService {
    List<User> userList = new Config<User>().readFromFile(Config.PATH_USER);

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        new Config<User>().writeFromFile(Config.PATH_USER, userList);
    }


    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return userList.get(i);
            }
        }
        return null;
    }


    @Override
    public void deleteById(int idDelete) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == idDelete) {
                userList.remove(userList.get(i));
            }
        }
    }

    @Override
    public boolean existedByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<User> userLogin = new Config<User>().readFromFile(Config.PATH_USER_LOGIN);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password)) {
                userLogin.add(userList.get(i));
                new Config<User>().writeFromFile(Config.PATH_USER_LOGIN, userLogin);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurentUser() {
        if (new Config<User>().readFromFile(Config.PATH_USER_LOGIN).size() != 0) {
            return new Config<User>().readFromFile(Config.PATH_USER_LOGIN).get(0);
        }
        return null;
    }

    @Override
    public void getLogout() {
        if (new Config<User>().readFromFile(Config.PATH_USER_LOGIN).size() != 0) {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter("D:\\MD3\\demo\\src\\Modun3\\database\\userLogin.txt");
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<User> searchByName(String name) {
        List<User> listUserSearch = new ArrayList<>();
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                listUserSearch.add(user);
            }

        }
        return listUserSearch;
    }

    @Override
    public void editStatusById(int idChange) {
        System.out.println("Enter status you want to change");
        boolean newStatus = Config.scanner().nextBoolean();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == idChange) {
                userList.get(i).setStatus(newStatus);
                break;
            }
        }
        new Config<User>().writeFromFile(Config.PATH_USER, userList);
    }

    @Override
    public void changePassword(String password) {
        findById(getCurentUser().getId()).setPassword(password);
        new Config<User>().writeFromFile(Config.PATH_USER, userList);
    }

}
