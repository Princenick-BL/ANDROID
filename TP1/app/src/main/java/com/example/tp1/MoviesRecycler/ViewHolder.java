package com.example.tp1.MoviesRecycler;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp1.R;


public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImg;
    public  ImageView addFav;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImg = itemView.findViewById(R.id.movieImg);
        addFav =  itemView.findViewById(R.id.addFav);
    }
}
