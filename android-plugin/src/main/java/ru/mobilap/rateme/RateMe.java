package ru.mobilap.rateme;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.view.View;
import android.util.Log;
import android.os.Build;
import android.net.Uri;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Calendar;

import org.godotengine.godot.Godot;
import org.godotengine.godot.GodotLib;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;

public class RateMe extends GodotPlugin {

    private Godot activity = null;

    public RateMe(Godot godot) {
        super(godot);
        activity = godot;
    }

    @Override
    public String getPluginName() {
        return "RateMe";
    }

    @Override
    public List<String> getPluginMethods() {
        return Arrays.asList("showRateMe");
    }

    /*
    @Override
    public Set<SignalInfo> getPluginSignals() {
        return Collections.singleton(loggedInSignal);
    }
    */

    @Override
    public View onMainCreate(Activity activity) {
        return null;
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

    @Override public void onMainActivityResult (int requestCode, int resultCode, Intent data)
    {
    }
}
