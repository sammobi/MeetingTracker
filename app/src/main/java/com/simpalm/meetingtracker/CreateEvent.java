package com.simpalm.meetingtracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateEvent extends BaseLocation {

    private Toolbar mToolbar;
    private EditText mEventTitleEt, mEventDescriptionEt, mEventLocationEt;
    private Button mCreateEventBtn;
    private TextView mEventDateTimeTv;
    private ProgressDialog mProgressDialog;
    EventDataSource eventDataSource;
    private LocationManager locationManager;
    private String provider;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";
    private SwitchDateTimeDialogFragment dateTimeFragment;
    private String eventTitle, eventDesc, eventDateTime, eventLocation;
    private RadioButton mCurrentLocationrb, mEnterLocationrb;
    private RadioGroup mRadioGrp;
    public static final String TAG = CreateEvent.class.getSimpleName();
    private double mLatitude = 0, mLongitude = 0;

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
        mEventLocationEt = (AutoCompleteTextView) findViewById(R.id.event_location_et);
        mCreateEventBtn = (Button) findViewById(R.id.add_event_btn);

        mRadioGrp = (RadioGroup) findViewById(R.id.rdgroup);
        mRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mEnterLocationrb = (RadioButton) findViewById(R.id.enter_location_rdbtn);
                mCurrentLocationrb = (RadioButton) findViewById(R.id.current_location_rdbtn);
                if (mEnterLocationrb.isChecked()) {

                    mEventLocationEt.setText("");

                } else if (mCurrentLocationrb.isChecked()) {

                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                    Criteria criteria = new Criteria();
                    provider = locationManager.getBestProvider(criteria, false);
                    Location location = locationManager.getLastKnownLocation(provider);
                    if (location != null) {
                        Log.e("Location ", provider);
                        onLocationChanged(location);
                        (new GetUserLocationTask(CreateEvent.this)).execute(location);
                    } else {
                        mEventLocationEt.setText("Location not available");

                    }

                }

            }
        });
        eventDataSource = new EventDataSource(this);

        mCreateEventBtn.setOnClickListener(new View.OnClickListener()

                                           {
                                               @Override
                                               public void onClick(View v) {
                                                   if (validateFields()) {
                                                       new CreateEventTask(eventTitle, eventDesc, eventDateTime, eventLocation).execute();
                                                       mEventTitleEt.setText("");
                                                       mEventDescriptionEt.setText("");
                                                       mEventDateTimeTv.setText("");
                                                       mEventLocationEt.setText("");

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

    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            Log.d("Location change", "mlatitude = " + mLatitude + " ,mlongitude = " + mLongitude);

        }

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

    private class GetUserLocationTask extends AsyncTask<Location, Void, String> {
        Context mContext;
        private ProgressDialog mProgressDialog;

        public GetUserLocationTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected void onPostExecute(String address) {
            // Display the current address in the UI
            mEventLocationEt.setText(address);
        }

        @Override
        protected String doInBackground(Location... params) {
            Geocoder geocoder =
                    new Geocoder(mContext, Locale.getDefault());
            // Get the current location from the input parameter list
            Location loc = params[0];
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
            } catch (IOException e1) {
                Log.e("LocationSampleActivity",
                        "IO Exception in getFromLocation()");
                e1.printStackTrace();
                return ("IO Exception trying to get address");
            } catch (IllegalArgumentException e2) {
                // Error message to post in the log
                String errorString = "Illegal arguments " +
                        Double.toString(loc.getLatitude()) +
                        " , " +
                        Double.toString(loc.getLongitude()) +
                        " passed to address service";
                Log.e("LocationSampleActivity", errorString);
                e2.printStackTrace();
                return errorString;
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address
                Address address = addresses.get(0);
                /*
                * Format the first line of address (if available),
                * city, and country name.
                */
                String addressText = String.format(
                        "%s, %s, %s",
                        // If there's a street address, add it
                        address.getMaxAddressLineIndex() > 0 ?
                                address.getAddressLine(0) : "",
                        // Locality is usually a city
                        address.getLocality(),
                        // The country of the address
                        address.getCountryName());
                // Return the text
                return addressText;
            } else {
                return "No address found";
            }
        }
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
            progressDialog = ProgressDialog.show(CreateEvent.this, "Create Event", "Loading. Please wait...", true);
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
                progressDialog.cancel();

            }
            if (result) {
                Toast.makeText(CreateEvent.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CreateEvent.this, "Unable to insert", Toast.LENGTH_SHORT).show();

            }
        }
    }
}



