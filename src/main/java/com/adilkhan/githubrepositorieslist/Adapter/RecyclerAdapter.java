package com.adilkhan.githubrepositorieslist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adilkhan.githubrepositorieslist.Model.SingleRow;
import com.adilkhan.githubrepositorieslist.R;
import com.adilkhan.githubrepositorieslist.ViewHolder.MyHolder;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<MyHolder>
{

    private ArrayList<SingleRow> list;
    private  Context context;

    public RecyclerAdapter(Context context,  ArrayList<SingleRow> list)
    {
        this.context=context;
        this.list=list;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View v=LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


       holder.name.setText(list.get(position).getName());
       holder.description.setText(list.get(position).getDescription());
       holder.language.setText(list.get(position).getLanguage());
       holder.open_issues.setText(list.get(position).getOpen_issues());
       holder.watchers.setText(list.get(position).getWatchers());


        Picasso.with(context).load(list.get(position).getAvatar_url()).fit().placeholder(R.drawable.progress_animation).into(holder.avatar_url);





    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
