package com.bustacall.user.bustacall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.Rental;

/**
 * Created by user on 2016-11-02.
 */
public class ReservationListBusAdapter extends BaseAdapter {

    Rental rental = new Rental();
    Context context = null;
    ViewHoder_bus viewHoder = null;

    public ReservationListBusAdapter(Rental rental, Context context) {
        this.rental = rental;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rental.getBus_list().size();
    }

    @Override
    public Object getItem(int position) {
        return rental.getBus_list().get(position);
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
            viewHoder = new ViewHoder_bus();
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.bus_list_item, null);

            viewHoder.tv_nickname = (TextView)v.findViewById(R.id.bus_list_item_tv_nickname);
            viewHoder.tv_type = (TextView)v.findViewById(R.id.bus_list_item_tv_bus_type);
            viewHoder.tv_career = (TextView)v.findViewById(R.id.bus_list_item_tv_career);
            viewHoder.iv_profile = (ImageView)v.findViewById(R.id.bus_list_item_lv_profile);
            viewHoder.tv_money = (TextView)v.findViewById(R.id.bus_list_item_tv_money);
            v.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder_bus) v.getTag();
        }

        Log.d("Test",rental.getBus_list().get(position).getNickname().toString());

        viewHoder.tv_nickname.setText(rental.getBus_list().get(position).getNickname());
        viewHoder.tv_career.setText(rental.getBus_list().get(position).getBus_career());
        viewHoder.tv_type.setText(rental.getBus_list().get(position).getBus_type());
        viewHoder.tv_money.setText(rental.getBus_list().get(position).getMoney()+"원");

        Glide.with(context).load(rental.getBus_list().get(position).getBus_url().get(0)).into(viewHoder.iv_profile);

        return v;
    }

    class ViewHoder_bus {
        public TextView tv_nickname, tv_type, tv_career,tv_money;
        public ImageView iv_profile;
    }
}

