package com.simpalm.meetingtracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreateEventBtn, mPastEventsBtn, mReminderSettingBtn, mPinSettingBtn, mUpcomingEventBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateEventBtn = (Button) findViewById(R.id.create_event_btn);
        mPastEventsBtn = (Button) findViewById(R.id.past_event_btn);
        mReminderSettingBtn = (Button) findViewById(R.id.reminder_setting_btn);
        mPinSettingBtn = (Button) findViewById(R.id.pin_setting_btn);
        mUpcomingEventBtn = (Button) findViewById(R.id.upcoming_event_btn);

        mCreateEventBtn.setOnClickListener(this);
        mReminderSettingBtn.setOnClickListener(this);
        mPinSettingBtn.setOnClickListener(this);
        mUpcomingEventBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create_event_btn) {
            Intent intent = new Intent(MainActivity.this, CreateEvent.class);
            startActivity(intent);
        } else if (v.getId() == R.id.reminder_setting_btn) {
            Intent intent = new Intent(MainActivity.this, ReminderSettings.class);
            startActivity(intent);
        } else if (v.getId() == R.id.pin_setting_btn) {
            Intent intent = new Intent(MainActivity.this, PinSettings.class);
            startActivity(intent);
        } else if (v.getId() == R.id.upcoming_event_btn) {

            Intent intent = new Intent(MainActivity.this, UpcomingEvent.class);
            startActivity(intent);


        }


    }
}
