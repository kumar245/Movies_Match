package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/2/2016.
 */

public class Recently_Adapter extends RecyclerView.Adapter<Recently_Adapter.ViewHolder> {
   List<String> images;
    private Context mcontext;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail;
        TextView moviename;

        public ViewHolder(View itemview) {
            super(itemview);
            thumbnail=(ImageView) itemview.findViewById(R.id.thumbnail);
            moviename=(TextView) itemview.findViewById(R.id.Moviename);
            Log.d("contructor", "contructor");
        }
    }
   public Recently_Adapter(Context context,List<String> images){
       mcontext=context;
       this.images=images;
   }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_list_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.moviename.setText(images.get(position));
        Log.d("kaka", images.get(position));
        /*if (position %2 ==0){
            ((TextView)holder.itemView.findViewById(R.id.Moviename)).setText(images.get(position));
            ((ImageView) holder.itemView.findViewById(R.id.thumbnail)).setVisibility(View.INVISIBLE);
        }
        else {
            ((TextView)holder.itemView.findViewById(R.id.Moviename)).setVisibility(View.INVISIBLE);
            Glide
                    .with(mcontext)
                    .load(images.get(position))
                    .into((ImageView) holder.itemView.findViewById(R.id.thumbnail));

        }*/
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
