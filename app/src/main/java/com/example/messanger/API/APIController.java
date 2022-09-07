package com.example.messanger.API;

import java.util.ArrayList;

import retrofit2.Call;

public class APIController {
    public static Call<ArrayList<MessageModel>> getMessages() {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);
        Call<ArrayList<MessageModel>> call = methods.getAllMessages();
        return call;
    }
}
