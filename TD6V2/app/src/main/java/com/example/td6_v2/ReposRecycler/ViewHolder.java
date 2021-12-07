package com.example.td6_v2.ReposRecycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td6_v2.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView repoName;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        repoName = itemView.findViewById(R.id.repoName);
    }
}
