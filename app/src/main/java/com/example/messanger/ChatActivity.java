package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messanger.Messages.DataAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ChatActivity extends AppCompatActivity {
    RecyclerView messagesRecycler;
    ArrayList<String> messages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages.add("Test message 1");
        messages.add("Test message 2");
        messages.add("Test message 3");

        messagesRecycler = findViewById(R.id.messagesRecycler);
        messagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        DataAdapter adapter = new DataAdapter(this, messages);
        messagesRecycler.setAdapter(adapter);

        updateMessages();
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