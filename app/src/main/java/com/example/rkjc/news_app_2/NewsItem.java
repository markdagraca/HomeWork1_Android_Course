package com.example.rkjc.news_app_2;

import java.util.Arrays;

public class NewsItem {



    String title;
    String description;
    String url;
    String date;
    public NewsItem(String title, String description, String url, String date)
    {
        this.title=title;
        this.description=description;
        this.url=url;
        this.date=date;
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
}
