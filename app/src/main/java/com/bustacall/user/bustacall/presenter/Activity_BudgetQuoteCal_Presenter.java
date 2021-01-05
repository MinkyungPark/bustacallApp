package com.bustacall.user.bustacall.presenter;

import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.view.Activity_BudgetQuoteCal;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-10-31.
 */
public class Activity_BudgetQuoteCal_Presenter {
    Activity_BudgetQuoteCal view;
    AppController app;

    public Activity_BudgetQuoteCal_Presenter(Activity_BudgetQuoteCal view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    public void request_lent(String nickname,Rental lent) {
        app.getRental().setType_two(1); //예약중
        lent.setNickname(app.getUser().getUser_nickname());
        /////////////////////////////////////////////////////////////////
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<JsonObject> retrofitinfo = retrofit_user.request_lent(lent);
        retrofitinfo.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    int i = response.body().get("rental_num").getAsInt();
                    app.getRental().setRental_num(i);
                    final Dialog_base dialog_base = new Dialog_base(view,"예약 완료되었습니다.");
                    dialog_base.show();
                    app.getUser().getRental_list().add(app.getRental());
                    Rental rental = new Rental(); //해당하는 렌탈 초기화
                    app.setRental(rental);
                    app.RENTAL_COUNT++;//하나 증가
                }else{
                    final Dialog_base dialog_base = new Dialog_base(view,view.getString(R.string.requestfail));
                    dialog_base.show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }
}