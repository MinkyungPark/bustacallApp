package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.model.Rental_List;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.ReasonDevision;
import com.bustacall.user.bustacall.dialog.Dialog_Progress;
import com.bustacall.user.bustacall.view.Activity_Together_Reason;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-11.
 */
public class Activity_Together_Reason_Presenter {
    Activity_Together_Reason view;
    AppController app;
    ReasonDevision reasonDevision;


    public Activity_Together_Reason_Presenter(Activity_Together_Reason view){
        this.view =view;
        app = (AppController)view.getApplicationContext();
    }

    public void request_get_rental(final String start_region, final String reason){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_rental();
        final Dialog_Progress dialog_progress = new Dialog_Progress(view);
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if(response.isSuccessful()){
                    reasonDevision = new ReasonDevision(start_region,reason,response.body());
                    app.setRental_list(reasonDevision.setRegion_list_reason());

                    Log.d("app",app.toString());
                    view.set_Weight(app.getRental_list());
                }else{

                }
                dialog_progress.dismiss();
            }

            @Override
            public void onFailure(Call<Rental_List> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }
}
