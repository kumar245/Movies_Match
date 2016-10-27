package com.mac.fireflies.wgt.moviematch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mac.fireflies.wgt.moviematch.model.Movie;

public class MovieOverview extends AppCompatActivity {
    Movie movie = new Movie();
    ImageView imageView;
    TextView MovieDetails;
    TextView Title;
    TextView OverView;
    TextView Language;
    TextView AddtoCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);
        MovieDetails=(TextView) findViewById(R.id.moviedetails);
        Title=(TextView) findViewById(R.id.titl);
        OverView=(TextView) findViewById(R.id.overview);
        Language=(TextView) findViewById(R.id.language);
        AddtoCategory=(TextView) findViewById(R.id.addtocategory);
        imageView=(ImageView) findViewById(R.id.imageView2);

        movie.imdbId = "65655";
        movie.posterPath="http://cdn.collider.com/wp-content/uploads/amazing-spider-man-movie-poster.jpg";
        movie.originalLanguage="English";
        movie.originalTitle="SPIDER MAN";
        movie.overview="Description";

        Title.setText(movie.originalTitle);
        Language.setText(movie.originalLanguage);
        OverView.setText(movie.imdbId);

        Glide
                .with(this)
                .load(movie.posterPath)
                .into(imageView);
    }
    public void setMovie(Movie movie){
        this.movie = movie;
    }
}
