package com.simpalm.meetingtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PinSettings extends AppCompatActivity implements View.OnClickListener {
    private Button mChangePinBtn, mRemovePinBtn, mSetupPinBtn;
    private Toolbar mToolbar;
    private String removePin;
    private EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_settings);

        mChangePinBtn = (Button) findViewById(R.id.button_changepin);
        mRemovePinBtn = (Button) findViewById(R.id.button_remove_pin);
        mSetupPinBtn = (Button) findViewById(R.id.button_setup_pin);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_pin_settings);
        mToolbar.setTitle(R.string.pin_settings);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(this);
        mChangePinBtn.setOnClickListener(this);
        mRemovePinBtn.setOnClickListener(this);
        mSetupPinBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_changepin) {
            Intent intent = new Intent(PinSettings.this, ChangePin.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button_remove_pin) {

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(PinSettings.this);
            View promptsView = li.inflate(R.layout.remove_pin_dialog, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    PinSettings.this);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

              userInput = (EditText) promptsView
                    .findViewById(R.id.editTextRemoveInput);



            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    if (validateFields() == true) {
                                        Toast.makeText(PinSettings.this, "Your pin has been removed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        } else if (v.getId() == R.id.button_setup_pin)

        {
            Intent intent = new Intent(PinSettings.this, SetupPin.class);
            startActivity(intent);
        }
    }

    public boolean validateFields() {
        removePin = userInput.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PinSettings.this);
        final String pin = prefs.getString("key", "");

        if (pin.equals("")) {

            Toast.makeText(PinSettings.this, "You do not have a pin setup", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!removePin.equals(pin)) {
            Toast.makeText(PinSettings.this, "Your pin is incorrect.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (removePin.equals(pin)) {
            SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(PinSettings.this);
            prefs.edit().remove("key").commit();
            return true;


        }
        return true;
    }

}
