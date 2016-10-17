package com.simpalm.meetingtracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mCreateEventBtn, mPastEventsBtn, mReminderSettingBtn, mPinSettingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateEventBtn = (Button) findViewById(R.id.create_event_btn);
        mPastEventsBtn = (Button) findViewById(R.id.past_event_btn);
        mReminderSettingBtn = (Button) findViewById(R.id.reminder_setting_btn);
        mPinSettingBtn = (Button) findViewById(R.id.pin_setting_btn);

        mCreateEventBtn.setOnClickListener(this);
        mReminderSettingBtn.setOnClickListener(this);
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


        } else if (v.getId() == R.id.past_event_btn) {
            Intent intent = new Intent(MainActivity.this, ReminderSettings.class);
            startActivity(intent);

        }


    }
}
