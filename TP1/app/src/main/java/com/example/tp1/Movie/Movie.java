package com.example.tp1.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @SerializedName("id")
    private int movie_id;
    @SerializedName("poster_path")
    private String imageUrl;
    @SerializedName("overview")
    private String description ;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("gender_ids")
    private List<Integer> genderIds = new ArrayList<>();


    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenderIds() {
        return genderIds;
    }

    public void setGenderIds(List<Integer> genderIds) {
        this.genderIds = genderIds;
    }
}
