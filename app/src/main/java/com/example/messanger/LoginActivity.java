package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.messanger.DB.DatabaseAdapter;
import com.example.messanger.DB.UserEntity;

public class LoginActivity extends AppCompatActivity {
    DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adapter = new DatabaseAdapter(this);
    }

    public void login(View view) {
        adapter.open();
        UserEntity user = adapter.getEntity();
        adapter.close();

        Toast.makeText(getApplicationContext(), user.getPassword(), Toast.LENGTH_LONG).show();

        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        main.putExtra("from", "login");
        startActivity(main);
    }

    public void register(View view) {
        Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(register);
    }
}