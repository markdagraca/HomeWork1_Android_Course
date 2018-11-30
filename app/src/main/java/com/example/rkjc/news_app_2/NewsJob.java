package com.example.rkjc.news_app_2;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.rkjc.news_app_2.Database.NewsRepository;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class NewsJob extends JobService {
    String TAG="NewsJob";
    @Override

    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "onStartJob: ");
        new NewsRepository(getApplication()).sync();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
