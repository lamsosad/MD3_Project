package Modun3.dto.request;

import java.util.HashSet;
import java.util.Set;

public class SignUpDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean status;
    private Set<String> roleStr = new HashSet<>();

    public SignUpDTO() {
    }

    public SignUpDTO(int id, String name, String username, String email, String password,boolean status, Set<String> roleStr) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleStr = roleStr;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(Set<String> roleStr) {
        this.roleStr = roleStr;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
