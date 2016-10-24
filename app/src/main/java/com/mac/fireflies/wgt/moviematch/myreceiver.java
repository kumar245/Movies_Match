package com.mac.fireflies.wgt.moviematch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 10/20/2016.
 */

public class myreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent i) {
        if (i.getAction()=="com.mac.fireflies.wgt.moviematch.STATUS_SUCCESS") {

//            Toast.makeText(context, "Success " , Toast.LENGTH_SHORT).show();

        }
        else if (i.getAction()=="com.mac.fireflies.wgt.moviematch.STATUS_SPELL_CHECK"){
//            Toast.makeText(context, "SpellCheck " + i.getAction(), Toast.LENGTH_SHORT).show();
        }
    }
}
