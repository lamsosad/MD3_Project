package Modun3.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config<E> {
    public static Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public static final String PATH_CATALOG = "D:\\MD3\\demo\\src\\Modun3\\database\\catalog.txt";
    public static final String PATH_USER = "D:\\MD3\\demo\\src\\Modun3\\database\\user.txt";
    public static final String PATH_USER_LOGIN = "D:\\MD3\\demo\\src\\Modun3\\database\\userLogin.txt";
    public static final String PATH_PRODUCT = "D:\\MD3\\demo\\src\\Modun3\\database\\product.txt";
    public static  final String PATH_CART = "D:\\MD3\\demo\\src\\Modun3\\database\\cart.txt";
    public static  final String PATH_CART_ODER = "D:\\MD3\\demo\\src\\Modun3\\database\\oder.txt";
    public static  final String PATH_COMMENT = "D:\\MD3\\demo\\src\\Modun3\\database\\comment.txt";

    public List<E> readFromFile(String PATH_FILE) {
        List<E> eList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            eList = (List<E>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (Exception e) {

        }
        return eList;
    }

    public void writeFromFile(String PATH_FILE, List<E> eList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(eList);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {

        }
    }

    public static final String validateEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    public static final String validatePhone = "^0(\\d){9}$";
    public static final String validateUserName = "^[a-z0-9._-]{6,15}$";
    public static final String validatePassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";


    public static boolean setValidateEmail(String email) {
        Pattern pattern = Pattern.compile(validateEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean setValidatePhone(String phone) {
        Pattern pattern = Pattern.compile(validatePhone);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    public static boolean setValidateUserName(String username) {
        Pattern pattern = Pattern.compile(validateUserName);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean setValidatePassword(String password) {
        Pattern pattern = Pattern.compile(validatePassword);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

