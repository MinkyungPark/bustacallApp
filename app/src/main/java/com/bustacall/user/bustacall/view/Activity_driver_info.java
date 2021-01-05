package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bustacall.user.bustacall.adapter.BusInfoAdapter;
import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.Bus;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-04.
 */
public class Activity_driver_info extends BaseActivity implements View.OnClickListener {
    TextView tv_title;
    TextView tv_nickname, tv_money, tv_type, tv_career, tv_carnum;
    TextView tv_tool, tv_park, tv_motel, tv_food, tv_vol, tv_tax;
    ImageView iv_profile;
    Button bt_enter;
    Bus bus = new Bus();
    RecyclerView recyclerView;
    BusInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        set_Values();
        set_Weight();
    }

    public void set_Values() {
        Intent intent = getIntent();
        bus = (Bus) intent.getSerializableExtra("Bus");
        Log.d("test",bus.toString());
    }

    public void set_Weight() {
        tv_title = (TextView) findViewById(R.id.blutitlebar_login_back_tv_title);
        tv_title.setText("기사 정보");
        tv_career = (TextView) findViewById(R.id.activity_driver_info_tv_career);
        tv_money = (TextView) findViewById(R.id.activity_driver_info_tv_money);
        tv_type = (TextView) findViewById(R.id.activity_driver_info_tv_type);
        tv_carnum = (TextView) findViewById(R.id.activity_driver_info_tv_carnum);
        tv_food = (TextView) findViewById(R.id.activity_driver_info_food);
        tv_tool = (TextView) findViewById(R.id.activity_driver_info_tv_tool);
        tv_motel = (TextView) findViewById(R.id.activity_driver_info_motel);
        tv_tax = (TextView) findViewById(R.id.activity_driver_info_tax);
        tv_park = (TextView) findViewById(R.id.activity_driver_info_park);
        tv_vol = (TextView) findViewById(R.id.activity_driver_info_vol);
        bt_enter = (Button) findViewById(R.id.activity_driver_info_bt_reservation);
        iv_profile = (ImageView) findViewById(R.id.activity_driver_info_iv_profile);
        tv_nickname = (TextView) findViewById(R.id.activity_driver_info_tv_nickname);

        tv_carnum.setText(bus.getBus_num());
        tv_career.setText(bus.getBus_career());
        tv_nickname.setText(bus.getNickname());
        tv_money.setText(bus.getMoney() + "원");
        tv_type.setText(bus.getBus_type());
        setMoney();

        Glide.with(this).load(bus.getBus_url().get(0)).into(iv_profile);

        recyclerView = (RecyclerView) findViewById(R.id.activity_driver_info_rv_list);
        ArrayList<String> bus_url = new ArrayList<>();
        for(int i=0;i<bus.getBus_url().size();i++){
            if(!bus.getBus_url().get(i).equals("")){
                bus_url.add(bus.getBus_url().get(i));
            }
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new BusInfoAdapter(this, recyclerView, bus_url);
        recyclerView.setAdapter(adapter);
    }

    public void setMoney() { //포함 미포함
        if (bus.getMoney_list().get(0) == 1) {
            tv_tool.setText("포함");
        } else {
            tv_tool.setText("미포함");
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv_tool.setTextColor(getColor(R.color.colorRed));
            } else {
                tv_tool.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
        if (bus.getMoney_list().get(1) == 1) {
            tv_park.setText("포함");
        } else {
            tv_park.setText("미포함");
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv_park.setTextColor(getColor(R.color.colorRed));
            } else {
                tv_park.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
        if (bus.getMoney_list().get(2) == 1) {
            tv_food.setText("포함");
        } else {
            tv_food.setText("미포함");
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv_food.setTextColor(getColor(R.color.colorRed));
            } else {
                tv_food.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
        if (bus.getMoney_list().get(3) == 1) {
            tv_motel.setText("포함");
        } else {
            tv_motel.setText("미포함");
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv_motel.setTextColor(getColor(R.color.colorRed));
            } else {
                tv_motel.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
        if (bus.getMoney_list().get(4) == 1) {
            tv_vol.setText("포함");
        } else {
            tv_vol.setText("미포함");
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv_vol.setTextColor(getColor(R.color.colorRed));
            } else {
                tv_vol.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
        if (bus.getMoney_list().get(5) == 1) {
            tv_tax.setText("포함");
        } else {
            tv_tax.setText("미포함");
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv_tax.setTextColor(getColor(R.color.colorRed));
            } else {
                tv_tax.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_driver_info_bt_reservation) {
            //선택시 다음 창
            if (bus.getAccount_flag() == 0) {
                Intent intent = new Intent(this, Activity_Payment.class);
                intent.putExtra("Bus", bus);
                startActivity(intent);
            }else{
                Dialog_base dialog_base = new Dialog_base(this,"이미 예약되어있습니다.");
                dialog_base.show();
            }
        }
    }
}
