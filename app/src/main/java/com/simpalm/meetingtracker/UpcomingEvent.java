package com.simpalm.meetingtracker;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class UpcomingEvent extends AppCompatActivity {

    private ImageButton mBackbtn, mGridBtn, mRecBtn;
    private FrameLayout mFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_event);

        mBackbtn = (ImageButton) findViewById(R.id.upcoming_backBtn);
        mGridBtn = (ImageButton) findViewById(R.id.upcoming_gridView_btn);
        mRecBtn = (ImageButton) findViewById(R.id.upcoming_recViewBtn);


        UpcomingFragment fragment = new UpcomingFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        mBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpcomingEvent.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mGridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpcomingEventGridFragment fragment = new UpcomingEventGridFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });

        mRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpcomingFragment fragment = new UpcomingFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });

    }
}
