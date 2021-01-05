package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.view.Activity_Signin;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-10-23.
 */
public class Activtiy_Signin_Presenter {
    Activity_Signin view;
    AppController app;

    public Activtiy_Signin_Presenter(Activity_Signin view){
        this.view = view;
        this.app = (AppController)view.getApplicationContext();
    }

    public void getis_nickname(String str){
        if(str.equals("")){
            view.setIs_nickname(false);
        }else{
            view.setIs_nickname(true);
            view.setEnableTextView(view.getTv_overlap(),true);
        }
    }

    public void getis_phonenum(String str){
        if(str.equals(""))
        {
            view.setIs_phonenum(false);
        }else{
            view.setIs_phonenum(true);
            view.setEnableTextView(view.getTv_request(),true);
        }
    }

    public void getis_requestnum(String str){
        if(str.equals(""))
        {
            view.setIs_requestnum(false);
        }else{
            view.setIs_requestnum(true);
        }
    }

    public void request_overlap(String nickname){
        //TODO 통신 통해서 중복확인
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_overlapNickname(nickname);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    final Dialog_base dialog_base = new Dialog_base(view,"사용가능한 닉네임입니다.");
                    dialog_base.show();
                    view.setIs_nicknamebutton(true);
                }else{
                    final Dialog_base dialog_base = new Dialog_base(view,"중복되었습니다.");
                    dialog_base.show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view,view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }

    public void request_requestNum(String phonenum) {
        //TODO 통신 통해서 인증 번호 입력
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_phonerequest(phonenum);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    view.setIs_phonenumbutton(true);
                    Dialog_base dialog_base = new Dialog_base(view,"인증번호를 요청하였습니다.");
                    dialog_base.show();
                    view.checkTrueofLogin();
                } else {
                    view.setIs_phonenumbutton(false);
                    final Dialog_base dialog_base = new Dialog_base(view, "잘못된 번호입니다.");
                    dialog_base.show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("실패", t.toString());
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }

    public void request_login(String nickname,String phonenum,String certificationnum){
        Log.d("Test",app.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_login(nickname,phonenum,certificationnum,app.getPushToken());
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    view.nextPage();

                }else{
                    final Dialog_base dialog_base = new Dialog_base(view, "인증번호가 틀립니다.");
                    dialog_base.show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }
}
