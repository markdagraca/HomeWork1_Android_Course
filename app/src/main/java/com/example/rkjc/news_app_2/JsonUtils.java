package com.example.rkjc.news_app_2;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {


    public static ArrayList<NewsItem> parseNews(String testString) {
        ArrayList<NewsItem> output=new ArrayList<NewsItem>();
        JSONObject json= null;
        try {
            json = new JSONObject(testString);
            JSONArray articles=json.getJSONArray("articles");
            for(int i=0;i<articles.length();i++)
            {
                JSONObject temp=articles.getJSONObject(i);
                output.add(new NewsItem((String)temp.get("title"),(String)temp.get("description"),(String)temp.get("url"),(String)temp.get("publishedAt")));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return output;

    }
}


