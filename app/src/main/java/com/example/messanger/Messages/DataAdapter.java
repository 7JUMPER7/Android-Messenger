package com.example.messanger.Messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messanger.API.MessageModel;
import com.example.messanger.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<MessageModel> messages;
    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<MessageModel> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageModel msg = messages.get(position);

        if(msg.getUserMessage() == true) {
            holder.itemView.findViewById(R.id.otherMessageContainer).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.userMessageContainer).setVisibility(View.VISIBLE);
            holder.userMessageText.setText(msg.getText());
        } else {
            holder.itemView.findViewById(R.id.otherMessageContainer).setVisibility(View.VISIBLE);
            holder.itemView.findViewById(R.id.userMessageContainer).setVisibility(View.GONE);
            holder.otherMessageText.setText(msg.getText());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
