package org.godotengine.godot;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.os.Build;
import android.net.Uri;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Calendar;

public class GodotRateMe extends Godot.SingletonBase {

    private Godot activity = null;

    static public Godot.SingletonBase initialize(Activity p_activity) 
    { 
        return new GodotRateMe(p_activity); 
    } 

    public GodotRateMe(Activity p_activity) 
    {
        registerClass("GodotRateMe", new String[]{"showRateMe"});
        activity = (Godot)p_activity;
    }

    // Public methods

    public void showRateMe() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            activity.startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            activity.startActivity(rateIntent);
        }
    }

    // Internal methods

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, activity.getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

    @Override protected void onMainActivityResult (int requestCode, int resultCode, Intent data)
    {
    }

}
