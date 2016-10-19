package com.simpalm.meetingtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPin extends AppCompatActivity {

    private EditText mPinLoginEdit;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pin);

        mPinLoginEdit = (EditText) findViewById(R.id.et_login_pin);
        mLoginBtn = (Button) findViewById(R.id.btn_login);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoginPin.this);
        final String pin = prefs.getString("key", "");

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userPin = mPinLoginEdit.getText().toString();

                if (userPin.equals(pin)) {

                    Toast.makeText(LoginPin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginPin.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginPin.this, "Incorrect Login Pin", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
