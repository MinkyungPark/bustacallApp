package com.bustacall.user.bustacall.presenter;

import com.bustacall.user.bustacall.view.Fragment_Main_Goal;

/**
 * Created by user on 2016-10-26.
 */
public class Fragment_Main_Goal_Presenter {
    Fragment_Main_Goal view;
    public Fragment_Main_Goal_Presenter(Fragment_Main_Goal view){
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
}
