package com.example.messanger.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIController {
    public static Call<Model> getNames() {
        Methods methods = RetrofitClient.getInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();
        return call;
    }
}
