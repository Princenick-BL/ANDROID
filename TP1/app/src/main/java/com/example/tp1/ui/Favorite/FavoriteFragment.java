package com.example.tp1.ui.Favorite;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp1.Movie.Movie;
import com.example.tp1.MovieServices;
import com.example.tp1.MoviesRecycler.Adapter;
import com.example.tp1.MyApplication;
import com.example.tp1.R;
import com.example.tp1.databinding.FavoriteFragmentBinding;
import com.example.tp1.databinding.FragmentHomeBinding;
import com.example.tp1.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;
    private FavoriteFragmentBinding binding;
    private RecyclerView rvFav;
    private GridLayoutManager lm;
    private List<Movie> movies = new ArrayList<>();
    private MyApplication globalClass;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding = FavoriteFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvFav = binding.rvFav;
        lm = new GridLayoutManager(getContext(),2);
        globalClass = (MyApplication) getContext().getApplicationContext();
        movies.addAll(globalClass.getMovies());
        favoriteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Adapter adapter = new Adapter(movies);
                rvFav.setAdapter(adapter);
                rvFav.setLayoutManager(lm);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}