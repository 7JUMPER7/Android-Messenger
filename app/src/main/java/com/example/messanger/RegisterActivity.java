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
import com.example.messanger.DB.DatabaseAdapter;
import com.example.messanger.DB.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        adapter = new DatabaseAdapter(this);
    }

    public void register(View view) {
        TextView nameText = findViewById(R.id.nameInput);
        TextView loginText = findViewById(R.id.loginInput);
        TextView passwordText = findViewById(R.id.passwordInput);

        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        String name = nameText.getText().toString();

        if(login.equals("") || password.equals("") || name.equals("")) {
            Toast.makeText(getApplicationContext(), "All fields should be passed!", Toast.LENGTH_LONG).show();
            return;
        }

        Call<RegisterResponse> call = APIController.register(login, password, name);
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

                UserEntity newUser = new UserEntity(registerResponse.getName(), registerResponse.getLogin(), registerResponse.getPassword());

                // Save to DB
                adapter.open();
                adapter.insert(newUser);
                adapter.close();

                Toast.makeText(RegisterActivity.this, "Successfully registered!", Toast.LENGTH_LONG).show();

                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                main.putExtra("from", "register");
                startActivity(main);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("REGISTER", "onFailure: " + t.getMessage());
            }
        });
    }
}