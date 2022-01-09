package com.example.tp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.Movie.Movie;
import com.example.tp1.Movie.MovieDetails;
import com.example.tp1.Movie.MovieGenre;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Details extends AppCompatActivity {
    private MovieServices movieServices;
    private ImageView movieDetailsImage ;
    private TextView description;
    private TextView genreText;
    private TextView realease;
    String genres = "";
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle("Details");



        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("movieId",0);

        movieDetailsImage = findViewById(R.id.movieDetailsImage);
        description = findViewById(R.id.description);
        genreText = findViewById(R.id.genres);
        realease = findViewById(R.id.release);

        movieServices = new Retrofit.Builder()
                .baseUrl(MovieServices.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieServices.class);
        resources = getBaseContext().getResources();
        String lang = resources.getString(R.string.language);

        movieServices.getMovieInfo(movieId,lang,1).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                if(response.body()!= null) {
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/"+response.body().getImageUrl()).into(movieDetailsImage);
                    description.setText(response.body().getDescription());
                    realease.setText(response.body().getReleaseDate());
                    for (MovieGenre genre:response.body().getGenres()) {
                        genres = genres +"\n-"+genre.getGenre_name()+"\n";
                    }
                    genreText.setText(genres);

                }else {
                    Toast.makeText(Details.this,"Aucune corresponsance trouvée", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Toast.makeText(Details.this,"Une erreur s'est produit", Toast.LENGTH_LONG).show();
            }
        });
    }
}