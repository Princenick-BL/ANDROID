package com.example.tp1.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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
import com.example.tp1.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private MovieServices movieServices;
    private RecyclerView rvSearch;
    private String searchInput;
    private SearchView search;
    Resources resources;
    int page = 0;
    private GridLayoutManager lm  ;
    private List<Movie> movies = new ArrayList<>();
    private EndlessScrollEventListener endlessScrollEventListener;
    private String queryString="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        movieServices = new Retrofit.Builder()
                .baseUrl(MovieServices.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieServices.class);

        rvSearch = binding.rvSearch;
        search = binding.search;
        lm = new GridLayoutManager(getContext(),2);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryString=query;
                searchMovies(query);
                return  false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //searchMovies(newText);
                return  false;

            }
        });
        endlessScrollEventListener = new EndlessScrollEventListener(lm) {
            @Override
            public void onLoadMore(int pageNum, RecyclerView recyclerView) {
                /*Todo: add your request call to load more data from server or database here */
                getMoreMovies();
            }
        };

        rvSearch.addOnScrollListener(endlessScrollEventListener);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void searchMovies(String query){
        resources = getContext().getResources();
        String lang = resources.getString(R.string.language);

        movieServices.searchMovie(lang,query,1).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if(response.body()!= null) {
                    movies.clear();
                    movies.addAll(response.body().getMovies());
                    Adapter adapter = new Adapter(movies);
                    rvSearch.setAdapter(adapter);
                    rvSearch.setLayoutManager(lm);
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
    public void getMoreMovies(){

        resources = getContext().getResources();
        String lang = resources.getString(R.string.language);
        page++;

        movieServices.searchMovie(lang,queryString,page).enqueue(new Callback<Movies>() {
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