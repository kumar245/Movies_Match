package com.mac.fireflies.wgt.moviematch.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mac.fireflies.wgt.moviematch.FindConnectionsArtistFragment;
import com.mac.fireflies.wgt.moviematch.api.themoviedb.PojoSearchMovie;
import com.mac.fireflies.wgt.moviematch.network.RetrofitApiService;
import com.mac.fireflies.wgt.moviematch.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 10/25/2016.
 */

public class DatabaseUserUtil {
    public static String insertOracleofBaconConnection(DatabaseReference refUser,  List<String> connectionArtist) {
        
        insertConnection(refUser, connectionArtist);
        return getkeyConnection(connectionArtist);        

    }

    private static void insertConnection(DatabaseReference refUser, List<String> connectionArtist) {
        DatabaseReference connections = refUser.child("connections").child(getkeyConnection(connectionArtist));

        for(int i = 0; i <connectionArtist.size(); i++){
            if (i%2 ==0){
                connections.child(i + "").child("Artist").setValue(connectionArtist.get(i));
            }
            else {
                connections.child(i + "").child("Movie").setValue(connectionArtist.get(i));
                getMoviePosterRetrofit(connections.child(i + "").child("Poster"), connectionArtist.get(i));
                //connections.child(i + "").child("Poster").setValue("http://www.navymwr.org/assets/movies/images/img-popcorn.png");
            }
        }
        
    }

    private static String getStringMovieName(String movieNameOoB){
        return movieNameOoB.substring(0, movieNameOoB.length() - 7);
    }

    private static void getMoviePosterRetrofit(final DatabaseReference poster, String nameMovieYear) {
        String URL_KEY = "movie?api_key=866403e3c5ee4487cc4ec63ee2e1e15c";
        RetrofitApiService retrofitApiService = RetrofitClient.getTheMovieDBApiService();
        final Call<PojoSearchMovie> list = retrofitApiService.getSeachMovieJSON(URL_KEY + "&query="+ getStringMovieName(nameMovieYear) + "&year=" + getStringMovieYear(nameMovieYear));
        list.enqueue(new Callback<PojoSearchMovie>() {
            @Override
            public void onResponse(Call<PojoSearchMovie> call, Response<PojoSearchMovie> response) {
                if (response.isSuccessful()) {
                    String posterPath = "";
                    if (response.body().results.size() == 0) {
                        posterPath = "http://www.baudettemovies.com/BT-moviepopcorn.jpg";
                    }
                    else
                        posterPath = ((PojoSearchMovie.Result)response.body().results.get(0)).posterPath;
                    poster.setValue("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + posterPath);
                }
                    Log.d("TheMovieDB ==> ",
                            "We found a connection");

            }

            @Override
            public void onFailure(Call<PojoSearchMovie> call, Throwable t) {

            }

        });
    }

    private static String getStringMovieYear(String nameMovieYear) {
        return nameMovieYear.substring(nameMovieYear.length() - 5, nameMovieYear.length()-1);
    }

    private static String getkeyConnection(List<String> connectionArtist) {
        return connectionArtist.get(0) + "_" + connectionArtist.get(connectionArtist.size() - 1);

    }

    public static DatabaseReference getUserReference(DatabaseReference databaseReference, FirebaseUser user) {
        DatabaseReference refUser = null;
        if (databaseReference.child("users").child(user.getUid()) == null) {
            databaseReference.child("users").setValue(user.getUid());
            databaseReference.child("users").child("name").setValue(user.getDisplayName());
        }
        refUser = databaseReference.child("users").child(user.getUid());
        return refUser;
    }

    public static void setConnectionAdapter(DatabaseReference userRef, String keyConnection, final FindConnectionsArtistFragment.AdapterArtistMovieConnection adapter) {
        DatabaseReference connections = userRef.child("connections").child(keyConnection);
        connections.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<DataSnapshot> conn = (ArrayList<DataSnapshot>) dataSnapshot.getValue();
                ArrayList<String> connList = new ArrayList<>();
//
                Iterable<DataSnapshot> dataIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = dataIterator.iterator();
                int i =0;
                while (iterator.hasNext()){
                    if (i % 2 == 0){
                        connList.add(iterator.next().getChildren().iterator().next().getValue().toString());
                    }
                    else{
                        Iterator<DataSnapshot> moviesIterator = iterator.next().getChildren().iterator();
                        moviesIterator.next().getValue().toString();
                        connList.add(moviesIterator.next().getValue().toString());

                    }
                    i++;
                }

                if (!adapter.isEmpty())
                    adapter.clear();
                adapter.addAll(connList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
