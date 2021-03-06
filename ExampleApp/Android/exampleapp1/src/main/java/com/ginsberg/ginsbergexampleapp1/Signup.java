package com.ginsberg.ginsbergexampleapp1;

import com.ginsberg.api.GAPI;
import com.ginsberg.api.IGAPICallbacks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.json.JSONArray;



//
// Handle signing up to Ginsberg, with minimal information needed from user
//

public class Signup extends FragmentActivity implements IGAPICallbacks
{
    private FrameLayout flBusy;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        flBusy = (FrameLayout)findViewById(R.id.flSignupBusy);

        SetBusy(false);

        //Set callbacks to this instance
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


    //
    // Actions
    //

    public void pressedSignUp(View v)
    {
        //Call signup with entered values, using defaults where possible to reduce initial user inputs
        GAPI.Instance().SignUp("Please", "Replace", ((EditText)findViewById(R.id.etSignupPassword)).getText().toString(),
                                                    ((EditText)findViewById(R.id.etSignupCPassword)).getText().toString(),
                                                    ((EditText)findViewById(R.id.etSignupEmail)).getText().toString(), 1, null);
    }


    public void pressedBack(View v)
    {
        MoveOn();
    }


    //
    // Callbacks
    //

    public void NeedLogin()
    {
    }


    public void GainedAccess()
    {
    }


    public void SetBusy(boolean truth)
    {
        flBusy.setVisibility(truth? View.VISIBLE: View.GONE);
    }


    public void Comment(String text)
    {
        //If message is of signup success, rather than default initial posting reponse, tell user of successful signup
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

