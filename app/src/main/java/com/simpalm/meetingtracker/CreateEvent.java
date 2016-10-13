package com.simpalm.meetingtracker;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateEvent extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText mEventTitleEt, mEventDescriptionEt, mEventDateTimeEt, mEventLocationEt;
    private Button mCreateEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Signup");
        mToolbar.setNavigationIcon(R.drawable.back);

        mEventTitleEt = (EditText) findViewById(R.id.event_title_et);
        mEventDescriptionEt = (EditText) findViewById(R.id.event_description_et);
        mEventDateTimeEt = (EditText) findViewById(R.id.event_date_time_et);
        mEventLocationEt = (EditText) findViewById(R.id.event_location_et);
        mCreateEventBtn = (Button) findViewById(R.id.add_event_btn);


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEvent.this, MainActivity.class);

                startActivity(intent);
                finish();

            }
        });

        mCreateEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mEventDateTimeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(CreateEvent.this);

                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Set Date and Time");

                TimePicker tp = (TimePicker)dialog.findViewById(R.id.timePicker1);



            }
        });
    }


}
