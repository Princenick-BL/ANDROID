package com.example.tp1;

import android.app.Application;

import com.example.tp1.Movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private List<Movie> favMovies = new ArrayList<>();

    public List<Movie> getMovies() {
        return favMovies;
    }

    public void setMovies(List<Movie> movies) {
        this.favMovies = movies;
    }
}