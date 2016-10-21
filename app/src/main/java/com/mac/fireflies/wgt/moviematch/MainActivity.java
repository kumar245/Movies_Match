package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.ArtistMoviesConnection;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    static int RC_SIGN_IN = 200;
    FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        fireBaseLogin();
        matchArtist();


    }

    private void fireBaseLogin() {
        //****Authentication using FireBaseUI
        auth = FirebaseAuth.getInstance().getCurrentUser();
        if (auth != null) {
            // already signed in
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(
                                    AuthUI.EMAIL_PROVIDER,
                                    AuthUI.GOOGLE_PROVIDER,
                                    AuthUI.FACEBOOK_PROVIDER)
                            .build(),
                    RC_SIGN_IN);

        }
    }
    public void onLogOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    private void matchArtist() {
        //ListView with data
        listView = (ListView) findViewById(R.id.listView);
        Button button = (Button) findViewById(R.id.button);
        final AutoCompleteTextView textViewArtist1 = (AutoCompleteTextView) findViewById(R.id.editText1) ;
        final AutoCompleteTextView textViewArtist2 = (AutoCompleteTextView) findViewById(R.id.editText2) ;
        textViewArtist1.setText("Tom Cruise");
        textViewArtist2.setText("Eminem");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AdapterArtistMovieConnection adapter = new AdapterArtistMovieConnection(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        new ArrayList<String>());
                ArtistMoviesConnection
                        .findConnection(textViewArtist1.getText().toString(), textViewArtist2.getText().toString(), adapter);


//                ArrayAdapter<String> adapter = new ArrayAdapter<ArtistMoviesConnection>(getApplicationContext(), android.R.layout.simple_list_item_1,
//                        new LinkedList<ArtistMoviesConnection>()){
//                    @NonNull
//                    @Override
//                    public View getView(int position, View convertView, ViewGroup parent) {
//                        return super.getView(position, convertView, parent);
//                    }
//                };

                listView.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                startActivity(new Intent(this, MainActivity.class));
                finish();

            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
            }
        }Toast.makeText(this, "Login Activity", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onLogOut(item.getActionView());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //test on console
    public static void main(String...args) throws Exception{
//        ArtistMoviesConnection
//                .findConnection("Kevin Bacon", "Eminem", adapter);
    }

    public class AdapterArtistMovieConnection extends ArrayAdapter<String>{
        List<String> connections;
        public AdapterArtistMovieConnection(Context context, int resource, List<String> conn) {
            super(context, resource);
            connections = conn;

        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return super.getView(position, convertView, parent);
        }
    }
}
