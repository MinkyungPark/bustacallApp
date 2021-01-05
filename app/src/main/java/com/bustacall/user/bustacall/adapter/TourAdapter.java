package com.bustacall.user.bustacall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.TourRegion;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-02.
 */
public class TourAdapter extends BaseAdapter {

    ArrayList<TourRegion> tourRegions;
    Context context = null;
    ViewHoder viewHoder = null;

    public TourAdapter(ArrayList<TourRegion> tourRegions, Context context) {
        this.tourRegions = tourRegions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tourRegions.size();
    }

    @Override
    public Object getItem(int position) {
        return tourRegions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        View v = convertView;
        if (v == null) {
            viewHoder = new ViewHoder();
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.tourlist_item, null);

            viewHoder.tv_name = (TextView)v.findViewById(R.id.tourlist_item_tv_name);
            viewHoder.tv_addr = (TextView)v.findViewById(R.id.tourlist_item_tv_addr);
            viewHoder.tv_phone_num = (TextView)v.findViewById(R.id.tourlist_item_tv_tel);
            viewHoder.iv_profile = (ImageView)v.findViewById(R.id.tourlist_item_iv_profile);
            v.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) v.getTag();
        }

        viewHoder.tv_name.setText(tourRegions.get(position).getTitle());
        viewHoder.tv_addr.setText(tourRegions.get(position).getAddr1());
        viewHoder.tv_phone_num.setText(tourRegions.get(position).getTel());

        Glide.with(context).load(tourRegions.get(position).getFirstimage()).into(viewHoder.iv_profile);

        return v;
    }

    class ViewHoder {
        public TextView tv_name,tv_phone_num,tv_addr;
        public ImageView iv_profile;
    }
}

