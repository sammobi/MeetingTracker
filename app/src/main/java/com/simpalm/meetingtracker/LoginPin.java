package com.simpalm.meetingtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginPin extends AppCompatActivity {

    private EditText mPinLoginEdit;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pin);

        mPinLoginEdit = (EditText) findViewById(R.id.et_login_pin);
        mLoginBtn = (Button) findViewById(R.id.btn_login);

    }
}
