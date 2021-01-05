package com.bustacall.user.bustacall.presenter;

import android.content.Intent;
import android.util.Log;

import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.view.Activity_Main;
import com.bustacall.user.bustacall.view.Activity_Signin;
import com.bustacall.user.bustacall.view.Activity_Splash;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_Progress;
import com.bustacall.user.bustacall.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-10-29.
 */
public class Activity_Splash_Presenter {
    Activity_Splash view;
    AppController app;

    public Activity_Splash_Presenter(Activity_Splash view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    public void checkLogin(){
        app.getUser().setUser_nickname(app.getSavedId("id","0"));
        if(app.getUser().getUser_nickname().equals("0"))
        {
            Intent intent = new Intent(view,Activity_Signin.class);
            view.startActivity(intent);
        }else{
            request_mainlogin();
        }
    }

    public void request_mainlogin(){
        String nickname = app.getSavedId("id","0");
        final Dialog_Progress dialog_progress = new Dialog_Progress(view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<User> retrofitinfo = retrofit_user.request_mainlogin(nickname);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    app.setUser(response.body());
                    ArrayList<Rental> rental_temp = new ArrayList<Rental>();
                    ArrayList<Rental> rental_temp_together = new ArrayList<Rental>();
                    for(int i=0;i<response.body().getRental_list().size();i++)
                    {
                        if(response.body().getRental_list().get(i).getTogether().getFlag()==0){ //처음에 대여하는 중
                            rental_temp.add(response.body().getRental_list().get(i));
                        }else if(response.body().getRental_list().get(i).getNickname().equals(response.body().getUser_nickname())
                                &&response.body().getRental_list().get(i).getTogether().getFlag()==1){ //대여 다하고 빈자리타기가 된 상황
                            rental_temp.add(response.body().getRental_list().get(i));
                        }
                        if(response.body().getRental_list().get(i).getTogether().getFlag()==2){ //같이타기
                            rental_temp_together.add(response.body().getRental_list().get(i));
                        }else if(response.body().getRental_list().get(i).getTogether().getFlag()==1 &&
                                !response.body().getRental_list().get(i).getNickname().equals(app.getUser().getUser_nickname())){ //빈자리타기이면서 내가 안올린 매물
                            rental_temp_together.add(response.body().getRental_list().get(i));
                        }
                    }
                    app.getUser().setRental_list(rental_temp);
                    app.getUser().setRental_list_together(rental_temp_together);
                    app.RENTAL_COUNT = rental_temp.size(); //처음에 전체 사이즈 받아오기
                    Intent intent = new Intent(view, Activity_Main.class);
                    view.startActivity(intent);
                }else{
                    Log.d("test",response.toString());
                }
                dialog_progress.dismiss();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog_progress.dismiss();
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }
}
