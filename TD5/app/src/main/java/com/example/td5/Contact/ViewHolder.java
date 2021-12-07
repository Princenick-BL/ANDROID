package com.example.td5.Contact;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td5.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView firstNameTextView;
    public TextView lastNameTextView;
    public ImageView imageImageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        lastNameTextView = itemView.findViewById(R.id.lastName);
        firstNameTextView = itemView.findViewById(R.id.firstName);
        imageImageView = itemView.findViewById(R.id.image);
    }
}
