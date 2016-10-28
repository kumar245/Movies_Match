package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.mac.fireflies.wgt.moviematch.api.themoviedb.PojoSearchMovie;
import com.mac.fireflies.wgt.moviematch.model.SuggestedNames;

import java.util.List;

/**
 * Created by User on 10/28/2016.
 */

public class SuggestedListView extends ListView {
    SuggestedNames suggestedNames;
    List<PojoSearchMovie> moviesSearched;
    public SuggestedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSuggestedNames(SuggestedNames suggestedNames){
        this.suggestedNames = suggestedNames;
    }
    public void setPojoMovie(PojoSearchMovie pojoSearchMovie){
        moviesSearched.add(pojoSearchMovie);
    }


    public PojoSearchMovie findMoviebyURI(String hint) {
        if (moviesSearched != null)
            for(PojoSearchMovie pojo : moviesSearched){
                if (pojo.results.get(0).posterPath == getPosterPath(hint)){
                    return pojo;
                }
            }
        return null;
    }

    private String getPosterPath(String hint) {
        String[] aux = hint.split("/");
        return "/"+ aux[aux.length - 1];
    }
}
