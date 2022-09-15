package com.example.messanger.API;

import com.example.messanger.DB.UserEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
    @POST("/api/message/get")
    Call<ArrayList<MessageModel>> getAllMessages(@Body UserEntity user);

    @POST("/api/user/register")
    Call<RegisterResponse> registerUser(@Body RegisterResponse register);

    @POST("/api/user/login")
    Call<LoginResponse> loginUser(@Body LoginResponse login);

    @POST("/api/user/online")
    Call<SetOnlineResponse> setUserOnline(@Body SetOnlineResponse online);

    @POST("/api/message/send")
    Call<MessageModel> sendMessage(@Body MessageModel message);
}
