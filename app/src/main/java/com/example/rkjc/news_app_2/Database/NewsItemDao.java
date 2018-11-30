package com.example.rkjc.news_app_2.Database;

import com.example.rkjc.news_app_2.NewsItem;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rkjc.news_app_2.NewsItem;

import java.util.List;

@Dao
public interface NewsItemDao {
    @Query("SELECT * FROM news_item ORDER BY date ")
    LiveData<List<NewsItem>> loadAllNewsItems();
    @Insert
    void insert(NewsItem items);
    @Query("DELETE FROM news_item")
    void clearAll () ;//clears all current entries in database

    @Delete
    void delete(NewsItem newsItem);
}
