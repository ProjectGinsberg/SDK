package com.ginsberg.ginsbergexampleapp1;

import com.ginsberg.api.GAPI;
import com.ginsberg.api.IGAPICallbacks;


//import android.app.Application;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Profile extends FragmentActivity implements IGAPICallbacks
{
    private boolean firstNameChanged = false;
    private boolean lastNameChanged = false;
    private boolean phoneNumberChanged = false;
    private boolean countryChanged = false;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile);

        SetBusy(false);

        // Create a new service client and bind our activity to this service
        viewWillAppear();


        //Callbacks
        ((EditText)findViewById(R.id.etProfileFirst)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) { firstNameChanged = true; } });
        ((EditText)findViewById(R.id.etProfileLast)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) { lastNameChanged = true; } });
        ((EditText)findViewById(R.id.etProfileNumber)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) { phoneNumberChanged = true; } });
    }



    public void viewWillAppear()
    {
        //self.screenName = @"Login Screen";
        GAPI.Instance().SetCallbacks(this, this);
        UpdateDetails();
    }


    private void MoveOn()
    {
        // Create a new handler with which to start the main activity
        //   and close this splash activity after SPLASH_DISPLAY_TIME has
        //   elapsed.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Create an intent that will start the main activity.
                //Intent mainIntent = new Intent(Profile.this, Post.class);
                //Profile.this.startActivity(mainIntent);

                // Finish splash activity so user cant go back to it.
                Profile.this.finish();

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    //Actions
    public void pressedUpdate(View sender)
    {
        String firstName =  ((EditText)findViewById(R.id.etProfileFirst)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.etProfileLast)).getText().toString();
        String phoneNumber = ((EditText)findViewById(R.id.etProfileNumber)).getText().toString();
        String countryName = ((Spinner)findViewById(R.id.spProfile)).getSelectedItem().toString();
        int countryID = ((Spinner)findViewById(R.id.spProfile)).getSelectedItemPosition();

        if(!firstName.equals(GAPI.Instance().userFirstName) || !lastName.equals(GAPI.Instance().userLastName)  ||
           !phoneNumber.equals(GAPI.Instance().userPhoneNumber) || !countryName.equals(GAPI.Instance().userCountry) )
        {
            GAPI.Instance().PostProfile(firstName, lastName, phoneNumber, countryID+1);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("No Updates")
                    .setMessage("No data has changed.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            SetBusy(false);
                        }
                    });
        }
    }


    public void pressedCancel(View sender)
    {
        MoveOn();
    }


    //Methods
    public void UpdateDetails()
    {
        if(GAPI.Instance().userFirstName != null && !firstNameChanged)
        {
            ((EditText)findViewById(R.id.etProfileFirst)).setText(GAPI.Instance().userFirstName);
        }
        if(GAPI.Instance().userLastName != null && !lastNameChanged)
        {
            ((EditText)findViewById(R.id.etProfileLast)).setText(GAPI.Instance().userLastName);
        }
        if(GAPI.Instance().userPhoneNumber != null && !phoneNumberChanged)
        {
            ((EditText)findViewById(R.id.etProfileNumber)).setText(GAPI.Instance().userPhoneNumber);
        }
        if(GAPI.Instance().countries != null && GAPI.Instance().countries.size() > 200 && !countryChanged)
        {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GAPI.Instance().countries);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
            Spinner spinner = (Spinner)findViewById(R.id.spProfile);
            spinner.setAdapter(spinnerArrayAdapter);

            int cid = GAPI.Instance().GetUserCountryID();
            spinner.setSelection(cid-1);
            /*
            ((BaseAdapter) varSpinner.getAdapter()).notifyDataSetChanged();
            varSpinner.invalidate();
            varSpinner.setSelection(1);
            */
            /*
            pkCountry.reloadAllComponents();
            var id:Int = Int(GAPI.Instance()!.GetUserCountryID());
            pkCountry.selectRow(id, inComponent: 0, animated: true);
            */
        }
    }


    //GAPI Callbacks
    public void NeedLogin()
    {
    }


    public void GainedAccess()
    {
    }


    public void SetBusy(boolean truth)
    {
        findViewById(R.id.flProfileBusy).setVisibility(truth? View.VISIBLE: View.GONE);
    }


    public void Comment(String newText)
    {
        new AlertDialog.Builder(this)
                .setTitle("Updated")
                .setMessage(newText)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        SetBusy(false);
                    }
                })
                /*
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public void CommentError(String newText)
    {
        new AlertDialog.Builder(this)
                .setTitle("Connection Error")
                .setMessage("Please check internet connection.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        SetBusy(false);
                    }
                })
                /*
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public void CommentResult(String newText)
    {
    }


    public void CommentSystem(String newText)
    {
    }


    public void DataReceived(String endPoint, JSONArray data)
    {
        UpdateDetails();
    }
}

