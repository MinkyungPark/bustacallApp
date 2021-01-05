package com.bustacall.user.bustacall.presenter;

import android.view.View;

import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.view.Activity_Payment;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-05.
 */
public class Activity_Payment_Presenter {
    Activity_Payment view;
    AppController app;

    public Activity_Payment_Presenter(Activity_Payment view) {
        this.view = view;
        app = (AppController) view.getApplicationContext();
    }

    public void request_payment(String nickname, int rental_num, String bus_nickname,final String bus_money) {
        final int rental_num_temp = rental_num;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_payment(nickname, rental_num, bus_nickname);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {//성공하면
                    for(int i=0;i<app.getUser().getRental_list().size();i++){
                        if(app.getUser().getRental_list().get(i).getRental_num() == rental_num_temp){
                            app.getUser().getRental_list().get(i).setType_two(3);
                            app.getUser().getRental_list().get(i).setRental_money(bus_money);
                            final Dialog_base dialog_base = new Dialog_base(view, "예약이 완료되었습니다.");
                            dialog_base.show();
                            dialog_base.getTv_button().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    view.finish();
                                    dialog_base.dismiss();
                                }
                            });
                            return;
                        }
                    }
                }else{
                    final Dialog_base dialog_base = new Dialog_base(view,view.getString(R.string.requestfail));
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
}
