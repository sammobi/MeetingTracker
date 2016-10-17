package com.simpalm.meetingtracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

public class ReminderSettings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Toolbar mToolbar;
    private CheckBox mTwoWeekChk, mOneWeekChk, mThreeDayChk, mOneDayChk, mTwelveHrChk, mAbouttoStartChk;
    private ImageButton mBackBtn, mDoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_settings);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_reminder_settings);
        mBackBtn = (ImageButton) findViewById(R.id.reminder_back);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReminderSettings.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mDoneBtn = (ImageButton) findViewById(R.id.reminder_done);

        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        mTwoWeekChk = (CheckBox) findViewById(R.id.two_week_chk);
        mOneWeekChk = (CheckBox) findViewById(R.id.one_week_chk);
        mThreeDayChk = (CheckBox) findViewById(R.id.three_day_chk);
        mOneDayChk = (CheckBox) findViewById(R.id.one_day_chk);
        mTwelveHrChk = (CheckBox) findViewById(R.id.twelve_hour_chk);
        mAbouttoStartChk = (CheckBox) findViewById(R.id.about_to_chk);
        mTwoWeekChk.setOnCheckedChangeListener(this);
        mOneWeekChk.setOnCheckedChangeListener(this);
        mThreeDayChk.setOnCheckedChangeListener(this);
        mOneDayChk.setOnCheckedChangeListener(this);
        mTwelveHrChk.setOnCheckedChangeListener(this);
        mAbouttoStartChk.setOnCheckedChangeListener(this);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView == mTwoWeekChk) {
                showTextNotification(ReminderSettings.this.getResources().getString(R.string.two_week) + " Selected");
            }

            if (buttonView == mOneWeekChk) {
                showTextNotification(ReminderSettings.this.getResources().getString(R.string.one_week) + " Selected");
            }

            if (buttonView == mThreeDayChk) {
                showTextNotification(ReminderSettings.this.getResources().getString(R.string.three_days) + " Selected");
            }
            if (buttonView == mOneDayChk) {
                showTextNotification(ReminderSettings.this.getResources().getString(R.string.one_day) + " Selected");
            }

            if (buttonView == mTwelveHrChk) {
                showTextNotification(ReminderSettings.this.getResources().getString(R.string.twelve_hour) + " Selected");
            }

            if (buttonView == mAbouttoStartChk) {
                showTextNotification(ReminderSettings.this.getResources().getString(R.string.about_start) + " Selected");

            }


        }
    }

    public void showTextNotification(String msgToDisplay) {
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }

}
