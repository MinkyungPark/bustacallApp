package com.bustacall.user.bustacall.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.User;
import com.bustacall.user.bustacall.presenter.Activity_Splash_Presenter;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_Splash extends BaseActivity implements View.OnClickListener{
    Button bt_login;
    AppController app;
    User user;
    Activity_Splash_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        app = (AppController)getApplicationContext();
        set_Weight();
        set_Values();
    }

    public void set_Values(){
        user = new User();
        app.setUser(user);
        presenter = new Activity_Splash_Presenter(this);
    }

    public void set_Weight(){
        bt_login = (Button)findViewById(R.id.activity_splash_bt_login);
    }

    @Override
    public void onClick(View v) { //통신이 필요하네. 로그인할때도
        if(v.getId()==R.id.activity_splash_bt_login){
            presenter.checkLogin();
        }
    }

}
