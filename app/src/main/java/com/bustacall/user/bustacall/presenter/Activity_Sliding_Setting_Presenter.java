package com.bustacall.user.bustacall.presenter;

import android.content.Intent;
import android.util.Log;

import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.view.Activity_Sliding_Setting;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.view.Activity_Splash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-15.
 */
public class Activity_Sliding_Setting_Presenter {
    Activity_Sliding_Setting view;
    AppController app;
    public Activity_Sliding_Setting_Presenter(Activity_Sliding_Setting view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    public void getis_bank_account(String str){
        if(str.equals("")){
            view.setIs_bank_account(false);
        }else{
            view.setIs_bank_account(true);
        }
    }

    public void request_setting_user(final String bank_name, final String bank_account){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_setting_user(app.getUser().getUser_nickname(),bank_name,bank_account);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    final Dialog_base dialog_base = new Dialog_base(view,"저장되었습니다.");
                    app.getUser().setUser_account(bank_account);
                    app.getUser().setUser_bank(bank_name);
                    dialog_base.show();
                }else{
                    final Dialog_base dialog_base = new Dialog_base(view,"연결이 실패되었습니다.");
                    dialog_base.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void request_send_notice_onoff(String busNum, int notice_onoff) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_notice_onoff(busNum, notice_onoff);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("success", "알림 여부 서버에 보냄");
                } else {
                    Log.d("test", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Test", t.toString());
            }
        });
    }

    public void request_delete_user(String nickname){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_delete_user(nickname);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    app.setSavedId("0");
                    Intent intent = new Intent(view, Activity_Splash.class);
                    view.startActivity(intent);
                    view.finish();
                }else{

                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
