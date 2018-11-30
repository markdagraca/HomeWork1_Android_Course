package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.rkjc.news_app_2.Database.NewsItemViewModel;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    final private ListItemClickListner mOnClickListener;


    private List<NewsItem> newsItems;

    public interface ListItemClickListner{
        void onListItemClick(int clickedItemIndex);

    }


    NewsItemViewModel model;
//    NewsQueryTask newsTask;



    public NewsRecyclerViewAdapter(ListItemClickListner listener, NewsItemViewModel model) {

        newsItems=new ArrayList<NewsItem>();
//        newsTask=new NewsQueryTask(this);
        mOnClickListener=listener;

        this.model=model;




//        newsTask.execute(newsItems);


        Log.d("Adp2","Size: "+ String.valueOf(newsItems.size()));






    }



    @NonNull
    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        int layoutIdForNewsItem=R.layout.news_item;
        LayoutInflater inflater= LayoutInflater.from(context);
        boolean shouldAttachToParen=false;
        View view=inflater.inflate(layoutIdForNewsItem,parent,shouldAttachToParen);
        NewsViewHolder viewHolder=new NewsViewHolder(view);

        return viewHolder;
    }
    public void update(List<NewsItem> newsItems)
    {

        this.newsItems=newsItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.NewsViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public void fill(ArrayList<NewsItem> newsItems) {
        this.newsItems=newsItems;
    }
    public Uri getUrl(int index)
    {
        return Uri.parse(newsItems.get(index).url);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements OnClickListener
    {
        TextView title,description,date;
        ImageView image;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            image=itemView.findViewById(R.id.imageView2);


            itemView.setOnClickListener(this);

        }
        void bind(int i)
        {
            title.setText("title: "+newsItems.get(i).title);
            description.setText("description: "+newsItems.get(i).description);
            Picasso.get().load(newsItems.get(i).thumbURL).into(image);
        }


        @Override
        public void onClick(View view) {
            int click=getAdapterPosition();
            Log.d("touch", String.valueOf(click));

            mOnClickListener.onListItemClick(click);
        }
    }


}