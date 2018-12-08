package com.example.rkjc.news_app_2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.rkjc.news_app_2.Database.NewsRepository;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class NewsJob extends JobService {
    String TAG="NewsJob";
    @Override


    public boolean onStartJob(JobParameters job) {

        Log.d(TAG, "onStartJob: ");
        FireBaseRefresh.notify(getApplicationContext(),"test",100);
        new NewsRepository(getApplication()).sync();



        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.d(TAG,"onEND");
        return false;
    }
}
