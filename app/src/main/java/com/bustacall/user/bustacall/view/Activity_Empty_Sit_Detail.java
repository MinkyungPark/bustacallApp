package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.presenter.Activity_Empty_Sit_Detail_Presenter;
import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-11-13.
 */
public class Activity_Empty_Sit_Detail extends BaseActivity implements View.OnClickListener {
    TextView tv_title,tv_day,tv_time,tv_total_sit,tv_current_sit,tv_remain_sit;
    TextView tv_one_money,tv_user,tv_total_money,tv_enter,tv_start,tv_end;
    TextView tv_want_user;
    ImageView iv_image;
    String user;
    Rental rental = new Rental();
    int count;
    Activity_Empty_Sit_Detail_Presenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_sit_detail);
        set_Values();
        set_Weight();
    }

    public void set_Values(){
        Intent intent = getIntent();
        rental = (Rental)intent.getSerializableExtra("rental");
        user = intent.getStringExtra("user");
        presenter = new Activity_Empty_Sit_Detail_Presenter(this);
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("빈자리 타기");
        iv_image = (ImageView)findViewById(R.id.activity_empty_sit_detail_iv_image);
        tv_day = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_day);
        tv_time = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_time);
        tv_total_sit = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_total_sit);
        tv_current_sit = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_current_sit);
        tv_remain_sit = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_remain_sit);
        tv_one_money = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_one_money); //한명
        tv_user = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_user);
        tv_total_money = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_total_money);
        tv_start = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_start);
        tv_end = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_end);
        tv_want_user = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_want_user);
        tv_enter = (TextView)findViewById(R.id.activity_empty_sit_detail_tv_enter);
        tv_enter.setOnClickListener(this);
        setRentel();
    }

    public void setRentel(){
        tv_one_money.setText(rental.getTogether().getMoney());
        String[] arr = user.split(" ");
        count = Integer.parseInt(arr[0]);
        int user_int = Integer.parseInt(arr[0]);
        int one_money = Integer.parseInt(tv_one_money.getText().toString());
        int total_money = one_money*user_int;

        if(rental.getType()==1){ //왕복
            iv_image.setImageResource(R.drawable.between_icon_goandback);
        }else{//편도
            iv_image.setImageResource(R.drawable.between_icon);
        }
        tv_day.setText(rental.getDay_one());
        tv_time.setText(rental.getTime_one());
        tv_total_sit.setText(String.valueOf(rental.getTogether().getMax_user_count())+" 석");
        tv_current_sit.setText(String.valueOf(rental.getTogether().getCurrent_user_count())+" 석");
        tv_remain_sit.setText(String.valueOf(rental.getTogether().getMax_user_count()-rental.getTogether().getCurrent_user_count())+" 석");
        tv_user.setText(arr[0]);

        tv_total_money.setText(String.valueOf(total_money));
        tv_start.setText(rental.getStart_point_one());
        tv_end.setText(rental.getEnd_point_one());
        tv_want_user.setText(arr[0]);
        count = Integer.parseInt(arr[0]);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==R.id.activity_empty_sit_detail_tv_enter){
            presenter.request_add_user_together(rental,count);
        }
    }
}
