package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messanger.API.APIController;
import com.example.messanger.API.LoginResponse;
import com.example.messanger.API.RegisterResponse;
import com.example.messanger.API.SetOnlineResponse;
import com.example.messanger.DB.DatabaseAdapter;
import com.example.messanger.DB.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adapter = new DatabaseAdapter(this);
    }

    public void login(View view) {
        EditText loginText = findViewById(R.id.loginInput);
        EditText passwordText = findViewById(R.id.passwordInput);

        Call<LoginResponse> call = APIController.login(loginText.getText().toString(), passwordText.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();

                if(result == null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<LoginResponse>() {}.getType();
                    result = gson.fromJson(response.errorBody().charStream(), type);
                    Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                UserEntity newUser = new UserEntity(result.getName(), result.getLogin(), result.getPassword());

                // Save to DB
                adapter.open();
                adapter.insert(newUser);
                adapter.close();

                if (newUser != null) {
                    Call<SetOnlineResponse> setOnlineCall = APIController.setOnline(true, newUser.getLogin());
                    setOnlineCall.enqueue(new Callback<SetOnlineResponse>() {
                        @Override
                        public void onResponse(Call<SetOnlineResponse> call, Response<SetOnlineResponse> response) { }

                        @Override
                        public void onFailure(Call<SetOnlineResponse> call, Throwable t) {
                            Log.d("ERROR", "setOnline: " + t.getMessage());
                        }
                    });
                }

                Toast.makeText(LoginActivity.this,  "Welcome " + newUser.getName() + "!", Toast.LENGTH_LONG).show();

                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                main.putExtra("from", "login");
                startActivity(main);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("REGISTER", "onFailure: " + t.getMessage());
            }
        });
    }

    public void register(View view) {
        Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(register);
    }
}