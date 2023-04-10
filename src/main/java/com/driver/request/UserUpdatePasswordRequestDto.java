package com.driver.request;

public class UserUpdatePasswordRequestDto {

    private int id;
    private String password;

    public UserUpdatePasswordRequestDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
