package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messanger.API.APIController;
import com.example.messanger.API.RegisterResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view) {
        TextView nameText = findViewById(R.id.nameInput);
        TextView loginText = findViewById(R.id.loginInput);
        TextView passwordText = findViewById(R.id.passwordInput);

        Call<RegisterResponse> call = APIController.register(loginText.getText().toString(), passwordText.getText().toString(), nameText.getText().toString());
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();

                if(registerResponse == null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<RegisterResponse>() {}.getType();
                    registerResponse = gson.fromJson(response.errorBody().charStream(), type);
                    Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(RegisterActivity.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                Log.d("TEST", "onResponse: " + registerResponse.getName());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("REGISTER", "onFailure: " + t.getMessage());
            }
        });

        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        main.putExtra("from", "register");
        startActivity(main);
    }
}