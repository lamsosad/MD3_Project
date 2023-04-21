package Modun3.dto.request;

import java.io.Serializable;

public class UserOrderDTO implements Serializable {
    private String name;
    private String UserName;
    private String phone;
    private String address;

    public UserOrderDTO() {
    }

    public UserOrderDTO(String name, String userName, String phone, String address) {
        this.name = name;
        UserName = userName;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
