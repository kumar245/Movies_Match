package com.mac.fireflies.wgt.moviematch;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mac.fireflies.wgt.moviematch.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Category extends Fragment {
Button btn;


    public Category() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.categories, container, false);

        btn=(Button)v.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), MovieOverview.class);
                startActivity(i);
            }
        });
        return v;

    }

}
