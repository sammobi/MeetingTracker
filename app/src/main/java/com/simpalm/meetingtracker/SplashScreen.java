package com.simpalm.meetingtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private Boolean skip;
    private String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadSharedData();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (skip == true) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (!pin.equals("")) {
                    Intent intent = new Intent(SplashScreen.this, LoginPin.class);
                    startActivity(intent);
                    finish();
                } else if (pin.equals("")) {
                    Intent intent = new Intent(SplashScreen.this, PinActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void loadSharedData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
        skip = prefs.getBoolean("skip", false);

        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
        pin = prefs1.getString("key", "");

    }
}
