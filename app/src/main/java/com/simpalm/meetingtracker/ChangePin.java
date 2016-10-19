package com.simpalm.meetingtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePin extends AppCompatActivity {

    private EditText mOldPinEt, mNewPinEt, mConfirmPinEt;
    private Button mChangePinBtn;
    private Toolbar mToolbar;
    private SharedPreferences mSharedPreferences;


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
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePin.this, PinSettings.class);
                startActivity(intent);
                finish();
            }
        });
        mChangePinBtn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {


// call the sharedprefernce and get the pin from sharepference and save it inside the pin variable.
                                                 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChangePin.this);
                                                 String pin = prefs.getString("key", "");


                                                 String oldPin = mOldPinEt.getText().toString();
                                                 String newPin = mNewPinEt.getText().toString();
                                                 String confirmPin = mConfirmPinEt.getText().toString();

                                                 if (pin.equals(null)) {

                                                     Intent intent = new Intent(ChangePin.this, SetupPin.class);
                                                     startActivity(intent);
                                                     Toast.makeText(ChangePin.this, "You do not have a pin setup. Please setup pin", Toast.LENGTH_SHORT).show();
                                                 } else if (!oldPin.equals(pin)) {
                                                     Toast.makeText(ChangePin.this, "Your old pin is incorrect", Toast.LENGTH_SHORT).show();
                                                 } else if (!newPin.equals(confirmPin)) {
                                                     Toast.makeText(ChangePin.this, "New pin and confirm pin does not match", Toast.LENGTH_SHORT).show();
                                                 } else {
                                                     mOldPinEt.setText("");
                                                     mNewPinEt.setText("");
                                                     mConfirmPinEt.setText("");
                                                     Toast.makeText(ChangePin.this, "Your pin has been reset", Toast.LENGTH_SHORT).show();
                                                     SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(ChangePin.this);
                                                     prefs.edit().putString("key", confirmPin).commit();
                                                 }
                                             }
                                         }

        );
    }
}
