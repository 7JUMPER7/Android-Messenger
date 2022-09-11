package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.messanger.DB.DatabaseAdapter;
import com.example.messanger.DB.UserEntity;

public class MainActivity extends AppCompatActivity {
    private boolean isFirstLoad = true;
    private DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new DatabaseAdapter(this);

        adapter.open();
        UserEntity user = adapter.getEntity();
        adapter.close();

        if(user != null) {
            Intent chat = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(chat);
        } else {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            isFirstLoad = false;
            startActivity(login);
        }
    }
}