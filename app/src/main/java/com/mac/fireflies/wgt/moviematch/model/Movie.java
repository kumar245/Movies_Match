package com.mac.fireflies.wgt.moviematch.model;

import com.mac.fireflies.wgt.moviematch.api.themoviedb.PojoSearchMovie;

/**
 * Created by User on 10/19/2016.
 */

public class Movie {
    public String id;
    public String posterPath;
    public String originalLanguage;
    public String originalTitle;
    public String overview;

    public Movie(){

    }


    public Movie(PojoSearchMovie moviebyURI) {
        id = moviebyURI.results.get(0).id.toString();
        posterPath = moviebyURI.results.get(0).posterPath;
        originalLanguage = moviebyURI.results.get(0).originalLanguage;
        originalTitle = moviebyURI.results.get(0).originalTitle;
        overview = moviebyURI.results.get(0).overview;
    }
    public String getPath(){
        return "https://image.tmdb.org/t/p/w300_and_h450_bestv2"+posterPath;
    }
}
