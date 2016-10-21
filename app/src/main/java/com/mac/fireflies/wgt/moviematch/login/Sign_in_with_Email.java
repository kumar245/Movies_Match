package com.mac.fireflies.wgt.moviematch.login;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mac.fireflies.wgt.moviematch.R;

import org.w3c.dom.Text;

public class Sign_in_with_Email extends AppCompatActivity implements View.OnClickListener {
    EditText e1;
    EditText e2;
    Button b1;
    TextView t1;
    private ProgressDialog progressdialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with__email);
        progressdialog=new ProgressDialog(this);
e1=(EditText) findViewById(R.id.username);
        e2=(EditText) findViewById(R.id.password);
        b1=(Button) findViewById(R.id.sign);
        t1=(TextView) findViewById(R.id.already);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e2.getText().toString();
                String password = e1.getText().toString();
                Log.d("SignIn",email);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Sign_in_with_Email.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {

                                    Toast.makeText(Sign_in_with_Email.this, task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    Log.e("SignIn",task.getException().getMessage().toString());
                                } else {
                                    progressdialog.dismiss();
                                    Toast.makeText(Sign_in_with_Email.this, "Success!",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });
        t1.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };




    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void registerUser(){
    String email=e2.getText().toString().trim();
    String password=e1.getText().toString().trim();
    if (TextUtils.isEmpty(email)){
        //email is empty//
        Toast.makeText(this, "Please Enter email", Toast.LENGTH_SHORT).show();
        //stopping the function execution//
        return;
    }
    if (TextUtils.isEmpty(password)){
        //password is empty//
        Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        return;
    }
    //if validations is ok, we will first show a progressbar//
    progressdialog.setMessage("Registering the User");
    progressdialog.show();
}

    @Override
    public void onClick(View v) {
if (v==b1){
    registerUser();
}
        if (v==t1){
            //Will Open sign in activity//

        }
    }
}
