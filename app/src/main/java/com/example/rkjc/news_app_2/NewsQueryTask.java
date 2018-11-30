package com.example.rkjc.news_app_2;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;

public class NewsQueryTask extends AsyncTask<ArrayList<NewsItem>,Void,ArrayList<NewsItem>> {
    @Override
    protected ArrayList<NewsItem> doInBackground(ArrayList<NewsItem>... arrayLists) {
        return null;
    }

//    NewsRecyclerViewAdapter adapter;
//    public NewsQueryTask(NewsRecyclerViewAdapter adapter){
//        this.adapter=adapter;
//
//    }
//
//    @Override
//    protected ArrayList<NewsItem> doInBackground(ArrayList<NewsItem>... arrayLists) {
//        try {
//             adapter.fill(arrayLists[0]=JsonUtils.parseNews(NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL())));
//             Log.d("async2", String.valueOf(arrayLists[0].size()));
//             return arrayLists[0];
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//    @Override
//    protected void onPostExecute(ArrayList<NewsItem> newsItems) {
//        super.onPostExecute(newsItems);
//        adapter.notifyDataSetChanged();
//    }

}