package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListner {
    private NewsRecyclerViewAdapter mAdapter;
    private RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(RecyclerView) findViewById(R.id.news_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        mAdapter=new NewsRecyclerViewAdapter(this);
        list.setAdapter(mAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.get_news)
        {


            Log.d("Main Activity",NetworkUtils.buildURL().toString());
            list=(RecyclerView) findViewById(R.id.news_recyclerview);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            list.setLayoutManager(layoutManager);
            mAdapter=new NewsRecyclerViewAdapter(this);
            list.setAdapter(mAdapter);

        }
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("Clicked", String.valueOf(clickedItemIndex));
        Intent intent=new Intent(Intent.ACTION_VIEW,mAdapter.getUrl(clickedItemIndex));
        startActivity(intent);

    }
}