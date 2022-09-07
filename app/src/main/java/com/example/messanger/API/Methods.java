package com.example.messanger.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET("/api/message")
    Call<ArrayList<MessageModel>> getAllMessages();
}
