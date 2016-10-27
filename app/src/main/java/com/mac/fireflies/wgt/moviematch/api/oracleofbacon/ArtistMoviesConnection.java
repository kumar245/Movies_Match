package com.mac.fireflies.wgt.moviematch.api.oracleofbacon;


import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mac.fireflies.wgt.moviematch.FindConnectionsArtistFragment;
import com.mac.fireflies.wgt.moviematch.model.DatabaseUserUtil;
import com.mac.fireflies.wgt.moviematch.network.RetrofitApiService;
import com.mac.fireflies.wgt.moviematch.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 10/19/2016.
 */

public class ArtistMoviesConnection {
    static private String URL_KEY = "/cgi-bin/json?p=38b99ce9ec87";
    static RetrofitApiService retrofitApiService = RetrofitClient.getOracleofBaconApiService();
    static final List<PojoArtistMoviesConnection> connection  = new ArrayList<>();

    public static void findConnection(String artist1, String artist2, final FindConnectionsArtistFragment.AdapterArtistMovieConnection adapter) {
        final Call<PojoArtistMoviesConnection> list = retrofitApiService.getOracleofBaconJSON(URL_KEY + "&a="+artist1 +"&b="+artist2);
        list.enqueue(new Callback<PojoArtistMoviesConnection>() {
            @Override
            public void onResponse(Call<PojoArtistMoviesConnection> call, Response<PojoArtistMoviesConnection> response) {
                if (response.isSuccessful()){
                    Intent i = new Intent();
                    switch (response.body().status){
                        case "success":
                            Toast.makeText(adapter.getContext(), "We found a connection", Toast.LENGTH_LONG).show();
                            List<String> links = response.body().link;
                            Collections.reverse(links);
                            DatabaseReference userRef = DatabaseUserUtil.getUserReference(FirebaseDatabase.getInstance().getReference(),
                                    FirebaseAuth.getInstance().getCurrentUser());
                            String keyConnection = DatabaseUserUtil.insertOracleofBaconConnection(
                                    userRef,
                                    links);
                            i.setAction("com.mac.fireflies.wgt.moviematch.STATUS_SUCCESS");
                            adapter.getContext().sendBroadcast(i);
                            DatabaseUserUtil.setConnectionAdapter(userRef, keyConnection, adapter);
                            break;
                        case "spellcheck":
                            Toast.makeText(adapter.getContext(), "Please check the suggested names", Toast.LENGTH_LONG).show();

                            adapter.addAll(response.body().matches);
                            i.setAction("com.mac.fireflies.wgt.moviematch.STATUS_SPELL_CHECK");
                            adapter.getContext().sendBroadcast(i);
                            break;
                    }
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




