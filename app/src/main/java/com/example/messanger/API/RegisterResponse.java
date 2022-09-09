package com.example.messanger.API;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    String id;
    String login;
    String password;
    String name;
    Boolean inOnline;
    String updatedAt;
    String createdAt;
    public String message;

    public RegisterResponse(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInOnline() {
        return inOnline;
    }
    public void setInOnline(Boolean inOnline) {
        this.inOnline = inOnline;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
