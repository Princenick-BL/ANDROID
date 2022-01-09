package com.example.tp1.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.tp1.MovieServices;
import com.example.tp1.Movie.Movies;
import com.example.tp1.MoviesRecycler.Adapter;
import com.example.tp1.R;
import com.example.tp1.databinding.FragmentDashboardBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private MovieServices movieServices;
    public RecyclerView rvUpcomming;
    Resources resources;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setFragmentRefreshListener(new MainActivity.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                getUpcomming();
            }
        });

        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        movieServices = new Retrofit.Builder()
                .baseUrl(MovieServices.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieServices.class);

        rvUpcomming = binding.rvUpcomming;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                getUpcomming();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getUpcomming(){

        resources = getContext().getResources();
        String lang = resources.getString(R.string.language);

        movieServices.getUpcomingMovies(lang,1).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if(response.body()!= null) {
                    Adapter adapter = new Adapter(response.body().getMovies());
                    rvUpcomming.setAdapter(adapter);
                    rvUpcomming.setLayoutManager(new GridLayoutManager(getContext(),2));
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