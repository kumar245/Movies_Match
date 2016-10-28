package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by User on 10/28/2016.
 */

public class HintImageView extends ImageView {
    String hint;
    public HintImageView(Context context) {
        super(context);
    }

    public HintImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HintImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setHint(String hint){
        this.hint = hint;
    }

}
