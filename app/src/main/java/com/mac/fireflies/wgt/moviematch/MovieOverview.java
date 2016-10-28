package com.mac.fireflies.wgt.moviematch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mac.fireflies.wgt.moviematch.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieOverview extends AppCompatActivity {

//    Array of strings
String[] categories = {"Action","Romance","Adventure","Kids","Animation"};
    Movie movie = new Movie();
    ImageView imageView;
    TextView MovieDetails;
    TextView Title;
    TextView OverView;
    TextView Language;
    TextView AddtoCategory;
    Button Add;


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

        //        Array Adapter

        List<String> cato = new ArrayList<>();
        cato.add(categories[0]);
        cato.add(categories[1]);
        cato.add(categories[2]);
        cato.add(categories[3]);
        cato.add(categories[4]);


        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.movie_listview, categories);

//        creating a list view
        ListView listView=(ListView) findViewById(R.id.categorylist);
        List_Adapter list_adapter = new List_Adapter(MovieOverview.this,R.layout.activity_movie_overview,cato);
        listView.setAdapter(list_adapter);

        Add=(Button) findViewById(R.id.add);
        movie.id = "65655";
        movie.posterPath="http://cdn.collider.com/wp-content/uploads/amazing-spider-man-movie-poster.jpg";
        movie.originalLanguage="English";
        movie.originalTitle="SPIDER MAN";
        movie.overview="Description";

List<Movie> mov=new ArrayList<>();
        mov.add(movie);
        mov.add(movie);
        mov.add(movie);
        mov.add(movie);
        mov.add(movie);
        mov.add(movie);
        mov.add(movie);



        Title.setText(movie.originalTitle);
        Language.setText(movie.originalLanguage);
        OverView.setText(movie.id);
        AddtoCategory.setText(movie.overview);

        Glide
                .with(this)
                .load(movie.posterPath)
                .into(imageView);
    }
    public void setMovie(Movie movie){
        this.movie = movie;
    }
}
