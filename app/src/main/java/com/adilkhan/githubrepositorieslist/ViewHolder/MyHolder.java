package com.adilkhan.githubrepositorieslist.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adilkhan.githubrepositorieslist.R;

public class MyHolder extends RecyclerView.ViewHolder{

    public TextView name,description,language,watchers,open_issues;
    public ImageView avatar_url;

    public MyHolder(View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.name);
        description=itemView.findViewById(R.id.description);
        language= itemView.findViewById(R.id.language);
        watchers=itemView.findViewById(R.id.watchers);
        open_issues=itemView.findViewById(R.id.issue);
        avatar_url=itemView.findViewById(R.id.profile_image);

    }

}
