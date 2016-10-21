package com.mac.fireflies.wgt.moviematch;

<<<<<<< HEAD
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
=======
import android.content.Context;
>>>>>>> development
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
<<<<<<< HEAD
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mac.fireflies.wgt.moviematch.login.Sign_in_with_Email;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private MediaPlayer mediaPlayer = null;
    SurfaceView surfaceView = null;

    Button btnHome;
=======
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.ArtistMoviesConnection;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
>>>>>>> development

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();
        surfaceView = (SurfaceView) findViewById(R.id.surface);

        btnHome = (Button) findViewById(R.id.button3);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Sign_in_with_Email.class);
//                startActivity(i);



//        Uri video = Uri.parse("android.resource://" + getPackageName() +"/"
//                + R.raw.wildlife);
//
//
//              //  Uri video = Uri.parse("C:\\Users\\User\\AndroidStudioProjects\\Movies_Match\\app\\src\\main\\res\\raw\\wildlife.wmv");
//
//                try {
//                    mediaPlayer.setDataSource(video.getEncodedPath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                mediaPlayer.prepare();
//
//
//                //Get the dimensions of the video
//                int videoWidth = mediaPlayer.getVideoWidth();
//                int videoHeight = mediaPlayer.getVideoHeight();
//
//                //Get the width of the screen
//                int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
//
//                //Get the SurfaceView layout parameters
//                ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
//
//                //Set the width of the SurfaceView to the width of the screen
//                lp.width = screenWidth;
//
//                //Set the height of the SurfaceView to match the aspect ratio of the video
//                //be sure to cast these as floats otherwise the calculation will likely be 0
//                lp.height = (int) (((float) videoHeight / (float) videoWidth) * (float) screenWidth);
//
//                //Commit the layout parameters
//                surfaceView.setLayoutParams(lp);
//
//                //Start video
//                mediaPlayer.start();

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource("http://www.hubharp.com/web_sound/BachGavotte.mp3");
                    mediaPlayer.setOnErrorListener((MediaPlayer.OnErrorListener) v);
                    mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) v);
                    mediaPlayer.prepareAsync();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Log.d("Development", "second commit");
        Toast.makeText(this, "First Toast Text", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "second Toast test", Toast.LENGTH_SHORT).show();

<<<<<<< HEAD


=======
        //ListView with data
        listView = (ListView) findViewById(R.id.listView);
        Button button = (Button) findViewById(R.id.button);
        final AutoCompleteTextView textViewArtist1 = (AutoCompleteTextView) findViewById(R.id.editText1) ;
        final AutoCompleteTextView textViewArtist2 = (AutoCompleteTextView) findViewById(R.id.editText2) ;
        textViewArtist1.setText("Tom Cruise");
        textViewArtist2.setText("Eminem");

        //********How to set List to Autocomnplete
        final String[] COUNTRIES = new String[] {
                "Belgium", "France", "Italy", "Germany", "Spain"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textViewArtist1.setAdapter(adapter);

        //*******OnClic Button
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
>>>>>>> development


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

<<<<<<< HEAD
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.wildlife);

        try {
            mediaPlayer.setDataSource(getApplicationContext(), video);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(video.toString());
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get the dimensions of the video
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        //Get the width of the screen
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        //Get the SurfaceView layout parameters
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();

        //Set the width of the SurfaceView to the width of the screen
        lp.width = screenWidth;

        //Set the height of the SurfaceView to match the aspect ratio of the video
        //be sure to cast these as floats otherwise the calculation will likely be 0
        lp.height = (int) (((float) videoHeight / (float) videoWidth) * (float) screenWidth);

        //Commit the layout parameters
        surfaceView.setLayoutParams(lp);

        //Start video
        mediaPlayer.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

=======
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
>>>>>>> development
    }
}
