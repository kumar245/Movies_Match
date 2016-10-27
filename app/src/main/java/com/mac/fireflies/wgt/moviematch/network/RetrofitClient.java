package com.mac.fireflies.wgt.moviematch.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 10/19/2016.
 */

public class RetrofitClient {
    private static final String BASE_ORACLE_OF_BACON = "http://oracleofbacon.org";
    private static final String BASE_THE_MOVIE_DB = "https://api.themoviedb.org/3/search/";
    /**
     * Get Retrofit Instance
     */
    private static Retrofit getOracleofBaconInstance() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(BASE_ORACLE_OF_BACON)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }
    private static Retrofit getTheMovieDBInstance() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(BASE_THE_MOVIE_DB)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static RetrofitApiService getOracleofBaconApiService() {
        return getOracleofBaconInstance().create(RetrofitApiService.class);
    }
    public static RetrofitApiService getTheMovieDBApiService() {
        return getTheMovieDBInstance().create(RetrofitApiService.class);
    }
}
