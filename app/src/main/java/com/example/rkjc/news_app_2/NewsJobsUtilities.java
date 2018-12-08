package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.sql.Driver;

public class NewsJobsUtilities {

    private static boolean sInitialized;
    synchronized public static void refreshthingfirebase(@NonNull final Context context)
    {
        Log.d("JobUtils","it was started");
        if(sInitialized){

            return;
        }
        Log.d("JobUtils","it was intialized");
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job=dispatcher.newJobBuilder().setService(NewsJob.class).setTag("NewsJobService")
                .setReplaceCurrent(true).setLifetime(Lifetime.FOREVER)
                .setRecurring(true).setTrigger(Trigger.executionWindow(10,10)).build();

        dispatcher.cancelAll();
        dispatcher.mustSchedule(job);
        dispatcher.schedule(job);
        sInitialized=true;

    }
}
