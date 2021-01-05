package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.view.Activity_Reservation_List;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.dialog.Dialog_Progress;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Retrofit_User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-02.
 */
public class Activity_Reservation_List_Presenter {

    AppController app;
    Activity_Reservation_List view;
    public Activity_Reservation_List_Presenter(Activity_Reservation_List view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }


    public void request_reservinfo(int rental_num){
        /////////////////////////////////////////////////////////////////
        final Dialog_Progress dialog_progress = new Dialog_Progress(view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental> retrofitinfo = retrofit_user.request_reservinfo(String.valueOf(rental_num));
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Rental>() {
            @Override
            public void onResponse(Call<Rental> call, Response<Rental> response) {
                if(response.isSuccessful()){
                        for (int i = 0; i < app.getUser().getRental_list().size(); i++) {
                            if (response.body().getRental_num() == app.getUser().getRental_list().get(i).getRental_num()) {
                                app.getUser().getRental_list().get(i).setBus_list(response.body().getBus_list()); //여기서 받아와서 num과 동일한 놈한테 집어넣는다.
                                app.getUser().getRental_list().get(i).setType_two(response.body().getType_two());
                                app.getUser().getRental_list().get(i).setEnd_day(response.body().getEnd_day());
                                for(int j=0;j<app.getUser().getRental_list().get(i).getBus_list().size();j++)
                                    app.getUser().getRental_list().get(i).getBus_list().get(j).setUser_count(app.getUser().getRental_list().get(i).getUser_count());
                                view.set_Weight(app.getUser().getRental_list().get(i));
                                dialog_progress.dismiss();
                                return;
                            }
                        }
                }else{
                    Log.d("test",response.toString());
                    dialog_progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Rental> call, Throwable t) {
                Log.d("test",t.toString());
                dialog_progress.dismiss();
            }
        });
    }
}
