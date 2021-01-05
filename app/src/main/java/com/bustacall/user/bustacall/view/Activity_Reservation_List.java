package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.adapter.ReservationListBusAdapter;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.presenter.Activity_Reservation_List_Presenter;

/**
 * Created by user on 2016-11-01.
 */
public class Activity_Reservation_List extends BaseActivity {
    TextView tv_day, tv_time, tv_user, tv_startpoint, tv_endpoint, tv_endday, tv_title;
    ListView lv_list;
    LinearLayout ll_main;
    AppController app;
    ReservationListBusAdapter adapter = null;

    Activity_Reservation_List_Presenter presenter;
    int rental_num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        set_Values();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void set_Values() {
        Intent intent = getIntent();
        rental_num = intent.getIntExtra("rental_num",-1);

        app = (AppController) getApplicationContext();
        presenter = new Activity_Reservation_List_Presenter(this);

        tv_title = (TextView) findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("예약 내역");
        lv_list = (ListView) findViewById(R.id.activity_reservation_list_lv_list);
        ll_main = (LinearLayout)findViewById(R.id.activity_reservation_list_ll_main);
        ll_main.setVisibility(View.GONE);
        if(rental_num == 0) //푸쉬알람일때
        {
            presenter.request_reservinfo(Integer.parseInt(app.getRental_num()));
        }else { //리스트에 띄울때

            presenter.request_reservinfo(rental_num);
        }
    }


    public void set_Weight(final Rental rental) {
        tv_day = (TextView) findViewById(R.id.activity_reservation_tv_day);
        tv_time = (TextView) findViewById(R.id.activity_reservation_tv_time);
        tv_user = (TextView) findViewById(R.id.activity_reservation_tv_user);
        tv_startpoint = (TextView) findViewById(R.id.activity_reservation_tv_startpoint);
        tv_endpoint = (TextView) findViewById(R.id.activity_reservation_tv_endpoint);
        tv_endday = (TextView) findViewById(R.id.activity_reservation_tv_endday);
        //
        tv_day.setText(rental.getDay_one());
        tv_time.setText(rental.getTime_one());
        tv_user.setText(rental.getUser_count());
        tv_startpoint.setText(rental.getStart_point_one());
        tv_endpoint.setText(rental.getEnd_point_one());
        tv_endday.setText(rental.getEnd_day());

        adapter = new ReservationListBusAdapter(rental, this);
        lv_list.setAdapter(adapter);

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Activity_driver_info.class);
                intent.putExtra("Bus", rental.getBus_list().get(position));
                startActivity(intent);
            }
        });
        ll_main.setVisibility(View.VISIBLE);
    }
}
