package com.ginsberg.ginsbergexampleapp1;

import com.ginsberg.api.GAPI;
import com.ginsberg.api.IGAPICallbacks;


//import android.app.Application;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import org.json.JSONArray;


public class Connections extends FragmentActivity implements IGAPICallbacks
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connections);

        // Create a new service client and bind our activity to this service
        viewWillAppear();
    }



    public void viewWillAppear()
    {
        GAPI.Instance().SetCallbacks(this, this);
        GAPI.Instance().ConnectionsWeb(R.drawable.bg);
    }


    private void MoveOn()
    {
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


    //GAPI Callbacks
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

