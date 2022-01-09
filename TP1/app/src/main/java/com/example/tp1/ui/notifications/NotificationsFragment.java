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
import com.example.tp1.MovieServices;
import com.example.tp1.Movie.Movies;
import com.example.tp1.MoviesRecycler.Adapter;
import com.example.tp1.R;
import com.example.tp1.databinding.FragmentNotificationsBinding;

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

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovies(query);
                return  false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //searchMovies(newText);
                return  false;

            }
        });

        /*notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                searchMovies();
            }
        });*/

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
                    Adapter adapter = new Adapter(response.body().getMovies());
                    rvSearch.setAdapter(adapter);
                    rvSearch.setLayoutManager(new GridLayoutManager(getContext(),2));
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