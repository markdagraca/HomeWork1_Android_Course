package com.example.rkjc.news_app_2.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rkjc.news_app_2.JsonUtils;
import com.example.rkjc.news_app_2.NetworkUtils;
import com.example.rkjc.news_app_2.NewsItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository{

    private NewsItemDao newsDao;
    private LiveData<List<NewsItem>> allNewsItems;
    public NewsRepository(Application application)
    {
        NewsItemDatabase db=NewsItemDatabase.getDataBase(application.getApplicationContext());
        newsDao=db.newsDao();
        allNewsItems=newsDao.loadAllNewsItems();
        sync();


    }

    public LiveData<List<NewsItem>> getAllNewsItems() {

        allNewsItems=newsDao.loadAllNewsItems();
        return allNewsItems;
    }
    public void sync () {
        new insertAsyncTask(newsDao,allNewsItems).execute();
    }


    private static class insertAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        LiveData<List<NewsItem>> data;
        static String results = "";

        insertAsyncTask(NewsItemDao dao, LiveData<List<NewsItem>> data) {
            mAsyncTaskDao = dao;
            this.data = data;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
               results = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (IOException e) {
                e.printStackTrace();


            }

            ArrayList<NewsItem> parsed = JsonUtils.parseNews(results);
            mAsyncTaskDao.clearAll();
            for (NewsItem item : parsed) {

                mAsyncTaskDao.insert(item);
                Log.d("database", item.getTitle());

            }


            data = mAsyncTaskDao.loadAllNewsItems();

            return null;
        }
    }


}

