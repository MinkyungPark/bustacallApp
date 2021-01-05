package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;

/**
 * Created by user on 2016-10-29.
 */
public class SlidingMenuFragment extends Fragment implements View.OnClickListener{
    TextView tv_nickname;
    ViewGroup ll_use_list,ll_tourist,ll_my_point,ll_inqulry,ll_setting;
    AppController app;

    public SlidingMenuFragment(){}

    public static SlidingMenuFragment newInstance(){
        return new SlidingMenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sliding_menu, container, false);

        app = (AppController)getActivity().getApplicationContext();
        // Instantiate image view object of the user avatar and attaching onClick listener to it.
        tv_nickname = (TextView)rootView.findViewById(R.id.sliding_menu_tv_name);

        ll_use_list = (LinearLayout)rootView.findViewById(R.id.sliding_menu_ll_use_list);
        ll_tourist = (LinearLayout)rootView.findViewById(R.id.sliding_menu_ll_tourist);
        ll_my_point = (LinearLayout)rootView.findViewById(R.id.sliding_menu_ll_my_point);
        ll_inqulry = (LinearLayout)rootView.findViewById(R.id.sliding_menu_ll_inqulry);
        ll_setting = (LinearLayout)rootView.findViewById(R.id.sliding_menu_ll_setting);
        ll_use_list.setOnClickListener(this);
        ll_my_point.setOnClickListener(this);
        ll_tourist.setOnClickListener(this);
        ll_inqulry.setOnClickListener(this);
        ll_setting.setOnClickListener(this);

        tv_nickname.setText(app.getUser().getUser_nickname());

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sliding_menu_ll_use_list){
            goTouserList();
        }else if(v.getId()==R.id.sliding_menu_ll_tourist){
            goTotourist();
        }else if(v.getId()==R.id.sliding_menu_ll_inqulry){
            goToinqulry();
        }else if(v.getId()==R.id.sliding_menu_ll_my_point){
            goTomypoint();
        }else if(v.getId()==R.id.sliding_menu_ll_setting){
            goTosetting();
        }
    }

    public void goTouserList(){
        Intent intent = new Intent(getContext(),Activity_Sliding_Use_List.class);
        startActivity(intent);
    }
    public void goTotourist(){
        Intent intent = new Intent(getContext(),Activity_Sliding_Tourist.class);
        startActivity(intent);
    }

    public void goToinqulry() {
        Intent intent = new Intent(getContext(), Activity_Sliding_Inquiry.class);
        startActivity(intent);
    }

    public void goTomypoint() {
        Intent intent = new Intent(getContext(), Activity_Sliding_My_Point.class);
        startActivity(intent);
    }

    public void goTosetting() {
        Intent intent = new Intent(getContext(), Activity_Sliding_Setting.class);
        startActivity(intent);
    }

}
