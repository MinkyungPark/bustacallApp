package com.bustacall.user.bustacall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-10.
 */
public class BusInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    AppController app;
    Context context;
    private ArrayList<String> profile = new ArrayList<>();

    public BusInfoAdapter(Context context, RecyclerView recyclerView, ArrayList<String> profile){
        this.context = context;
        app = (AppController)context.getApplicationContext();
        this.profile = profile;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        UserViewHolder holder = new UserViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            UserViewHolder userViewHolder = (UserViewHolder)holder;
            Glide.with(context).load(profile.get(position+1)).into(userViewHolder.iv_image);
    }

    @Override
    public int getItemCount() {
        return profile.size()-1;
    }

    public class UserViewHolder extends  RecyclerView.ViewHolder{
        public ImageView iv_image;

        public UserViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView)itemView.findViewById(R.id.recyclerview_item_iv_image);
        }
    }
}
