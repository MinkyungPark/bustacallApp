package com.bustacall.user.bustacall.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Retrofit_User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_Sliding_My_Point extends BaseActivity implements View.OnClickListener{
    TextView tv_title,tv_my_point;
    TextView tv_enter;
    EditText et_point;
    AppController app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_my_point);
        set_Values();
        set_Weight();
    }


    public void set_Values(){
        app = (AppController)getApplicationContext();
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_my_point = (TextView)findViewById(R.id.activity_sliding_my_point_tv_point);
        tv_title.setText("나의 포인트");
        tv_my_point.setText(app.getUser().getUser_point());
        tv_enter = (TextView)findViewById(R.id.activity_sliding_my_point_tv_enter);
        et_point = (EditText)findViewById(R.id.activity_sliding_my_point_et_point);
        et_point.addTextChangedListener(tw_exchange);
        tv_enter.setOnClickListener(this);
        setEnableTextView(tv_enter,false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.activity_sliding_my_point_tv_enter){
            if (checkMyPoint()) {
                final Dialog_base_two_button dialog = new Dialog_base_two_button(this, "환전 하시겠습니까?");
                dialog.show();
                dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        //환전 서버에 보내고 남은 포인트 셋팅
                        final int exchangeInt = Integer.valueOf(et_point.getText().toString().trim());
                        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
                        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
                        Call<Void> retrofitinfo = retrofit_user.request_send_exchange(app.getUser().getUser_nickname(),exchangeInt*1000);
                        retrofitinfo.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                int point = Integer.parseInt(app.getUser().getUser_point())-exchangeInt*1000;
                                app.getUser().setUser_point(String.valueOf(point));
                                setPoint(exchangeInt*1000);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                        dialog.dismiss();
                    }
                });

                dialog.getTv_cancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            } else {
                final Dialog_base dialog = new Dialog_base(this, "환전값을 확인해주세요~");
                dialog.show();
            }
        }
    }

    private boolean checkMyPoint() {
        int point = 0;
        if(!Integer.valueOf(tv_my_point.getText().toString().trim()).equals("")){
            point = Integer.valueOf(tv_my_point.getText().toString().trim());
        }
        int changePoint = 0;
        String changePointStr= et_point.getText().toString().trim();
        if(!"".equals(changePointStr)){
            changePoint = Integer.valueOf(changePointStr) * 1000;
        }

        if (changePoint > point || changePoint == 0 ) {
            return false;
        }
        return true;
    }

    /**
     * 남은 포인트 셋팅, 서버에도 저장
     * @param exchange 환전 금액
     */
    private void setPoint(int exchange) {
        int point = Integer.valueOf(tv_my_point.getText().toString().trim());
        String remainPointStr = String.valueOf(point - exchange);
        tv_my_point.setText(remainPointStr);
    }

    TextWatcher tw_exchange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String str = et_point.getText().toString();
            if(!str.equals("")){
                setEnableTextView(tv_enter,true);
            }else{
                setEnableTextView(tv_enter,false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}