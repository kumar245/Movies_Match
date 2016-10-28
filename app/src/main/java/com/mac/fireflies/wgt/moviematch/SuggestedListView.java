package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.mac.fireflies.wgt.moviematch.model.SuggestedNames;

/**
 * Created by User on 10/28/2016.
 */

public class SuggestedListView extends ListView {
    SuggestedNames suggestedNames;
    public SuggestedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSuggestedNames(SuggestedNames suggestedNames){
        this.suggestedNames = suggestedNames;
    }
}
