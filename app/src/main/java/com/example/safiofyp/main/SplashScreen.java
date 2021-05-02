package com.example.safiofyp.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.safiofyp.R;
import com.example.safiofyp.auth.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashScreen extends Activity {


    private final int SPLASH_DISPLAY_LENGTH = 2000;

    private FirebaseAuth fAuth;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);


        fAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                FirebaseUser mFirebaseUser = fAuth.getCurrentUser();
                if(mFirebaseUser != null)
                {
                    Intent mainIntent1 = new Intent(SplashScreen.this, NewMainActivity.class);
                    startActivity(mainIntent1);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}