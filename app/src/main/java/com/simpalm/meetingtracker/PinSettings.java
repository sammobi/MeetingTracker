package com.simpalm.meetingtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PinSettings extends AppCompatActivity implements View.OnClickListener {
    private Button mChangePinBtn, mRemovePinBtn, mSetupPinBtn;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_settings);

        mChangePinBtn = (Button) findViewById(R.id.button_changepin);
        mRemovePinBtn = (Button) findViewById(R.id.button_remove_pin);
        mSetupPinBtn = (Button) findViewById(R.id.button_setup_pin);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_pin_settings);
        mToolbar.setTitle(R.string.pin_settings);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(this);
        mChangePinBtn.setOnClickListener(this);
        mRemovePinBtn.setOnClickListener(this);
        mSetupPinBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
