package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.presenter.Activtiy_Signin_Presenter;

/**회원가입 페이지
 * Created by user on 2016-10-09.
 */
public class Activity_Signin extends BaseActivity implements View.OnClickListener{

    TextView tv_overlap,tv_request,tv_login,tv_title;
    EditText et_nickname,et_phonenum,et_requestnum;
    boolean is_nickname,is_phonenum,is_requestnum; //버튼 활성화
    boolean is_nicknamebutton;
    boolean is_phonenumbutton; //중복, 요청이 잘 됬는지
    Activtiy_Signin_Presenter presenter;
    AppController app;

    public void setIs_phonenumbutton(boolean is_phonenumbutton) {
        this.is_phonenumbutton = is_phonenumbutton;
    }

    public void setIs_nicknamebutton(boolean is_nicknamebutton) {
        this.is_nicknamebutton = is_nicknamebutton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        app = (AppController)getApplicationContext();
        set_Values();
    }

    public void set_Values(){
        set_Weight();
        presenter = new Activtiy_Signin_Presenter(this);
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.blutitlebar_login_back_tv_title);
        tv_title.setText("로그인");
        tv_login = (TextView)findViewById(R.id.activity_signin_tv_login);
        tv_overlap = (TextView)findViewById(R.id.activity_signin_tv_overlap);
        tv_request = (TextView)findViewById(R.id.activity_signin_tv_phonerequest);
        et_nickname = (EditText)findViewById(R.id.activtiy_signin_et_nickname);
        et_phonenum = (EditText)findViewById(R.id.activtiy_signin_et_phonenum);
        et_requestnum = (EditText)findViewById(R.id.activtiy_signin_et_request_answer);
        tv_login.setOnClickListener(this);
        tv_overlap.setOnClickListener(this);
        tv_request.setOnClickListener(this);
        et_nickname.addTextChangedListener(tw_nickname);
        et_phonenum.addTextChangedListener(tw_phonenum);
        et_requestnum.addTextChangedListener(tw_requestnum);
        setEnableTextView(tv_login,false);
        setEnableTextView(tv_overlap,false);
        setEnableTextView(tv_request,false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_signin_tv_overlap) {
            Log.d("Test", "tt");
            presenter.request_overlap(et_nickname.getText().toString());
        }
        if (v.getId() == R.id.activity_signin_tv_phonerequest) {
            presenter.request_requestNum(et_phonenum.getText().toString());
        }
        if (v.getId() == R.id.activity_signin_tv_login) {
            presenter.request_login(et_nickname.getText().toString(),et_phonenum.getText().toString(), et_requestnum.getText().toString());
        }
    }
    public void nextPage(){
        app.setSavedId(et_nickname.getText().toString());
        app.getUser().setUser_nickname(et_nickname.getText().toString());
        Intent intent = new Intent(this, Activity_Main.class);
        startActivity(intent);
    }

    public boolean is_nickname() {
        return is_nickname;
    }

    public void setIs_nickname(boolean is_nickname) {
        this.is_nickname = is_nickname;
    }

    public boolean is_phonenum() {
        return is_phonenum;
    }

    public void setIs_phonenum(boolean is_phonenum) {
        this.is_phonenum = is_phonenum;
    }

    public boolean is_requestnum() {
        return is_requestnum;
    }

    public void setIs_requestnum(boolean is_requestnum) {
        this.is_requestnum = is_requestnum;
    }

    public TextView getTv_overlap() {
        return tv_overlap;
    }

    public TextView getTv_request() {
        return tv_request;
    }

    public TextView getTv_login() {
        return tv_login;
    }

    TextWatcher tw_nickname = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_nickname(et_nickname.getText().toString());
            checkTrueofLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher tw_phonenum = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_phonenum(et_phonenum.getText().toString());
            checkTrueofLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher tw_requestnum = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_requestnum(et_requestnum.getText().toString());
            checkTrueofLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void checkTrueofLogin(){
        if(is_nicknamebutton==true&&is_phonenumbutton==true&&is_requestnum==true){
            setEnableTextView(tv_login,true);
        }
    }
}
