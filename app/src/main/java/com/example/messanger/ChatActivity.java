package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messanger.API.APIController;
import com.example.messanger.API.Methods;
import com.example.messanger.API.Model;
import com.example.messanger.Messages.DataAdapter;
import com.example.messanger.Sidebar.SidebarFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    RecyclerView messagesRecycler;
    ArrayList<String> messages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        messages.add("Test message 1");
//        messages.add("Test message 2");
//        messages.add("Test message 3");

        Call<Model> call = APIController.getNames();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                ArrayList<Model.data> data = response.body().getData();

                for(Model.data elem : data) {
                    messages.add(elem.getFirst_name());
                }

                updateMessages();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
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
                    ft.setCustomAnimations(R.anim.hotizontal_in, R.anim.hotizontal_in, R.anim.horizontal_out, R.anim.horizontal_out);
                    sidebar = SidebarFragment.newInstance();
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
        messages.add(message);
        textInput.setText("");
        updateMessages();
    }
}