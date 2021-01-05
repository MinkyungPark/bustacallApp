package com.bustacall.user.bustacall.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.bustacall.user.bustacall.presenter.Activity_Sliding_Setting_Presenter;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_Sliding_Setting extends BaseActivity implements View.OnClickListener{
    RelativeLayout rl_notice_button; // 알림 투글버튼같은 역할
    TextView tv_title;
    TextView tv_on;
    TextView tv_off;
    TextView tv_enter;
    Button bt_cancel;
    EditText et_account_bank;
    Spinner sp_back;
    String items = null;
    Boolean is_bank_account=false;
    private static int NOTICE_ON_OFF = 0; // on == 0, off == 1 flag
    Activity_Sliding_Setting_Presenter presenter;
    AppController app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_setting);
        set_Values();
        set_Weight();
    }

    public void set_Values(){
        presenter = new Activity_Sliding_Setting_Presenter(this);
        app = (AppController)getApplicationContext();
    }

    public void set_Weight(){
        rl_notice_button = (RelativeLayout)findViewById(R.id.activity_sliding_setting_on_or_off_layout);
        rl_notice_button.setOnClickListener(this);
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("설정");
        tv_enter = (TextView)findViewById(R.id.activity_sliding_setting_tv_enter);
        tv_enter.setOnClickListener(this);
        bt_cancel = (Button)findViewById(R.id.activity_sliding_setting_bt_cancel);
        bt_cancel.setOnClickListener(this);
        setEnableTextView(tv_enter,false);
        sp_back = (Spinner)findViewById(R.id.activity_sliding_setting_sp_bank);
        tv_on = (TextView)findViewById(R.id.activity_sliding_setting_tv_on_text);
        tv_off = (TextView)findViewById(R.id.activity_sliding_setting_tv_off_text);
        et_account_bank = (EditText)findViewById(R.id.activity_sliding_setting_et_account);
        et_account_bank.addTextChangedListener(tw_account_bank);
        ArrayList<String> str_bank = new ArrayList<>();
        str_bank.add("신한");
        str_bank.add("국민");
        str_bank.add("우리");
        str_bank.add("농협");
        str_bank.add("하나");
        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,str_bank);
        sp_back.setAdapter(bankAdapter);
        sp_back.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                items= (String)sp_back.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setNoticeOnOff() {
        switch (NOTICE_ON_OFF) {
            case 0: // 켜기 -> 끄기
                rl_notice_button.setBackgroundResource(R.drawable.notice_off);
                tv_on.setTextColor(Color.parseColor("#2978B0"));
                tv_off.setTextColor(Color.parseColor("#FFFFFF"));
                NOTICE_ON_OFF = 1; // 끄기 flag로 바꿔줌
                presenter.request_send_notice_onoff(app.getUser().getUser_nickname(),NOTICE_ON_OFF);
                break;
            case 1: // 끄기 -> 켜기
                rl_notice_button.setBackgroundResource(R.drawable.notice_on);
                tv_on.setTextColor(Color.parseColor("#FFFFFF"));
                tv_off.setTextColor(Color.parseColor("#2978B0"));
                NOTICE_ON_OFF = 0; // 켜기 flag로 바꿔줌
                presenter.request_send_notice_onoff(app.getUser().getUser_nickname(),NOTICE_ON_OFF);
                break;
        }
        //presenter.request_send_notice_onoff(NOTICE_ON_OFF); // 0: 끄기, 1: 켜기
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_setting_on_or_off_layout: // 알림 켜기, 끄기
                setNoticeOnOff();
                break;
            case R.id.activity_sliding_setting_tv_enter:
                presenter.request_setting_user(items,et_account_bank.getText().toString());
                break;
            case R.id.activity_sliding_setting_bt_cancel:
                final Dialog_base_two_button dialog_base_two_button = new Dialog_base_two_button(this,"정말 탈퇴하시겠습니까?");
                dialog_base_two_button.show();
                dialog_base_two_button.getTv_enter().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_base_two_button.dismiss();
                        if(app.getUser().getRental_list_together().size() > 0 || app.getUser().getRental_list().size()>0)
                        {
                            Dialog_base dialog_base = new Dialog_base(Activity_Sliding_Setting.this,"매물 내역이 존재합니다.");
                            dialog_base.show();
                        }else{
                            presenter.request_delete_user(app.getUser().getUser_nickname());
                        }
                    }
                });

                break;
        }
    }


    TextWatcher tw_account_bank = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_bank_account(et_account_bank.getText().toString());
            checkTonext();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void checkTonext(){
        if(is_bank_account){
            setEnableTextView(tv_enter,true);
        }
    }
    public Boolean getIs_bank_account() {
        return is_bank_account;
    }

    public void setIs_bank_account(Boolean is_bank_account) {
        this.is_bank_account = is_bank_account;
    }
}
