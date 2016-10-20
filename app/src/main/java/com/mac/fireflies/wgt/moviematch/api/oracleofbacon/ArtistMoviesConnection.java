package com.mac.fireflies.wgt.moviematch.api.oracleofbacon;


import com.mac.fireflies.wgt.moviematch.network.RetrofitApiService;
import com.mac.fireflies.wgt.moviematch.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 10/19/2016.
 */

public class ArtistMoviesConnection {
    static private String URL_KEY = "/cgi-bin/json?p=38b99ce9ec87";
    static RetrofitApiService retrofitApiService = RetrofitClient.getApiService();
    static final List<PojoArtistMoviesConnection> connection  = new ArrayList<>();

    public static void findConnection(String artist1, String artist2) {
        Call<PojoArtistMoviesConnection> list = retrofitApiService.getMyJSON(URL_KEY + "&a="+artist1 +"&b="+artist2);
        list.enqueue(new Callback<PojoArtistMoviesConnection>() {
            @Override
            public void onResponse(Call<PojoArtistMoviesConnection> call, Response<PojoArtistMoviesConnection> response) {
                if (response.isSuccessful()){
                    connection.add(response.body());
                    System.out.println(connection.get(0).status);
                }
            }

            @Override
            public void onFailure(Call<PojoArtistMoviesConnection> call, Throwable t) {

            }
        });
    }

    public static boolean isConnected() {
        return !isEmpty() && (connection.get(0).status).equals("success");
    }
    public static boolean isEmpty(){
        return connection.size() > 0;
    }
    public static String getStatus() {
        if (isEmpty())
            return "error";
        else
            return connection.get(0).status;
    }
}




