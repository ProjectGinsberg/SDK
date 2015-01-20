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
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Calendar;


public class Connections extends FragmentActivity implements IGAPICallbacks
{
    private int results = 0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connections);

        //ApplyRescale();
        //SetBusy(false);

        // Create a new service client and bind our activity to this service
        viewWillAppear();
    }



    public void viewWillAppear()
    {
        //self.screenName = @"Login Screen";
        GAPI.Instance().SetCallbacks(this, this);
        GAPI.Instance().ConnectionsWeb(R.drawable.bg);
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
                //Intent mainIntent = new Intent(Connections.this, Post.class);
                //Connections.this.startActivity(mainIntent);

                // Finish splash activity so user cant go back to it.
                Connections.this.finish();

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    //Actions

    //GAPI Callbacks
    public void NeedLogin()
    {
    }


    public void GainedAccess()
    {
    }


    public void SetBusy(boolean truth)
    {
        //findViewById(R.id.pbConnectionProgress).setVisibility(truth ? View.VISIBLE : View.GONE);
        //findViewById(R.id.ivConnectionsOverlay).setVisibility(truth? View.VISIBLE: View.GONE);
    }


    public void Comment(String newText)
    {
        if(newText == "Webview Closed")
        {
            MoveOn();
        }
    }


    public void CommentError(String newText)
    {
        new AlertDialog.Builder(this)
                .setTitle("Connection Error")
                .setMessage(newText)
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
    }
}

