package com.mac.fireflies.wgt.moviematch.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 10/19/2016.
 */

public class RetrofitClient {
    private static final String ROOT_URL = "http://oracleofbacon.org";
    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static RetrofitApiService getApiService() {
        return getRetrofitInstance().create(RetrofitApiService.class);
    }
}
