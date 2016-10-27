package com.mac.fireflies.wgt.moviematch.network;

import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.PojoArtistMoviesConnection;
import com.mac.fireflies.wgt.moviematch.api.themoviedb.PojoMovie;
import com.mac.fireflies.wgt.moviematch.api.themoviedb.PojoSearchMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by User on 10/19/2016.
 */
public interface RetrofitApiService {
    /*
   Retrofit get annotation with our URL
   And our method that will return us the List of ContactList
   */
    @GET
    Call<PojoArtistMoviesConnection> getOracleofBaconJSON(@Url String url);

    @GET
    Call<PojoMovie> getTheMovieDBJSON(@Url String url);

    @GET
    Call<PojoSearchMovie> getSeachMovieJSON(@Url String url);
}