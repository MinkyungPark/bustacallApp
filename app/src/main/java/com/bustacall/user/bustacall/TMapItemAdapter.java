package com.bustacall.user.bustacall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bustacall.user.bustacall.view.Activity_MapView;
import com.skp.Tmap.TMapPOIItem;

import java.util.ArrayList;

/**
 * 위치 검색 결과 어댑터
 * Created by user on 2016-10-26.
 */
public class TMapItemAdapter extends BaseAdapter {
    private ArrayList<TMapPOIItem> listviewList = new ArrayList<TMapPOIItem>();

    public TMapItemAdapter() {
    }

    public TMapItemAdapter(ArrayList<TMapPOIItem> listviewList) {
        this.listviewList = listviewList;
    }

    @Override
    public int getCount() {
        return listviewList.size();
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = convertview;
        final int pos = position;
        final Context context = parent.getContext();

        if( v == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(com.bustacall.user.bustacall.R.layout.searchresult_row, null);
        }

        final TMapPOIItem tMapPOIItem = listviewList.get(position);
        TextView name = (TextView)v.findViewById(com.bustacall.user.bustacall.R.id.searchresult_name);
        TextView addr = (TextView)v.findViewById(com.bustacall.user.bustacall.R.id.searchresult_addr);
        TextView map = (TextView)v.findViewById(com.bustacall.user.bustacall.R.id.searchresult_btnMap);


        name.setText(tMapPOIItem.getPOIName());
        String addrStr = tMapPOIItem.getPOIAddress();
        if(addrStr.contains("null")) {
            addrStr = addrStr.replaceAll(" null","");
            Log.d("test",addrStr);
        }

        addr.setText(addrStr);

        final String finalAddrStr = addrStr;
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_MapView.class);
                intent.putExtra("name", tMapPOIItem.getPOIName());
                intent.putExtra("addr", finalAddrStr);
                intent.putExtra("latitude", tMapPOIItem.getPOIPoint().getLatitude());
                intent.putExtra("longitude",tMapPOIItem.getPOIPoint().getLongitude());
                context.startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public Object getItem(int position) {
        return listviewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(TMapPOIItem tMapPOIItem) {
        listviewList.add(tMapPOIItem);
    }
}
