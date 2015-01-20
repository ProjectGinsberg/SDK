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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Calendar;


public class Signup extends FragmentActivity implements IGAPICallbacks
{
    private int results = 0;
    private FrameLayout flBusy;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        flBusy = (FrameLayout)findViewById(R.id.flSignupBusy);

        SetBusy(false);
        viewWillAppear();
    }



    public void viewWillAppear()
    {
        GAPI.Instance().SetCallbacks(this, this);
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

                // Finish splash activity so user cant go back to it.
                Signup.this.finish();

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    //Actions
    public void pressedSignUp(View v)
    {
        GAPI.Instance().SignUp("Please", "Replace", ((EditText)findViewById(R.id.etSignupPassword)).getText().toString(),
                                                    ((EditText)findViewById(R.id.etSignupCPassword)).getText().toString(),
                                                    ((EditText)findViewById(R.id.etSignupEmail)).getText().toString(), 1, null);
    }


    public void pressedBack(View v)
    {
        MoveOn();
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
        flBusy.setVisibility(truth? View.VISIBLE: View.GONE);
        //findViewById(R.id.greyoverlay).setVisibility(truth? View.VISIBLE: View.GONE);
    }


    public void Comment(String text)
    {
        if(text != "Post Success!")
        {
            final Activity activity = this;
            final String usedText = text;

            activity.runOnUiThread(new Runnable() {
                public void run() {
                    new AlertDialog.Builder(activity)
                            .setTitle("Updated")
                            .setMessage(usedText)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    MoveOn();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
        }
    }


    public void CommentError(String newText)
    {
        new AlertDialog.Builder(this)
                .setTitle("Signup Error")
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


    public void CommentResult(String newText)
    {
    }


    public void CommentSystem(String newText)
    {
    }


    public void DataReceived(String endPoint, JSONArray data)
    {
    }
}

