package com.example.messanger.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
    @GET("/api/message")
    Call<ArrayList<MessageModel>> getAllMessages();

    @POST("/api/user/register")
    Call<RegisterResponse> registerUser(@Body RegisterResponse register);
}
