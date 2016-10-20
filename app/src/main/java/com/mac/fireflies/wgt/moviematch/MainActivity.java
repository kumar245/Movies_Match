package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.ArtistMoviesConnection;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

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
        Log.d("Development", "second commit");
        Toast.makeText(this, "First Toast Text", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "second Toast test", Toast.LENGTH_SHORT).show();

        //ListView with data
        listView = (ListView) findViewById(R.id.listView);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AdapterArtistMovieConnection adapter = new AdapterArtistMovieConnection(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        new ArrayList<String>());
                ArtistMoviesConnection
                        .findConnection("Tom Cruise", "Eminem", adapter);

                String[] values = new String[]{
                        "Kevin", "Batman", "William"
                };
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
