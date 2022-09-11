package com.example.messanger.Messages;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messanger.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView otherMessageText;
    TextView userMessageText;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        otherMessageText = itemView.findViewById(R.id.otherMessageText);
        userMessageText = itemView.findViewById(R.id.userMessageText);
    }
}
