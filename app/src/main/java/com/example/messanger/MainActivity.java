package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private boolean isFirstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();

//        if(extras != null) {
//
//        } else {
//            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
//            isFirstLoad = false;
//            startActivity(login);
//        }

        Intent chat = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(chat);
    }
}