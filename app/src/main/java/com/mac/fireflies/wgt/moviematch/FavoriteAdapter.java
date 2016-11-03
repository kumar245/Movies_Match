package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.mac.fireflies.wgt.moviematch.model.DatabaseUserUtil;
import com.mac.fireflies.wgt.moviematch.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/2/2016.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    List<Movie> movies;
    Context context;

    public FavoriteAdapter(Context context, FirebaseUser user){
        movies = new ArrayList<>();
        DatabaseUserUtil.getFavoritesMovies(user, movies);
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_favorites, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(movies.get(position).originalTitle);
        Glide.with(context)
                .load(movies.get(position).getPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.favorite_textView);
            imageView = (ImageView) itemView.findViewById(R.id.favorite_imageView);
    }
    }
}
