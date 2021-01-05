package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.dialog.Dialog_base_payment;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.view.Activity_Together_Sit_Detail;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-13.
 */
public class Activity_Together_Sit_Detail_Presenter {
    Activity_Together_Sit_Detail view;
    AppController app;
    public Activity_Together_Sit_Detail_Presenter(Activity_Together_Sit_Detail view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    public void request_add_user_together(Rental rental,final int count){
        final Rental rental_temp = rental;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<JsonObject> retrofitinfo = retrofit_user.request_add_user_together(app.getUser().getUser_nickname(),rental.getRental_num(),count);
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    int i = response.body().get("flag").getAsInt();
                    if(i==1){//성공
                        Dialog_base_payment dialog_base_payment = new Dialog_base_payment(view);
                        dialog_base_payment.show();
                        int count_temp = rental_temp.getTogether().getCurrent_user_count();
                        rental_temp.getTogether().setCurrent_user_count(count+count_temp);
                        app.getUser().getRental_list_together().add(rental_temp);
                        Log.d("app",app.toString());
                    }else if(i==2){
                        Dialog_base dialog_base = new Dialog_base(view,"자신이 올린 매물입니다.");
                        dialog_base.show();
                    }
                    else if(i==3){
                        Dialog_base dialog_base = new Dialog_base(view,"이미 신청한 매물입니다.");
                        dialog_base.show();
                    }
                }else{
                    Log.d("t",response.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("t",t.toString());
            }
        });
    }
}
