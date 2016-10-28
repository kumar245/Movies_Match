package com.mac.fireflies.wgt.moviematch;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 10/27/2016.
 */

public class List_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private List<String> answerList;

    public List_Adapter(Activity context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.answerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.categorylist, null, true);
        TextView answer = (TextView) view.findViewById(R.id.textView2);

        answer.setText(answerList.get(position));
        return view;
    }
}
