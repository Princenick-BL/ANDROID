package com.example.tp1.MoviesRecycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp1.Movie;
import com.example.tp1.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private  final List<Movie> movies;//Va stocquer la liste de repos qu'on lui passera en paramatre à l'instanciation

    public Adapter(List<Movie> movies) {
        this.movies = movies; //affectation a l'instantiation
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Injection de la vue de item_repos dans un view holder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Remplissage des champs du view holder avec les valeurs de la position correspondante
        Movie movie = movies.get(position);
        ImageView movieImg = holder.movieImg;
        new DownLoadImageTask(movieImg).execute("https://image.tmdb.org/t/p/w500/"+movie.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);

            } catch (Exception e) { // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
