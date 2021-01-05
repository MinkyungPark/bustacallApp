package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.view.Activity_Make_Together;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-12.
 */
public class Activity_Make_Together_Presenter {
    Activity_Make_Together view;
    AppController app;
    public Activity_Make_Together_Presenter(Activity_Make_Together view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }
    public void getis_startpoint_one(String str){
        if(str.equals("설정하기")){
            view.setIs_startpoint_one(false);
        }else{
            view.setIs_startpoint_one(true);
        }
    }

    public void getis_endpoint_one(String str){
        if(str.equals("설정하기")){
            view.setIs_endpoint_one(false);
        }else{
            view.setIs_endpoint_one(true);
        }
    }

    public void getis_bus_type(String str){
        if(str.equals("25,28,35,45인승")){
            view.setIs_bus_type(false);
        }else{
            view.setIs_bus_type(true);
        }
    }

    public void getis_text(String str){
        if(str.equals("글을 작성해주세요")){
            view.setIs_text(false);
        }else{
            view.setIs_text(true);
        }
    }

    public String getnowtime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy. MM. dd.");
        SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH시 mm분");
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");


        String strCurDate = CurDateFormat.format(date);
        String strCurTime = CurTimeFormat.format(date);
        String strCurYear = CurYearFormat.format(date);
        String strCurMonth = CurMonthFormat.format(date);
        String strCurDay = CurDayFormat.format(date);
        String strCurHour = CurHourFormat.format(date);
        String strCurMinute = CurMinuteFormat.format(date);

        String day = view.findDay(Integer.parseInt(strCurYear),Integer.parseInt(strCurMonth)-1,Integer.parseInt(strCurDay));
        return strCurYear+". "+strCurMonth+". "+strCurDay+". "+day;
    }

    public String getnowtime_two(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy. MM. dd.");
        SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH시 mm분");
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");


        String strCurDate = CurDateFormat.format(date);
        String strCurTime = CurTimeFormat.format(date);
        String strCurYear = CurYearFormat.format(date);
        String strCurMonth = CurMonthFormat.format(date);
        String strCurDay = CurDayFormat.format(date);
        String strCurHour = CurHourFormat.format(date);
        String strCurMinute = CurMinuteFormat.format(date);

        String day = view.findDay(Integer.parseInt(strCurYear),Integer.parseInt(strCurMonth)-1,Integer.parseInt(strCurDay));
        return strCurYear+"-"+strCurMonth+"-"+strCurDay;
    }

    public void request_set_rental_together(Rental rental){
        Log.d("Text",rental.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_set_rental_together(rental.getStart_point_one(),rental.getEnd_point_one(),rental.getStart_region(),rental.getEnd_region(),
                rental.getTogether().getText(),app.getUser().getUser_nickname(),rental.getTogether().getMax_user_count(),rental.getTogether().getCurrent_user_count(),
                rental.getTogether().getMoney(),rental.getTogether().getFlag(),rental.getType(),rental.getDay_one(),rental.getRental_reason(),rental.getTime_one(),rental.getRental_money(),rental.getTogether().getType_flag());
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                Dialog_base dialog_base = new Dialog_base(view,"그룹을 생성하였습니다.");
                dialog_base.show();
                }
                else{
                    Log.d("text",response.toString());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("text",t.toString());
            }
        });
    }
}
