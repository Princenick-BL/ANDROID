package com.example.td6_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.td6_v2.ReposRecycler.Adapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRepo extends AppCompatActivity {
    RecyclerView rvSearchRepos;
    Button search;
    EditText userName;
    GithubService githubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repo);

        rvSearchRepos = findViewById(R.id.rvSearchRepos);
        search = findViewById(R.id.search);
        userName = findViewById(R.id.userName);

        githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListRepos();
            }
        });

    }

    public void getListRepos(){
        githubService.searchRepos(userName.getText().toString()).enqueue(new Callback<Repos>() {
            @Override
            public void onResponse(Call<Repos> call, Response<Repos> response) {

                if(response.body()!= null) {
                    Adapter adapter = new Adapter(response.body().getItems());
                    rvSearchRepos.setAdapter(adapter);
                    rvSearchRepos.setLayoutManager(new LinearLayoutManager(SearchRepo.this));
                }else {
                    Toast.makeText(SearchRepo.this,"Aucune corresponsance trouvée", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Repos> call, Throwable t) {
                Toast.makeText(SearchRepo.this,"Une erreur s'est produit", Toast.LENGTH_LONG).show();
            }
        });
    }



}