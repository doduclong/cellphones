package com.example.cellphones.dto.request.user;

public class CreateUserReq {
    private String username;
    private String password;

    public CreateUserReq() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
