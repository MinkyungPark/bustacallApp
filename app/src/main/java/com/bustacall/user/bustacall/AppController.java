package com.bustacall.user.bustacall;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Rental_List;
import com.bustacall.user.bustacall.model.User;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by user on 2016-10-25.
 */
public class AppController extends Application {
    public final static String SERVERIP="http://203.252.166.242:8080";
    public final static String TOURAPI="VJJG%2BuKFWVvOmLygIhpDScCEInuXX%2F6IVTF%2B%2FpvKylgONwTn5k9JDbtbTCmKe84Ahg2EI3q3uQdvDGNnIi44mw%3D%3D";

    public static int RENTAL_COUNT=0;
    Rental_List rental_list = new Rental_List(); //합승 확인하기 위한 list
    String pushToken; //토큰
    User user; //한명의 유저
    String rental_num;
    Rental rental = new Rental(); //마지막 예약할때 보내는 rental

    public String getRental_num() {
        return rental_num;
    }

    public void setRental_num(String rental_num) {
        this.rental_num = rental_num;
    }

    public Rental_List getRental_list() {
        return rental_list;
    }

    public void setRental_list(Rental_List rental_list) {
        this.rental_list = rental_list;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPushToken() {
        String push_temp = getSavedToken("token","null");
        return push_temp;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getSavedId(String key, String dftValue) {
        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public void setSavedId(String id){
        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //id에 첫번째 값 가져와서 집어넣기.
        editor.putString("id",id);
        editor.commit();
    }

    public String getSavedToken(String key, String dftValue) {
        SharedPreferences pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public void setSavedToken(String token){
        SharedPreferences pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //id에 첫번째 값 가져와서 집어넣기.
        editor.putString("token",token);
        editor.commit();
    }

    @Override
    public void onCreate() {
//        pushToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d("pushTo",pushToken);

        super.onCreate();
    }
}
