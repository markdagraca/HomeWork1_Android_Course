package com.example.rkjc.news_app_2.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rkjc.news_app_2.NewsItem;

@Database(entities = {NewsItem.class},version = 1 ,exportSchema = false)
public abstract class NewsItemDatabase extends RoomDatabase {

    public abstract NewsItemDao newsDao();
    private static volatile NewsItemDatabase INSTANCE;


    public static NewsItemDatabase getDataBase(final Context applicationContext) {
        if(INSTANCE==null)
        {
            synchronized (NewsItemDatabase.class)
            {
                if(INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(applicationContext.
                            getApplicationContext(),NewsItemDatabase.class,"news_item")
                            .build();

                }
            }
        }
        return INSTANCE;
    }


}
