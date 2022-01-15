package com.example.tp1.ui.home;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp1.MainActivity;
import com.example.tp1.Movie.Movie;
import com.example.tp1.MovieServices;
import com.example.tp1.Movie.Movies;
import com.example.tp1.MoviesRecycler.Adapter;
import com.example.tp1.MoviesRecycler.EndlessScrollEventListener;
import com.example.tp1.R;
import com.example.tp1.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MovieServices movieServices;
    private RecyclerView rvPopular;
    private GridLayoutManager lm  ;
    private Resources resources;
    int page = 0;
    private List<Movie> movies = new ArrayList<>();
    private EndlessScrollEventListener endlessScrollEventListener;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setFragmentRefreshListener(new MainActivity.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                getPopular();
            }
        });

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lm = new GridLayoutManager(getContext(),2);

        movieServices = new Retrofit.Builder()
                .baseUrl(MovieServices.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieServices.class);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                getPopular();
            }
        });

        rvPopular = binding.rvPopular;

        endlessScrollEventListener = new EndlessScrollEventListener(lm) {
            @Override
            public void onLoadMore(int pageNum, RecyclerView recyclerView) {
                /*Todo: add your request call to load more data from server or database here */
                getLoadMorePopular();
            }
        };

        rvPopular.addOnScrollListener(endlessScrollEventListener);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getPopular(){
        page= 1;
        resources = getContext().getResources();
        String lang = resources.getString(R.string.language);

        movieServices.getPopularMovies(lang,page).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if(response.body()!= null) {
                    movies.clear();
                    movies.addAll(response.body().getMovies());
                    Adapter adapter = new Adapter(movies);
                    rvPopular.setAdapter(adapter);
                    rvPopular.setLayoutManager(lm);
                }else {
                    Toast.makeText(getContext(),"Aucune corresponsance trouvée", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(getContext(),"Une erreur s'est produit", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getLoadMorePopular(){

        resources = getContext().getResources();
        String lang = resources.getString(R.string.language);
        page ++;
        movieServices.getPopularMovies(lang,page).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if(response.body()!= null) {
                    movies.addAll(response.body().getMovies());
                }else {
                    Toast.makeText(getContext(),"Aucune corresponsance trouvée", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(getContext(),"Une erreur s'est produit", Toast.LENGTH_LONG).show();
            }
        });
    }


}