package com.ginsberg.ginsbergexampleapp1;

import com.ginsberg.api.GAPI;
import com.ginsberg.api.IGAPICallbacks;


import org.json.JSONArray;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AdapterView;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class Post extends Activity implements IGAPICallbacks, AdapterView.OnItemSelectedListener
{
    private EditText textView;
    private LinearLayout frame;
    private ProgressBar pb;
    private LinearLayout items;

    private Button btSend;
    private TextView tvValue;
    private EditText etDeleteID;
    private EditText etValue;
    private EditText etDate;
    private EditText etTime;
    private Spinner snChoice;
    private EditText etID;
    private Spinner snGetPeriod;

    private boolean dataRequested = false;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        SetBusy(false);

        textView = (EditText) findViewById(R.id.etConsole);
        frame = (LinearLayout) findViewById(R.id.llMain);
        items = (LinearLayout) findViewById(R.id.llItems);

        btSend = (Button) findViewById(R.id.btSend);
        tvValue = (TextView) findViewById(R.id.tvValue);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        etValue = (EditText) findViewById(R.id.etValue);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        snChoice = (Spinner) findViewById(R.id.spinner);
        //snEmotion = (Spinner) findViewById(R.id.snEmotion);
        snGetPeriod = (Spinner) findViewById(R.id.snGetPeriod);
        etID = (EditText) findViewById(R.id.etID);
        etDeleteID = (EditText) findViewById(R.id.etDeleteID);

        snChoice.setOnItemSelectedListener(this);
        onSetTimeNow(null);

        SetupClient();
    }


    //General methods
    private void SetupClient()
    {
        GAPI.Instance().SetCallbacks(this, this);
    }


    private void EnableViews(boolean getData, boolean postData, boolean deleteData, String valueType, int... keeps)
    {
        int itemsCount = items.getChildCount();
        int keepsCount = keeps.length;

        //Run through all items
        for(int i = 0; i < itemsCount; ++i)
        {
            View item = items.getChildAt(i);
            int id = item.getId();

            //Check for match
            boolean match = false;
            for(int j = 0; j < keepsCount; ++j)
            {
                if(id == keeps[j])
                {
                    match = true;
                    break;
                }
            }

            item.setVisibility(match? View.VISIBLE: View.GONE);
        }

        //Show buttons
        findViewById(R.id.btGet).setVisibility(getData? View.VISIBLE: View.GONE);
        findViewById(R.id.btDelete).setVisibility(deleteData? View.VISIBLE: View.GONE);
        findViewById(R.id.etDeleteID).setVisibility(deleteData? View.VISIBLE: View.GONE);
        btSend.setVisibility(postData? View.VISIBLE: View.GONE);

        //Show variable
        findViewById(R.id.flValue).setVisibility(valueType != null? View.VISIBLE: View.GONE);
        if(valueType != null)
        {
            ((TextView)findViewById(R.id.tvValue)).setText(valueType);
        }
    }


    //GUI callbacks
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
        //Update visible items respective of selected choice
        String selection = snChoice.getSelectedItem().toString();

         //case "Aggregate":Int(ID)); break;
        if(selection.equals("Correlations"))       EnableViews(false, false, false, null);
        else if(selection.equals("Daily Summary")) EnableViews(true, false, false, null);
        else if(selection.equals("Tag"))           EnableViews(true, false, false, null);
        else if(selection.equals("Profile"))       EnableViews(false, false, false, null);
        else if(selection.equals("Activity"))      EnableViews(true, true, true, null, R.id.flDateTimeStart, R.id.flDateTimeEnd, R.id.flDistance, R.id.flCalories, R.id.flStepCount, R.id.flDateTime);
        else if(selection.equals("Alcohol"))       EnableViews(true, true, true, "Units", R.id.flDateTime);
        else if(selection.equals("Body"))          EnableViews(true, true, true, null, R.id.flWeight, R.id.flFat, R.id.flDateTime);
        else if(selection.equals("Caffeine"))      EnableViews(true, true, true, "Caffeine", R.id.flDateTime);
        else if(selection.equals("Events"))        EnableViews(true, true, true, null, R.id.flDateTime);
        else if(selection.equals("Exercise"))      EnableViews(true, true, true, "Distance", R.id.flDateTime);
        else if(selection.equals("Measures"))      EnableViews(false, false, false, null);
        else if(selection.equals("Notifications")) EnableViews(true, false, false, null);
        else if(selection.equals("Nutrition"))     EnableViews(true, true, true, null, R.id.flCalories, R.id.flCarbohydrates, R.id.flFat, R.id.flFiber, R.id.flProtein, R.id.flSugar, R.id.flDateTime);
        else if(selection.equals("Sleep"))         EnableViews(true, true, true, null, R.id.flTimeAwoke, R.id.flAwake, R.id.flLightSleep, R.id.flRemSleep, R.id.flDeepSleep, R.id.flTotalSleep, /*"sleepQuality",*/ R.id.flDateTime);
        else if(selection.equals("Smoking"))       EnableViews(true, true, true, "Quantity", R.id.flDateTime);
        else if(selection.equals("Social"))        EnableViews(true, true, true, null, R.id.flDateTime);
        else if(selection.equals("Stepcount"))     EnableViews(true, true, true, "Distance", R.id.flDateTimeStart, R.id.flDateTimeEnd, R.id.flCalories, R.id.flStepCount, R.id.flDateTime);
        else if(selection.equals("Survey"))        EnableViews(false, false, false, null);
        else if(selection.equals("Wellbeing"))     EnableViews(true, true, true, null);

        /*
        if (selection.startsWith("Notifications")) {
            EnableViews();
        }  else if (selection.startsWith("Activity")) {
            EnableViews(R.id.flDateTime, R.id.flStepCount, R.id.flDistance, R.id.flCalories, R.id.flDateTimeStart, R.id.flDateTimeEnd);
        } else if (selection.startsWith("Alcohol")) {
            tvValue.setText("Units");
            EnableViews(R.id.flDateTime, R.id.flValue);
        } else if (selection.startsWith("Nutrition")) {
            EnableViews(R.id.flDateTime, R.id.flCalories, R.id.flCarbohydrates, R.id.flFat, R.id.flFiber, R.id.flProtein, R.id.flSugar);
        } else if (selection.startsWith("Sleep")) {
            EnableViews(R.id.flDateTime, R.id.flAwake, R.id.flTimeAwoke, R.id.flLightSleep, R.id.flRemSleep, R.id.flDeepSleep, R.id.flTotalSleep);
        } else if (selection.startsWith("Body")) {
            EnableViews(R.id.flDateTime, R.id.flWeight, R.id.flFat);
        } else if (selection.startsWith("Caffeine")) {
            tvValue.setText("Mgs");
            EnableViews(R.id.flDateTime, R.id.flValue );
        } else if (selection.startsWith("Smoking")) {
            tvValue.setText("Quantity");
            EnableViews(R.id.flDateTime, R.id.flValue );
        } else if (selection.startsWith("Stepcount")) {
            EnableViews(R.id.flDateTime, R.id.flStepCount, R.id.flDateTimeStart, R.id.flDateTimeEnd, R.id.flDistance, R.id.flCalories, R.id.flStepCount );
        } else if (selection.startsWith("Exercise")) {
            EnableViews(R.id.flDateTime, R.id.flStepCount, R.id.flDateTimeStart, R.id.flDateTimeEnd, R.id.flDistance, R.id.flCalories, R.id.flStepCount );
        } else if (selection.startsWith("Social")) {
            EnableViews(R.id.flDateTime);
        } else if (selection.startsWith("Events")) {
            EnableViews(R.id.flDateTime);
        } else if (selection.startsWith("Measures")) {
            EnableViews();
        } else if (selection.startsWith("Wellbeing")) {
            tvValue.setText("Value");
            EnableViews(R.id.flDateTime, R.id.flValue, R.id.flWellbeing);
        } else if (selection.startsWith("Survey")) {
            EnableViews();//R.id.flDateTime, R.id.flValue);
        }
        */
    }
    public void onNothingSelected(AdapterView<?> parent){}


    public void onSend(View v)
    {
        SetBusy(true);
        dataRequested = true;

        String ev = etValue.getText().toString();
        String selection = snChoice.getSelectedItem().toString();
        //String emotion = snEmotion.getSelectedItem().toString();
        String timeStamp =  etDate.getText().toString() + "T" + etTime.getText().toString();

        int ei = 0;
        float ef = 0.0f;
        try{ ei = Integer.valueOf(ev); }
        catch(NumberFormatException e) {}
        try{ ef = Float.valueOf(ev); }
        catch(NumberFormatException e) {}

        try {
            String timeStart =  ((EditText)findViewById(R.id.etDateStart)).getText().toString() + "T" + ((EditText)findViewById(R.id.etTimeStart)).getText().toString();
            String timeEnd =  ((EditText)findViewById(R.id.etDateEnd)).getText().toString() + "T" + ((EditText)findViewById(R.id.etTimeEnd)).getText().toString();

            float distance = Float.valueOf(((EditText)findViewById(R.id.etDistance)).getText().toString());
            float calories = Float.valueOf(((EditText)findViewById(R.id.etCalories)).getText().toString());
            float weight = Integer.valueOf(((EditText)findViewById(R.id.etWeight)).getText().toString());
            float fat = Float.valueOf(((EditText)findViewById(R.id.etFat)).getText().toString());
            float carbohydrates = Float.valueOf(((EditText)findViewById(R.id.etCarbohydrates)).getText().toString());
            float fiber = Float.valueOf(((EditText)findViewById(R.id.etFiber)).getText().toString());
            float protein = Float.valueOf(((EditText)findViewById(R.id.etProtein)).getText().toString());
            float sugar = Float.valueOf(((EditText)findViewById(R.id.etSugar)).getText().toString());
            float totalSleep = Integer.valueOf(((EditText)findViewById(R.id.etTotalSleep)).getText().toString());
            float deepSleep = Integer.valueOf(((EditText)findViewById(R.id.etDeepSleep)).getText().toString());
            float remSleep = Integer.valueOf(((EditText)findViewById(R.id.etRemSleep)).getText().toString());
            float lightSleep = Integer.valueOf(((EditText)findViewById(R.id.etLightSleep)).getText().toString());
            float awake = Integer.valueOf(((EditText)findViewById(R.id.etAwake)).getText().toString());
            int timesAwoken = Integer.valueOf(((EditText)findViewById(R.id.etTimesAwoke)).getText().toString());
            int stepCount = Integer.valueOf(((EditText)findViewById(R.id.etStepCount)).getText().toString());
            int wellbeingType = Integer.valueOf(((EditText)findViewById(R.id.etWellbeing)).getText().toString());

            if (selection.startsWith("Body"))     { GAPI.Instance().PostBody(weight, fat, timeStamp); }
            else if (selection.startsWith("Caffeine")) { GAPI.Instance().PostCaffeine(ef, timeStamp); }
            else if (selection.startsWith("Smoking"))  { GAPI.Instance().PostSmoking(ei, timeStamp); }
            else if (selection.startsWith("Stepcount")) { GAPI.Instance().PostStepcount(timeStart, timeEnd, distance, calories, stepCount, timeStamp); }
            else if (selection.startsWith("Social")) { GAPI.Instance().PostSocial(timeStamp); }
            else if (selection.startsWith("Nutrition")) { GAPI.Instance().PostNutrition(calories, carbohydrates, fat, fiber, protein, sugar, timeStamp); }
            else if (selection.startsWith("Activity")) { GAPI.Instance().PostActivity(timeStart, timeEnd, distance, calories, stepCount, timeStamp); }
            else if (selection.startsWith("Alcohol"))  { GAPI.Instance().PostAlcohol(timeStamp, ef); }
            else if (selection.startsWith("Events")) { GAPI.Instance().PostEvents(timeStamp, "Todays event string", GAPI.Instance().todaysEventID ); }
            else if (selection.startsWith("Sleep")) { GAPI.Instance().PostSleep(timeStamp, timesAwoken, awake, lightSleep, remSleep, deepSleep, totalSleep, 5); }

            else if (selection.startsWith("Exercise")) { GAPI.Instance().PostExercise(timeStart, timeEnd, distance, calories, stepCount, timeStamp); }
            else if (selection.startsWith("Wellbeing")) { GAPI.Instance().PostWellbeing(ei, timeStamp, "I've been interested in new things", 10/*wellbeingType*/); }
            else if (selection.startsWith("Profile")) { GAPI.Instance().PostProfile("Bill", "Ben", "12345", 1); }

            //else if (selection.startsWith("Notifications")) { GAPI.Instance().PostNotifications(ei, timeStamp); }
            //else if (selection.startsWith("Survey")) { GAPI.Instance().PostSurvey(ev, timeStamp); }
            else { SetBusy(false); dataRequested = false; }
        }
        catch(Exception e)
        {
            CommentError("Wrong format");
            return;
        }
    }


    public void onGet(View v)
    {
        SetBusy(true);
        dataRequested = true;

        String selection = snChoice.getSelectedItem().toString();
        String period = snGetPeriod.getSelectedItem().toString();
        String typeFrom = ((Spinner)findViewById(R.id.snFromType)).getSelectedItem().toString();
        String typeTo = ((Spinner)findViewById(R.id.snToType)).getSelectedItem().toString();
        String dateFrom =  ((EditText)findViewById(R.id.etFromDate)).getText().toString();
        String dateTo =  ((EditText)findViewById(R.id.etToDate)).getText().toString();

        int id = 0;
        try{ id = Integer.valueOf(etID.getText().toString()); }
        catch(NumberFormatException e) {}

        if(selection.startsWith("Correlations"))       { GAPI.Instance().GetCorrelations(); }
        else if(selection.startsWith("Daily Summary")) { GAPI.Instance().GetDailySummary(period,typeFrom,dateFrom,typeTo,dateTo); }
        else if(selection.startsWith("Profile"))       { GAPI.Instance().GetProfile(); }
        else if(selection.startsWith("Tag"))           { GAPI.Instance().GetTag(period,typeFrom,dateFrom,typeTo,dateTo); }
        else if(selection.startsWith("Notifications")) { GAPI.Instance().GetNotifications(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        //else if(selection.startsWith("Emotion"))   { api.GetEmotion(period); }
        else if(selection.startsWith("Activity"))  { GAPI.Instance().GetActivity(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Alcohol"))   { GAPI.Instance().GetAlcohol(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Nutrition")) { GAPI.Instance().GetNutrition(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Sleep"))     { GAPI.Instance().GetSleep(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Body"))      { GAPI.Instance().GetBody(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Caffeine"))  { GAPI.Instance().GetCaffeine(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Smoking"))   { GAPI.Instance().GetSmoking(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Stepcount")) { GAPI.Instance().GetStepcount(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Exercise"))  { GAPI.Instance().GetExercise(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Social"))    { GAPI.Instance().GetSocial(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Events"))    { GAPI.Instance().GetEvents(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Measures"))  { GAPI.Instance().GetMeasures(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Wellbeing")) { GAPI.Instance().GetWellbeing(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else if(selection.startsWith("Survey"))    { GAPI.Instance().GetSurvey(period,typeFrom,dateFrom,typeTo,dateTo,id); }
        else { SetBusy(false); dataRequested = false; }
    }


    public void onDelete(View v)
    {
        SetBusy(true);
        dataRequested = true;

        String selection = snChoice.getSelectedItem().toString();
        String ev = etDeleteID.getText().toString();

        int ei = 0;
        try
        {
            ei = Integer.valueOf(ev);
        }
        catch(NumberFormatException e)
        {
            CommentError("Invalid id");
        }

        if(selection.startsWith("Notifications"))  { GAPI.Instance().DeleteNotifications(ei); }
        //else if(selection.startsWith("Emotion"))   { api.DeleteEmotion(ei); }
        else if(selection.startsWith("Activity"))  { GAPI.Instance().DeleteActivity(ei); }
        else if(selection.startsWith("Alcohol"))   { GAPI.Instance().DeleteAlcohol(ei); }
        else if(selection.startsWith("Nutrition")) { GAPI.Instance().DeleteNutrition(ei); }
        else if(selection.startsWith("Sleep"))     { GAPI.Instance().DeleteSleep(ei); }
        else if(selection.startsWith("Body"))      { GAPI.Instance().DeleteBody(ei); }
        else if(selection.startsWith("Caffeine"))  { GAPI.Instance().DeleteCaffeine(ei); }
        else if(selection.startsWith("Smoking"))   { GAPI.Instance().DeleteSmoking(ei); }
        else if(selection.startsWith("Stepcount")) { GAPI.Instance().DeleteStepcount(ei); }
        else if(selection.startsWith("Exercise"))  { GAPI.Instance().DeleteExercise(ei); }
        else if(selection.startsWith("Social"))    { GAPI.Instance().DeleteSocial(ei); }
        else if(selection.startsWith("Events"))    { GAPI.Instance().DeleteEvents(ei); }
        //else if(selection.startsWith("Measures"))  { GAPI.Instance().DeleteMeasures(ei); }
        else if(selection.startsWith("Wellbeing")) { GAPI.Instance().DeleteWellbeing(ei); }
        //else if(selection.startsWith("Survey"))    { GAPI.Instance().DeleteSurvey(ei); }
        else { SetBusy(false); dataRequested = false; }
    }


    public void onClearToken(View v)
    {
        GAPI.Instance().ClearToken();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Create an intent that will start the main activity.
                Intent mainIntent = new Intent(Post.this, Login.class);
                Post.this.startActivity(mainIntent);

                // Finish splash activity so user cant go back to it.
                Post.this.finish();

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    public void onClearComments(View v)
    {
        textView.setText("");
    }


    public void onSetTimeNow(View v)
    {
        Time times = new Time();
        times.setToNow();

        String now = times.format3339(false);
        String date = now.substring(0,10);
        String time = now.substring(11,19);

        //Posting
        etDate.setText(date);
        etTime.setText(time);

        ((EditText)findViewById(R.id.etDateStart)).setText(date);
        ((EditText)findViewById(R.id.etTimeStart)).setText(time);
        ((EditText)findViewById(R.id.etDateEnd)).setText(date);
        ((EditText)findViewById(R.id.etTimeEnd)).setText(time);

        //Getting
        ((EditText)findViewById(R.id.etFromDate)).setText(date);
        ((EditText)findViewById(R.id.etToDate)).setText(date);
    }


    public void pressedConnections(View v)
    {
        // Create a new handler with which to start the main activity
        //   and close this splash activity after SPLASH_DISPLAY_TIME has
        //   elapsed.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Create an intent that will start the main activity.
                Intent mainIntent = new Intent(Post.this, Connections.class);
                Post.this.startActivity(mainIntent);

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    public void pressedProfile(View v)
    {
        // Create a new handler with which to start the main activity
        //   and close this splash activity after SPLASH_DISPLAY_TIME has
        //   elapsed.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Create an intent that will start the main activity.
                Intent mainIntent = new Intent(Post.this, Profile.class);
                Post.this.startActivity(mainIntent);

                // Apply our splash exit (fade out) and main
                //   entry (fade in) animation transitions.
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 0);
    }


    //GAPI Callbacks
    public void GainedAccess()
    {
    }

    /**
      * @brief      Callback for when app has access
      * @details    After the SDK is initialized and finds no valid user login details, else a connection fault, this method will be called
      */
    public void NeedLogin()
    {

    }

    /**
      * @brief      Callback for when app receives data from the server
      * @details    When ever the app requests data, this will be where valid returned data will be sent
      */
    public void DataReceived(String endPoint, JSONArray data)
    {
        if( dataRequested ||
          (   endPoint != "/defaults.json" && !endPoint.contains("/account/signupmetadata")
           && endPoint != "/v1/me" && !endPoint.contains("/v1/wellbeing")
           && !endPoint.contains("/v1/o/events") && endPoint != "/v1/tags") )
        {
            Comment("Got data from " + endPoint + " of " + data.toString());
            SetBusy(false);
            dataRequested = false;
        }
    }

    /**
      * @brief      Callback for when the sdk busy state has changed
      * @details   
      */
    public void SetBusy(boolean truth)
    {
        findViewById(R.id.flPostBusy).setVisibility( truth? View.VISIBLE: View.INVISIBLE);
    }


    public void Comment(String newText)
    {
        textView.setText(newText + "\n" + textView.getText());
    }

    public void CommentError(String newText) { textView.setText("E:"+newText + "\n" + textView.getText()); }
    public void CommentResult(String newText) { textView.setText("R:"+newText + "\n" + textView.getText()); }
    public void CommentSystem(String newText) { textView.setText("S:"+newText + "\n" + textView.getText()); }
}
