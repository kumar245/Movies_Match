package com.mac.fireflies.wgt.moviematch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by User on 10/20/2016.
 */

public class myreceiver extends BroadcastReceiver {
    Button button;
    @Override
    public void onReceive(Context context, Intent i) {
        if (i.getAction()=="com.mac.fireflies.wgt.moviematch.STATUS_SUCCESS") {

            Toast.makeText(context, "Success " , Toast.LENGTH_SHORT).show();
            button.setText("Success");

        }
        else if (i.getAction()=="com.mac.fireflies.wgt.moviematch.STATUS_SPELL_CHECK"){
            Toast.makeText(context, "SpellCheck " + i.getAction(), Toast.LENGTH_SHORT).show();
            button.setText("Spell-Check");

        }
    }
    myreceiver(Button b1){

        button=b1;

    }
}
