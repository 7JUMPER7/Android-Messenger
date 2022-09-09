package com.example.messanger.API;

import java.util.ArrayList;

import retrofit2.Call;

public class APIController {
    public static Call<ArrayList<MessageModel>> getMessages() {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);
        Call<ArrayList<MessageModel>> call = methods.getAllMessages();
        return call;
    }

    public static Call<RegisterResponse> register(String login, String password, String name) {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);

        RegisterResponse user = new RegisterResponse(login, password, name);
        Call<RegisterResponse> call = methods.registerUser(user);
        return call;
    }
}
