package com.example.messanger.API;

public class SetOnlineResponse {
    Boolean isOnline;
    String login;

    public SetOnlineResponse(Boolean isOnline, String login) {
        this.isOnline = isOnline;
        this.login = login;
    }

    public Boolean getOnline() {
        return isOnline;
    }
    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
