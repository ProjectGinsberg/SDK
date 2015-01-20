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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Calendar;


public class Login extends FragmentActivity implements IGAPICallbacks
{
    static final String CLIENT_ID = "B2FCEFED62ED69D9C345E826A749AA86E0CC8F40";
    static final String CLIENT_SECRET = "51CBCCCBBF026387B64F4EBA4B0F4B335FA844D3";
    private int results = 0;
    //private ProgressBar pbActivity;

    //Notificaiton stuff
    static final String NotifyStoreKey = "token";
    static final String HourStoreKey = "nothour";
    static final String MinStoreKey = "notmin";

    public static int hour = 0;
    public static int min = 0;
    public static boolean notification = true;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        //ApplyRescale();

        //pbActivity = (ProgressBar)findViewById(R.id.pbActivity);
        //pbActivity.setVisibility(View.VISIBLE);
        //findViewById(R.id.greyoverlay).setVisibility(View.VISIBLE);
        findViewById(R.id.btLogin).setVisibility(View.GONE);
        findViewById(R.id.lbLogin).setVisibility(View.GONE);

        //Update fonts
        Typeface osl = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        Typeface osb = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");
        ((TextView)findViewById(R.id.lbLogin)).setTypeface(osl);
        ((TextView)findViewById(R.id.btLogin)).setTypeface(osb);

        // Create a new service client and bind our activity to this service
        viewWillAppear();
    }



    public void viewWillAppear()
    {
        //self.screenName = @"Login Screen";
        GAPI.Instance().Setup(this, CLIENT_ID, CLIENT_SECRET, this);
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
                Intent mainIntent = new Intent(Login.this, Post.class);
                Login.this.startActivity(mainIntent);

                // Finish splash activity so user cant go back to it.
                Login.this.finish();

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    //Actions
    public void pressedSignupLayout(View v)
    {
        // Create a new handler with which to start the main activity
        //   and close this splash activity after SPLASH_DISPLAY_TIME has
        //   elapsed.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Create an intent that will start the main activity.
                Intent mainIntent = new Intent(Login.this, Signup.class);
                Login.this.startActivity(mainIntent);

                // Finish splash activity so user cant go back to it.
                //Login.this.finish();

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }

    public void pressedSignupWeb(View v) { GAPI.Instance().SignUpWeb(); }


    public void pressedLogin(View v)
    {
        GAPI.Instance().Login();
    }


    //
    // Notifications
    //

    private void CheckInitialNotification()
    {
        SharedPreferences prefs = this.getSharedPreferences("GINSBERG", Application.MODE_PRIVATE);
        if(!prefs.contains(NotifyStoreKey))
        {
          //  SaveValues(this,0,0);
        }
    }


    public static void LoadValues(Activity activity, int cbID, int tpID)
    {
        //Load values
        SharedPreferences prefs = activity.getSharedPreferences("GINSBERG", Application.MODE_PRIVATE);
        notification = prefs.getBoolean(NotifyStoreKey, true);
        hour = prefs.getInt(HourStoreKey, 18);
        min = prefs.getInt(MinStoreKey, 0);

        CheckBox cb = (CheckBox)activity.findViewById(cbID);//R.id.cbNotify);
        if(cb != null)
        {
            cb.setChecked(notification);
        }

        TimePicker tp = (TimePicker)activity.findViewById(tpID);//R.id.tpNotify);
        if(tp != null)
        {
            tp.setCurrentHour(hour);
            tp.setCurrentMinute(min);
        }
    }


    //GAPI Callbacks
    public void NeedLogin()
    {
        //pbActivity.setVisibility(View.GONE);
        //findViewById(R.id.greyoverlay).setVisibility(View.GONE);
        findViewById(R.id.btLogin).setVisibility(View.VISIBLE);
        findViewById(R.id.lbLogin).setVisibility(View.VISIBLE);
    }


    public void GainedAccess()
    {
        //Get initial data
        //pbActivity.setVisibility(View.VISIBLE);
        //findViewById(R.id.greyoverlay).setVisibility(View.VISIBLE);
        findViewById(R.id.btLogin).setVisibility(View.GONE);
        findViewById(R.id.lbLogin).setVisibility(View.GONE);
        //a_add_subjective.GetInitialData();

        MoveOn();
    }


    public void SetBusy(boolean truth)
    {
        //pbActivity.setVisibility(truth? View.VISIBLE: View.GONE);
        //findViewById(R.id.greyoverlay).setVisibility(truth? View.VISIBLE: View.GONE);
    }


    public void Comment(String newText)
    {
    }


    public void CommentError(String newText)
    {
        NeedLogin();

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
        ++results;

        if(results >= 3)
        {
            //MoveOn();
        }
    }
}

