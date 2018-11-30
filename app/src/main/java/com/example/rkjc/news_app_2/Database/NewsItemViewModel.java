package com.example.rkjc.news_app_2.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import com.example.rkjc.news_app_2.NewsItem;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private NewsRepository nRepository;

    private LiveData<List<NewsItem>> allNewsItems;
    public NewsItemViewModel(Application application) {
        super(application);
        nRepository=new NewsRepository(application);
        allNewsItems=nRepository.getAllNewsItems();


    }

    public LiveData<List<NewsItem>> getAllNewsItems() {

        return allNewsItems;
    }
    public void sync()
    {
        nRepository.sync();
    }



}
