package com.ginsberg.ginsbergexampleapp1;

import com.ginsberg.api.GAPI;
import com.ginsberg.api.IGAPICallbacks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;


//
// Initial screen, for starting signup for user, else logging into system
//

public class Login extends FragmentActivity implements IGAPICallbacks
{
    //Example apps ID
    static final String CLIENT_ID = "B2FCEFED62ED69D9C345E826A749AA86E0CC8F40";
    static final String CLIENT_SECRET = "51CBCCCBBF026387B64F4EBA4B0F4B335FA844D3";


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        findViewById(R.id.btLogin).setVisibility(View.GONE);
        findViewById(R.id.lbLogin).setVisibility(View.GONE);

        //Update fonts
        Typeface osl = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        Typeface osb = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");
        ((TextView)findViewById(R.id.lbLogin)).setTypeface(osl);
        ((TextView)findViewById(R.id.btLogin)).setTypeface(osb);

        //Initial SDK setup
        GAPI.Instance().Setup(this, CLIENT_ID, CLIENT_SECRET, this);
    }


    private void MoveOn()
    {
        //Move onto post screen
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


    //
    // Actions
    //

    public void pressedSignupLayout(View v)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Create an intent that will start the main activity.
                Intent mainIntent = new Intent(Login.this, Signup.class);
                Login.this.startActivity(mainIntent);

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }

    public void pressedSignupWeb(View v)
    {
        //Start web signup popover
        GAPI.Instance().SignUpWeb(); }


    public void pressedLogin(View v)
    {
        //Start SDK login process
        GAPI.Instance().Login();
    }


    //
    // Callbacks
    //

    public void NeedLogin()
    {
        //Show login buttons as login needed
        findViewById(R.id.btLogin).setVisibility(View.VISIBLE);
        findViewById(R.id.lbLogin).setVisibility(View.VISIBLE);
    }


    public void GainedAccess()
    {
        //Login done so hide login button
        findViewById(R.id.btLogin).setVisibility(View.GONE);
        findViewById(R.id.lbLogin).setVisibility(View.GONE);

        //Move onto next view
        MoveOn();
    }


    public void SetBusy(boolean truth)
    {
    }


    public void Comment(String newText)
    {
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

