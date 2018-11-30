package com.example.rkjc.news_app_2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;
@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey (autoGenerate = true)
    private int id;
    String title;
    String description;
    String url;


    String thumbURL;

    public String getDate() {
        return date;
    }

    String date;
    @Ignore
    public NewsItem(String title, String description, String url, String date,String thumbURL)
    {
        this.title=title;
        this.description=description;
        this.url=url;

        this.thumbURL=thumbURL;
    }
    public NewsItem(int id,String title, String description, String url, String date,String thumbURL)
    {

        this.id=id;
        this.title=title;
        this.description=description;
        this.url=url;

        this.thumbURL=thumbURL;
    }
    public String getUrl() {
        return url;
    }
    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
    public String getThumbURL() {
        return thumbURL;
    }

    public int getId() {
        return id;
    }

}
