package com.example.messanger.API;

import com.example.messanger.DB.UserEntity;

import java.util.ArrayList;

import retrofit2.Call;

public class APIController {
    public static Call<ArrayList<MessageModel>> getMessages(UserEntity user) {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);
        Call<ArrayList<MessageModel>> call = methods.getAllMessages(user);
        return call;
    }

    public static Call<RegisterResponse> register(String login, String password, String name) {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);

        RegisterResponse user = new RegisterResponse(login, password, name);
        Call<RegisterResponse> call = methods.registerUser(user);
        return call;
    }

    public static Call<LoginResponse> login(String login, String password) {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);

        LoginResponse user = new LoginResponse(login, password);
        Call<LoginResponse> call = methods.loginUser(user);
        return call;
    }

    public static Call<SetOnlineResponse> setOnline(Boolean isOnline, String login) {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);

        SetOnlineResponse online = new SetOnlineResponse(isOnline, login);
        Call<SetOnlineResponse> call = methods.setUserOnline(online);
        return call;
    }

    public static Call<MessageModel> sendMessage(MessageModel message) {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);

        Call<MessageModel> call = methods.sendMessage(message);
        return call;
    }
}
