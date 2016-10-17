package com.simpalm.meetingtracker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEvent extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText mEventTitleEt, mEventDescriptionEt, mEventDateTimeEt, mEventLocationEt;
    private Button mCreateEventBtn;

    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

    private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";


    private SwitchDateTimeDialogFragment dateTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        if (savedInstanceState != null) {
            // Restore value from saved state
            mEventDateTimeEt.setText(savedInstanceState.getCharSequence(STATE_TEXTVIEW));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar_create_event);
        mToolbar.setTitle("Signup");
        mToolbar.setNavigationIcon(R.drawable.back);

        mEventTitleEt = (EditText) findViewById(R.id.event_title_et);
        mEventDescriptionEt = (EditText) findViewById(R.id.event_description_et);
        mEventDateTimeEt = (EditText) findViewById(R.id.event_date_time_et);
        mEventDateTimeEt.requestFocus();
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


        // Construct SwitchDateTimePicker
        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if (dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(R.string.positive_button_datetime_picker),
                    getString(R.string.negative_button_datetime_picker)
            );
        }
        // Assign values we want
        dateTimeFragment.setHour(0);
        dateTimeFragment.setMinute(20);
        // Set listener for get Date
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");

                mEventDateTimeEt.setText(simpleDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                mEventDateTimeEt.setText("");
            }
        });


        mEventDateTimeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(v);
                dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
            }
        });
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current textView
        savedInstanceState.putCharSequence(STATE_TEXTVIEW, mEventDateTimeEt.getText());

        super.onSaveInstanceState(savedInstanceState);
    }


}



