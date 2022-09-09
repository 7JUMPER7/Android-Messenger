package com.example.messanger.API;

public class MessageModel {
    String id;
    String text;
    String createdAt;
    String updatedAt;
    String userId;
    User user;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public class User {
        String name;
        String login;
        Boolean isOnline;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getLogin() {
            return login;
        }
        public void setLogin(String login) {
            this.login = login;
        }

        public Boolean getOnline() {
            return isOnline;
        }
        public void setOnline(Boolean online) {
            isOnline = online;
        }
    }
}