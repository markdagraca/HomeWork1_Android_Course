package com.example.rkjc.news_app_2;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rkjc.news_app_2.Database.NewsItemViewModel;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;


public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListner {
    private NewsRecyclerViewAdapter mAdapter;
    private RecyclerView list;
    FirebaseJobDispatcher dispatcher;
    NewsItemViewModel newsItemViewModel;
    Job myJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dispatcher=new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));

        myJob=dispatcher.newJobBuilder().setService(NewsJob.class).setTag("NewsJob")
                .setReplaceCurrent(true).setLifetime(Lifetime.FOREVER)
                .setRecurring(true).setTrigger(Trigger.executionWindow(10,20)).build();

        dispatcher.mustSchedule(myJob);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(RecyclerView) findViewById(R.id.news_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        list.setAdapter(mAdapter);
        newsItemViewModel= ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mAdapter=new NewsRecyclerViewAdapter(this,newsItemViewModel);
        newsItemViewModel.getAllNewsItems().observe(this, newsItems -> mAdapter.update(newsItems));




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



            list=(RecyclerView) findViewById(R.id.news_recyclerview);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            list.setLayoutManager(layoutManager);
            mAdapter=new NewsRecyclerViewAdapter(this,newsItemViewModel);
            list.setAdapter(mAdapter);
            newsItemViewModel.sync();


        }
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("Clicked", String.valueOf(clickedItemIndex));
        Intent intent=new Intent(Intent.ACTION_VIEW,mAdapter.getUrl(clickedItemIndex));
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}