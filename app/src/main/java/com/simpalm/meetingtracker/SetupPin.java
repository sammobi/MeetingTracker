package com.simpalm.meetingtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SetupPin extends AppCompatActivity {

    private EditText mPinEt, mConfirmPin;
    private Button mSetUpBtn;
    private ImageButton mBackBtn;
    private String userPin;
    private String confirmPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_pin);

        mPinEt = (EditText) findViewById(R.id.edittext_SetupPin);
        mConfirmPin = (EditText) findViewById(R.id.editText_confirm_pin_setupPin);
        mBackBtn = (ImageButton) findViewById(R.id.setupPin_back);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupPin.this, PinSettings.class);
                startActivity(intent);
                finish();
            }
        });
        mSetUpBtn = (Button) findViewById(R.id.btn_setupPin);

        mSetUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields() == true) {


                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SetupPin.this);
                    prefs.edit().putString("key", confirmPin).commit();

                    SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(SetupPin.this);
                    prefs1.edit().remove("skip").commit();

                    Toast.makeText(SetupPin.this, "Your pin has been set", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean validateFields() {
        userPin = mPinEt.getText().toString();
        confirmPin = mConfirmPin.getText().toString();

        if (!userPin.equals(confirmPin)) {
            Toast.makeText(SetupPin.this, "Pin and Confirm Pin should be same", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userPin.length() < 4) {
            Toast.makeText(SetupPin.this, "Pin must be 4 digits", Toast.LENGTH_SHORT).show();
            return false;
        } else if (confirmPin.length() < 4) {
            Toast.makeText(SetupPin.this, "Pin must be 4 digits", Toast.LENGTH_SHORT).show();
        }

        return true;

    }
}
