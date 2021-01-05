package com.bustacall.user.bustacall.presenter;

import com.bustacall.user.bustacall.view.Fragment_Main_GoandBack;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2016-10-26.
 */
public class Fragment_Main_GoandBack_Presenter {
    Fragment_Main_GoandBack view;
    public Fragment_Main_GoandBack_Presenter(Fragment_Main_GoandBack view){
        this.view = view;
    }

    public void getis_Usercount(String str){
        if(str.equals("")){
            view.setIs_user(false);
        }else{
            view.setIs_user(true);
        }
    }

    public void getis_startpoint_one(String str){
        if(str.equals("설정하기")){
            view.setIs_startpoint_one(false);
        }else{
            view.setIs_startpoint_one(true);
        }
    }

    public void getis_startpoint_two(String str){
        if(str.equals("설정하기")){
            view.setIs_startpoint_two(false);
        }else{
            view.setIs_startpoint_two(true);
        }
    }

    public void getis_endpoint_one(String str){
        if(str.equals("설정하기")){
            view.setIs_endpoint_one(false);
        }else{
            view.setIs_endpoint_one(true);
        }
    }

    public void getis_endpoint_two(String str){
        if(str.equals("설정하기")){
            view.setIs_endpoint_two(false);
        }else{
            view.setIs_endpoint_two(true);
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
}
