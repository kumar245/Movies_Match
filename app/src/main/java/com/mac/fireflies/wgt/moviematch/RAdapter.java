package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by User on 11/2/2016.
 */

public class RAdapter extends RecyclerView.Adapter<RAdapter.MyViewHolder> {

    List<String> images;
    Context context;

    public RAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("kaka", "something");
        if (position %2 ==0){
            holder.txtName.setText(images.get(position));
            holder.img.setVisibility(View.INVISIBLE);

        }
        else {
            holder.txtName.setVisibility(View.INVISIBLE);
            Glide.with(context).load(images.get(position)).override(500, 300).into(holder.img);
        }


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.thumbnail);
            txtName = (TextView) itemView.findViewById(R.id.Moviename);
itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int current_position = getAdapterPosition();

            String current_image = images.get(current_position);
            Toast.makeText(context, current_image, Toast.LENGTH_SHORT).show();

        }
    }
}
