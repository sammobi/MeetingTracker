package com.simpalm.meetingtracker;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class PastEvents extends AppCompatActivity {

    private ImageButton mBackbtn, mGridBtn, mRecBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_events);

        mBackbtn = (ImageButton) findViewById(R.id.past_backBtn);
        mGridBtn = (ImageButton) findViewById(R.id.past_GridBtn);
        mRecBtn = (ImageButton) findViewById(R.id.past_recBtn);


        PastFragment fragment = new PastFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_past, fragment);
        fragmentTransaction.commit();

        mBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PastEvents.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mGridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PastEventGridFragment fragment = new PastEventGridFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_past, fragment);
                fragmentTransaction.commit();
            }
        });

        mRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PastFragment fragment = new PastFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_past, fragment);
                fragmentTransaction.commit();
            }
        });

    }
}
