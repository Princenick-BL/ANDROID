package com.example.tp1.Movie;

import com.google.gson.annotations.SerializedName;

public class MovieGenre {

    @SerializedName("id")
    private int genre_id;
    @SerializedName("name")
    private String genre_name;

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }


}
