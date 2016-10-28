package com.mac.fireflies.wgt.moviematch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mac.fireflies.wgt.moviematch.model.DatabaseUserUtil;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentlyWatched extends Fragment {

    public RecentlyWatched() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recently_watched, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fblogin-8f810.firebaseio.com/");
        DatabaseUserUtil.getConnections(databaseReference, FirebaseAuth.getInstance().getCurrentUser(), adapter);
        ListView listView = (ListView) v.findViewById(R.id.listViewRecent);
        listView.setAdapter(adapter);


        return v;
    }

}
