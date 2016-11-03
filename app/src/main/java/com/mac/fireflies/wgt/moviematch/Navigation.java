package com.mac.fireflies.wgt.moviematch;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.ArtistMoviesConnection;
import com.mac.fireflies.wgt.moviematch.model.Movie;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FindConnectionsArtistFragment.OnFragmentInteractionListener {

    static int RC_SIGN_IN = 200;
    FirebaseUser auth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FindConnectionsArtistFragment findConnectionsArtistFragment;
    public static Movie movie=new Movie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.GRAY);
        setSupportActionBar(toolbar);

        ArtistMoviesConnection aux = ArtistMoviesConnection.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findConnectionsArtistFragment = FindConnectionsArtistFragment.newInstance("Leonardo Di Caprio", "Eminem");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myContainer, findConnectionsArtistFragment)
                .commit();

        fireBaseLogin();
//        Intent intent = getParentActivityIntent();
//        if (intent != null) {
//            SuggestedNames suggestedNames = (SuggestedNames) intent.getSerializableExtra("SuggestedNames");
//            if (suggestedNames != null) {
//                suggestedNames = (SuggestedNames) intent.getSerializableExtra("SuggestedNames");
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedNames.link);
//
//                if (suggestedNames.isNameFirst) {
//                    findConnectionsArtistFragment.textViewArtist1.setAdapter(adapter);
//                    findConnectionsArtistFragment.textViewArtist2.setText(suggestedNames.nameArtist2);
//                } else {
//                    findConnectionsArtistFragment.textViewArtist2.setAdapter(adapter);
//                    findConnectionsArtistFragment.textViewArtist1.setText(suggestedNames.nameArtist1);
//
//                }
//
//
//            }
//        }
//        Log.d("sadf",auth.getEmail());
//        Log.d("abce",auth.getUid());
//        TextView username=(TextView) navigationView.getHeaderView(0).findViewById(R.id.uid);
      TextView email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
//        username.setText(auth.getUid());
//        email.setText(auth.getEmail());
//        auth.getEmail();
//        auth.getUid();

//        auth.getDisplayName();

        //TextView username=(TextView) navigationView.getHeaderView(0).findViewById(R.id.uid);



        movie.id = "65655";
        movie.posterPath="http://cdn.collider.com/wp-content/uploads/amazing-spider-man-movie-poster.jpg";
        movie.originalLanguage="English";
        movie.originalTitle="SPIDER MAN";
        movie.overview="Description";

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                auth = FirebaseAuth.getInstance().getCurrentUser();
                database.getReferenceFromUrl("https://fblogin-8f810.firebaseio.com/");
                startActivity(new Intent(this, Navigation.class));
                finish();

            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
            }
        }
        Toast.makeText(this, "Login Activity", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_match) {
            FindConnectionsArtistFragment findConnectionsArtistFragment = FindConnectionsArtistFragment.newInstance("Leonardo Di Caprio", "Eminem");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.myContainer, findConnectionsArtistFragment)
                    .commit();
            // Handle the camera action
        } else if (id == R.id.recent_watched) {
            RecentlyWatched recentlyWatched=new RecentlyWatched();
            getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,recentlyWatched).commit();
//        } else if (id == R.id.category) {
//            Category category = new Category();
//
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.myContainer,category )
//                    .commit();

        } else if (id == R.id.favorites) {
            MyFavourites myFavourites=new MyFavourites();
            getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,myFavourites).commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.sing_out) {
            onLogOut(item.getActionView());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
                        startActivity(new Intent(Navigation.this, Navigation.class));
                        finish();
                    }
                });
    }
}
