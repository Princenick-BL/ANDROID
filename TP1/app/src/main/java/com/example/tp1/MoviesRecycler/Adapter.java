package com.example.tp1.MoviesRecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp1.MainActivity;
import com.example.tp1.Movie.Movie;
import com.example.tp1.Details;
import com.example.tp1.MyApplication;
import com.example.tp1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    public Context context;
    private MyApplication globalClass;
    private List<Movie> favMovies;

    private  final List<Movie> movies;//Va stocquer la liste de repos qu'on lui passera en paramatre à l'instanciation

    public Adapter(List<Movie> movies) {
        this.movies = movies; //affectation a l'instantiation
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Injection de la vue de item_repos dans un view holder
        context = parent.getContext();
        globalClass = (MyApplication) context.getApplicationContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Remplissage des champs du view holder avec les valeurs de la position correspondante
        Movie movie = movies.get(position);
        ImageView movieImg = holder.movieImg;
        //movieImg.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w500/"+movie.getImageUrl()));
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getImageUrl()).into(movieImg);
        //new DownLoadImageTask(movieImg).execute("https://image.tmdb.org/t/p/w500/"+movie.getImageUrl());
        holder.movieImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Details.class);
                intent.putExtra("movieId",movie.getMovie_id());
                view.getContext().startActivity(intent);

                //You can call detail fragment here
            }
        });
        holder.addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //here pour récupérer le ids du movie
                favMovies = globalClass.getMovies();
                boolean exist = false;
                for (int counter = 0; counter < favMovies.size(); counter++) {
                    if(favMovies.get(counter).getMovie_id() == movie.getMovie_id()){
                        exist=true;
                    }
                }
                if(exist){
                    Toast.makeText(v.getContext(), "Movie already exists in fav", Toast.LENGTH_SHORT).show();
                }else{
                    favMovies.add(movie);
                    Toast.makeText(v.getContext(), "ADD TO FAV", Toast.LENGTH_SHORT).show();
                }
                /*
                if(!(favMovies.contains(movie))){
                    favMovies.add(movie);
                }else {
                    Toast.makeText(v.getContext(), "Movie already exists in fav", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView movieImg;
        public  ImageView addFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.movieImg);
            addFav =  itemView.findViewById(R.id.addFav);


        }


    }

}
