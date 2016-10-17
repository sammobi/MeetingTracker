package com.simpalm.meetingtracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

public class ChangePin extends AppCompatActivity {

    private EditText mOldPinEt, mNewPinEt, mConfirmPinEt;
    private Button mChangePinBtn;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        mOldPinEt = (EditText) findViewById(R.id.editText_old_pin_changePin);
        mNewPinEt = (EditText) findViewById(R.id.editText_old_pin_newPin);
        mConfirmPinEt = (EditText) findViewById(R.id.editText_confirm_pin_changePin);

        mChangePinBtn = (Button) findViewById(R.id.btn_changePin);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_change_pin);
        mToolbar.setTitle("Change Pin");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.back);


    }
}
