package com.simpalm.meetingtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmPinActivity extends AppCompatActivity {

    private EditText mEnterPinEt;
    private Button mSubmitBtn;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pin);

        mEnterPinEt = (EditText) findViewById(R.id.confirm_pin_et);
        mSubmitBtn = (Button) findViewById(R.id.confirm_pin_btn);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = getIntent().getStringExtra("Pin");

                String confirmPin = mEnterPinEt.getText().toString();

                if (confirmPin.equals(pin)) {
                    Toast.makeText(ConfirmPinActivity.this, "Pin matches", Toast.LENGTH_SHORT).show();

                    mSharedPreferences = getApplicationContext().getSharedPreferences("Logged User Pin", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("user_pin", confirmPin); // Storing string

                    editor.commit(); // commit

                    Intent intent = new Intent(ConfirmPinActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                } else {
                    Toast.makeText(ConfirmPinActivity.this, "Pin does not match", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


}
