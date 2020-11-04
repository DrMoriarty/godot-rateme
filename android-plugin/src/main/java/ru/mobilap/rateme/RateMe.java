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
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.tasks.Task;

import org.godotengine.godot.Godot;
import org.godotengine.godot.GodotLib;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;

public class RateMe extends GodotPlugin {

    final private SignalInfo finishedSignal = new SignalInfo("finished");

    public RateMe(Godot godot) {
        super(godot);
    }

    @Override
    public String getPluginName() {
        return "RateMe";
    }

    @Override
    public List<String> getPluginMethods() {
        return Arrays.asList("showRateMe");
    }

    @Override
    public Set<SignalInfo> getPluginSignals() {
        return Collections.singleton(finishedSignal);
    }

    @Override
    public View onMainCreate(Activity activity) {
        return null;
    }

    // Public methods

    public void showRateMe() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ) {
            ReviewManager manager = ReviewManagerFactory.create(getActivity());
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // We can get the ReviewInfo object
                        ReviewInfo reviewInfo = task.getResult();
                        Task<Void> flow = manager.launchReviewFlow(getActivity(), reviewInfo);
                        flow.addOnCompleteListener(task2 -> {
                                // The flow has finished. The API does not indicate whether the user
                                // reviewed or not, or even whether the review dialog was shown. Thus, no
                                // matter the result, we continue our app flow.
                                emitSignal(finishedSignal.getName());
                            });
                    } else {
                        // There was some problem, continue regardless of the result.
                        emitSignal(finishedSignal.getName());
                    }
                });
        } else {
            try {
                Intent rateIntent = rateIntentForUrl("market://details");
                getActivity().startActivity(rateIntent);
            } catch (ActivityNotFoundException e) {
                Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
                getActivity().startActivity(rateIntent);
            }
        }
    }

    // Internal methods

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getActivity().getPackageName())));
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
