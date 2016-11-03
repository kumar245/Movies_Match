package com.mac.fireflies.wgt.moviematch.model;

import com.mac.fireflies.wgt.moviematch.api.themoviedb.PojoSearchMovie;

import java.util.HashMap;

/**
 * Created by User on 10/19/2016.
 */

public class Movie {
    public String id;
    public String posterPath;
    public String originalLanguage;
    public String originalTitle;
    public String overview;
    public String releaseDate;

    public Movie(){

    }


    public Movie(PojoSearchMovie moviebyURI) {
        id = moviebyURI.results.get(0).id.toString();
        posterPath = moviebyURI.results.get(0).posterPath;
        originalLanguage = moviebyURI.results.get(0).originalLanguage;
        originalTitle = moviebyURI.results.get(0).originalTitle;
        overview = moviebyURI.results.get(0).overview;
        releaseDate = moviebyURI.results.get(0).releaseDate;
    }

    public Movie(PojoSearchMovie.Result moviePojo) {
        id = moviePojo.id.toString();
        posterPath = moviePojo.posterPath;
        originalTitle = moviePojo.originalTitle;
        originalLanguage = moviePojo.originalLanguage;
        overview = moviePojo.overview;
        releaseDate = moviePojo.releaseDate;

    }

    public Movie(HashMap<String, String> value) {
        id = value.get("id");
        posterPath = value.get("posterPath");
        originalTitle = value.get("originalTitle");
        originalLanguage = value.get("originalLanguage");
        releaseDate = value.get("releaseDate");
        overview = value.get("overview");
    }

    public String getPath(){
        return "https://image.tmdb.org/t/p/w300_and_h450_bestv2"+posterPath;
    }
}
