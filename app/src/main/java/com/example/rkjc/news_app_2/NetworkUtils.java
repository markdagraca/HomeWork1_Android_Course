package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static final String base_url = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=3c03d15911f946c2a8c626633b1615ca";
    public static final String source = "source";
    public static final String sourceValue = "the-next-web";
    public static final String sortBy = "sortBy";
    public static final String sortByValue = "latest";
    public static final String apiKey = "apiKey";
    public static final String apiKeyValue = "3c03d15911f946c2a8c626633b1615ca";

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildURL() {
      Uri builtUri=Uri.parse(base_url).buildUpon().build();
        try {
            return new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
