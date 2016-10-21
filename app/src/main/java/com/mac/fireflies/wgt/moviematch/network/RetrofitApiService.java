package com.mac.fireflies.wgt.moviematch.network;

import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.PojoArtistMoviesConnection;

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
    Call<PojoArtistMoviesConnection> getMyJSON(@Url String url);
}