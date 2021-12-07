package com.example.td6_v2.ReposRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td6_v2.R;
import com.example.td6_v2.Repo;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private  final List<Repo> repos;//Va stocquer la liste de repos qu'on lui passera en paramatre à l'instanciation

    public Adapter(List<Repo> repos) {
        this.repos = repos; //affectation a l'instantiation
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Injection de la vue de item_repos dans un view holder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_repos, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Remplissage des champs du view holder avec les valeurs de la position correspondante
        Repo repo = repos.get(position);
        TextView repoName = holder.repoName;
        repoName.setText(repo.getFull_name());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}
