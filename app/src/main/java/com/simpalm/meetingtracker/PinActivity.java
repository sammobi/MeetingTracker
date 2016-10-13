package com.simpalm.meetingtracker;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PinActivity extends AppCompatActivity {
    private EditText mEnterPinEt;
    private Button mSubmitBtn;
    private TextView mSkipTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        mEnterPinEt = (EditText) findViewById(R.id.pin_et);
        mSubmitBtn = (Button) findViewById(R.id.enter_pin_btn);
        mSkipTv = (TextView) findViewById(R.id.skip_tv);

        mSkipTv.setPaintFlags(mSkipTv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mSkipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validInput() == true) {

                    Intent intent = new Intent(PinActivity.this, ConfirmPinActivity.class);
                    String pin = mEnterPinEt.getText().toString();
                    intent.putExtra("Pin", pin);
                    startActivity(intent);
                    finish();

                } else {
                    mEnterPinEt.setError("Please enter pin number or skip to enter it later ");
                }
            }


        });
    }

    public boolean validInput() {
        if (mEnterPinEt.getText().toString().length() == 0) {

            return false;

        }

        return true;
    }

}

