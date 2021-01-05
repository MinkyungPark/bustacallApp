package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.Bus;
import com.bustacall.user.bustacall.presenter.Activity_Payment_Presenter;

/**
 * Created by user on 2016-11-04.
 */
public class Activity_Payment extends BaseActivity implements View.OnClickListener{
    TextView tv_user,tv_money,tv_money_two,tv_enter;
    TextView tv_title;
    AppController app;
    Bus bus = new Bus();
    Activity_Payment_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        set_Values();
        set_Weight();
    }

    public void set_Values(){
        app = (AppController)getApplicationContext();
        presenter = new Activity_Payment_Presenter(this);
        Intent intent = getIntent();
        bus = (Bus) intent.getSerializableExtra("Bus");
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("결제 하기");
        tv_user = (TextView)findViewById(R.id.activity_payment_tv_user);
        tv_money = (TextView)findViewById(R.id.activity_payment_tv_money);
        tv_money_two = (TextView)findViewById(R.id.activity_payment_tv_money_two);
        tv_enter = (TextView)findViewById(R.id.activity_payment_tv_enter);
        tv_enter.setOnClickListener(this);
        settv();
    }

    public void settv(){
        tv_money.setText(bus.getMoney());
        int temp = Integer.valueOf(bus.getMoney());
        temp = temp/10;
        tv_money_two.setText(String.valueOf(temp));
        tv_user.setText(bus.getUser_count());
    }

    public int setRental_num(){
        for (int i = 0; i < app.getUser().getRental_list().size(); i++) {
            for (int j = 0; j < app.getUser().getRental_list().get(i).getBus_list().size(); j++) {
                if (app.getUser().getRental_list().get(i).getBus_list().get(j).getNickname().equals(bus.getNickname())) {
                    Log.d("test", String.valueOf(app.getUser().getRental_list().get(i).getRental_num()));
                    return app.getUser().getRental_list().get(i).getRental_num();
                }
            }
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_payment_tv_enter){
            presenter.request_payment(app.getUser().getUser_nickname(),setRental_num(),bus.getBus_num(),bus.getMoney());
        }
    }
}
