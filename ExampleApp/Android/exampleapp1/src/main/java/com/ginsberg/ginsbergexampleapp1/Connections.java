package com.ginsberg.ginsbergexampleapp1;

import com.ginsberg.api.GAPI;
import com.ginsberg.api.IGAPICallbacks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import org.json.JSONArray;


//
// Show connections to third party products, such as Fitbit, RunKeeper, Jawbone
//

public class Connections extends FragmentActivity implements IGAPICallbacks
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connections);

        //Set callbacks to this instance
        GAPI.Instance().SetCallbacks(this, this);

        //Start connections web popover, with views default background
        GAPI.Instance().ConnectionsWeb(R.drawable.bg);
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
    }


    public void Comment(String newText)
    {
        //Detect if SDKs webview button has been sent, (sends a "Webview Closed" comment).
        if(newText == "Webview Closed")
        {
            //Move back to post screen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    // Finish splash activity so user cant go back to it.
                    Connections.this.finish();

                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }, 0);
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

