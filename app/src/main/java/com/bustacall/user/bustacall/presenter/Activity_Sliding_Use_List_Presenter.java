package com.bustacall.user.bustacall.presenter;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.view.Activity_Sliding_Use_List;

/**
 * Created by user on 2016-11-02.
 */
public class Activity_Sliding_Use_List_Presenter {

    Activity_Sliding_Use_List view;
    AppController app;

    public Activity_Sliding_Use_List_Presenter(Activity_Sliding_Use_List view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

}
