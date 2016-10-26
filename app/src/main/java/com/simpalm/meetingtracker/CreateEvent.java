package com.simpalm.meetingtracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEvent extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText mEventTitleEt, mEventDescriptionEt, mEventLocationEt;
    private Button mCreateEventBtn;
    private TextView mEventDateTimeTv;
    private ProgressDialog mProgressDialog;
    EventDataSource eventDataSource;
    private AsyncTask<String, Void, String> asyncTask;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";
    private SwitchDateTimeDialogFragment dateTimeFragment;
    private String eventTitle, eventDesc, eventDateTime, eventLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        if (savedInstanceState != null) {
            // Restore value from saved state
            mEventDateTimeTv.setText(savedInstanceState.getCharSequence(STATE_TEXTVIEW));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar_create_event);
        mToolbar.setTitle("Create Event");
        mToolbar.setNavigationIcon(R.drawable.back);

        mEventTitleEt = (EditText) findViewById(R.id.event_title_et);
        mEventTitleEt.requestFocus();
        mEventDescriptionEt = (EditText) findViewById(R.id.event_description_et);
        mEventDateTimeTv = (TextView) findViewById(R.id.event_date_time_et);
        mEventLocationEt = (EditText) findViewById(R.id.event_location_et);
        mCreateEventBtn = (Button) findViewById(R.id.add_event_btn);

        mCreateEventBtn.setOnClickListener(new View.OnClickListener()

                                           {
                                               @Override
                                               public void onClick(View v) {
                                                   if (validateFields()) {
                                                       new CreateEventTask(eventTitle, eventDesc, eventDateTime, eventLocation).execute();

                                                   }
                                               }
                                           }

        );


        mToolbar.setNavigationOnClickListener(new View.OnClickListener()

                                              {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Intent intent = new Intent(CreateEvent.this, MainActivity.class);
                                                      startActivity(intent);
                                                      finish();

                                                  }
                                              }

        );


        // Construct SwitchDateTimePicker
        dateTimeFragment = (SwitchDateTimeDialogFragment)

                getSupportFragmentManager()

                        .

                                findFragmentByTag(TAG_DATETIME_FRAGMENT);

        if (dateTimeFragment == null)

        {
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
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener()

                                                  {
                                                      @Override
                                                      public void onPositiveButtonClick(Date date) {
                                                          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");

                                                          mEventDateTimeTv.setText(simpleDateFormat.format(date));
                                                      }

                                                      @Override
                                                      public void onNegativeButtonClick(Date date) {
                                                          mEventDateTimeTv.setText("");
                                                      }
                                                  }

        );


        mEventDateTimeTv.setOnClickListener(new View.OnClickListener()

                                            {
                                                @Override
                                                public void onClick(View v) {

                                                    dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
                                                }
                                            }

        );
    }


    public boolean validateFields() {
        eventTitle = mEventTitleEt.getText().toString();
        eventDesc = mEventDescriptionEt.getText().toString();
        eventDateTime = mEventDateTimeTv.getText().toString();
        eventLocation = mEventLocationEt.getText().toString();

        if (eventTitle.equals("")) {
            mEventTitleEt.setError("Please enter event title");
            return false;
        } else if (eventDesc.equals("")) {
            mEventDescriptionEt.setError("Event description cannot be empty");
            return false;
        } else if (eventDateTime.equals("")) {
            mEventDateTimeTv.setError("Please select event Date and Time");
            return false;
        } else if (eventLocation.equals("")) {
            mEventLocationEt.setError("PLease enter event location");
        }

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current textView
        savedInstanceState.putCharSequence(STATE_TEXTVIEW, mEventDateTimeTv.getText());

        super.onSaveInstanceState(savedInstanceState);
    }

    private class CreateEventTask extends AsyncTask<Void, Void, Boolean> {
        private String eventTitle, eventDesc, eventDateTime, eventLocation;
        private ProgressDialog progressDialog;

        public CreateEventTask(String eventTitle, String eventDesc, String eventDateTime, String eventLocation) {
            this.eventTitle = eventTitle;
            this.eventDesc = eventDesc;
            this.eventDateTime = eventDateTime;
            this.eventLocation = eventLocation;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(CreateEvent.this, "Create Event", "Creating Event...", true, false);
        }

        @Override
        protected Boolean doInBackground(Void... inputs) {
            eventDataSource.open();
            long id = eventDataSource.insertNewEvent(eventTitle, eventDesc, eventDateTime, eventLocation);
            eventDataSource.closeDatabase();
            return id != -1;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (result) {
                Toast.makeText(CreateEvent.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CreateEvent.this, "Unable to insert", Toast.LENGTH_SHORT).show();

            }
        }
    }
}



