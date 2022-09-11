package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messanger.API.APIController;
import com.example.messanger.API.MessageModel;
import com.example.messanger.API.SetOnlineResponse;
import com.example.messanger.DB.DatabaseAdapter;
import com.example.messanger.DB.UserEntity;
import com.example.messanger.Messages.DataAdapter;
import com.example.messanger.Sidebar.SidebarFragment;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    RecyclerView messagesRecycler;
    ArrayList<MessageModel> messages = new ArrayList<MessageModel>();
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // set drawable icon
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set database adapter for user info
        databaseAdapter = new DatabaseAdapter(this);

        databaseAdapter.open();
        UserEntity user = databaseAdapter.getEntity();
        databaseAdapter.close();

        // get chat messages
        Call<ArrayList<MessageModel>> call = APIController.getMessages(user);
        call.enqueue(new Callback<ArrayList<MessageModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MessageModel>> call, Response<ArrayList<MessageModel>> response) {
                ArrayList<MessageModel> data = response.body();

                if(data != null) {
                    for(MessageModel message : data) {
                        messages.add(message);
                    }
                    updateMessages();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MessageModel>> call, Throwable t) {
                Log.e("ERROR", "onFailure: " + t.getMessage());
            }
        });

        messagesRecycler = findViewById(R.id.messagesRecycler);
        messagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        DataAdapter adapter = new DataAdapter(this, messages);
        messagesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                SidebarFragment sidebar = (SidebarFragment) getSupportFragmentManager().findFragmentByTag("Sidebar");

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if(sidebar == null) {
                    databaseAdapter.open();
                    UserEntity user = databaseAdapter.getEntity();
                    databaseAdapter.close();

                    ft.setCustomAnimations(R.anim.hotizontal_in, R.anim.hotizontal_in, R.anim.horizontal_out, R.anim.horizontal_out);
                    sidebar = SidebarFragment.newInstance(user.getName());
                    ft.add(R.id.chatContainer, sidebar, "Sidebar").addToBackStack("charContainer");
                } else {
                    ft.setCustomAnimations(R.anim.hotizontal_in, R.anim.horizontal_out);
                    if(sidebar.isVisible()) {
                        ft.hide(sidebar);
                    } else {
                        ft.show(sidebar);
                    }
                }
                ft.commit();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public void updateMessages() {
        messagesRecycler.getAdapter().notifyDataSetChanged();
        messagesRecycler.smoothScrollToPosition(messages.size());
    }

    public void sendMessage(View view) {
        EditText textInput = findViewById(R.id.textInput);
        String message = textInput.getText().toString();
        if(message.equals("")) {
            Toast.makeText(this, "Write the message first", Toast.LENGTH_LONG).show();
            return;
        }

        MessageModel newMessage = new MessageModel();
        newMessage.setText(message);

        messages.add(newMessage);
        textInput.setText("");
        updateMessages();
    }

    public void logout(View view) {
        databaseAdapter.open();
        UserEntity user = databaseAdapter.getEntity();
        databaseAdapter.delete(user.getLogin());
        databaseAdapter.close();

        if (user != null) {
            Call<SetOnlineResponse> call = APIController.setOnline(false, user.getLogin());
            call.enqueue(new Callback<SetOnlineResponse>() {
                @Override
                public void onResponse(Call<SetOnlineResponse> call, Response<SetOnlineResponse> response) { }

                @Override
                public void onFailure(Call<SetOnlineResponse> call, Throwable t) {
                    Log.d("ERROR", "setOnline: " + t.getMessage());
                }
            });
        }

        Intent login = new Intent(getApplicationContext(), MainActivity.class);
        login.putExtra("from", "chat");
        startActivity(login);
    }

    @Override
    protected void onPause() {
        super.onPause();

        databaseAdapter.open();
        UserEntity user = databaseAdapter.getEntity();
        databaseAdapter.close();

        if (user != null) {
            Call<SetOnlineResponse> call = APIController.setOnline(false, user.getLogin());
            call.enqueue(new Callback<SetOnlineResponse>() {
                @Override
                public void onResponse(Call<SetOnlineResponse> call, Response<SetOnlineResponse> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<SetOnlineResponse> call, Throwable t) {
                    Log.d("ERROR", "onPause: " + t.getMessage());
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        databaseAdapter.open();
        UserEntity user = databaseAdapter.getEntity();
        databaseAdapter.close();

        if (user != null) {
            Call<SetOnlineResponse> call = APIController.setOnline(true, user.getLogin());
            call.enqueue(new Callback<SetOnlineResponse>() {
                @Override
                public void onResponse(Call<SetOnlineResponse> call, Response<SetOnlineResponse> response) { }

                @Override
                public void onFailure(Call<SetOnlineResponse> call, Throwable t) {
                    Log.d("ERROR", "onResume: " + t.getMessage());
                }
            });
        }
    }
}