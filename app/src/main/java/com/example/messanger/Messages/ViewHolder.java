package com.example.messanger.Messages;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messanger.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView message;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.userMessage);
    }
}
