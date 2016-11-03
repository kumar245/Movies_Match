package com.mac.fireflies.wgt.moviematch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFavourites extends Fragment {
    RecyclerView recyclerView;

    public MyFavourites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.my_favourites, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_favorite);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getContext(), FirebaseAuth.getInstance().getCurrentUser());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(favoriteAdapter);

        return v;

    }

}
