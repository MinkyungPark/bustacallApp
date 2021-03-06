package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.bustacall.user.bustacall.dialog.Dialog_listview;
import com.bustacall.user.bustacall.presenter.Fragment_Main_Goal_Presenter;
import com.skp.Tmap.TMapPoint;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-26.
 */
public class Fragment_Main_Goal extends BaseFragment implements View.OnClickListener{
    EditText et_user; //몇명 이용
    TextView tv_rental;
    TextView tv_startpoint_one; //시작점, 도착점
    TextView tv_enter;
    AppController app;
    ArrayList<String> str_rental; //배열
    boolean is_user;
    boolean is_startpoint_one;
    Fragment_Main_Goal_Presenter presenter;
    Rental rental = new Rental();

    TMapPoint tmapPoint_start_one;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_goal, container, false);
        set_Weight(v);
        set_Values();
        return v;
    }
    public void set_Weight(View v){
        app = (AppController)getActivity().getApplicationContext();
        et_user = (EditText)v.findViewById(R.id.fragment_goal_et_user);
        tv_startpoint_one = (TextView)v.findViewById(R.id.fragment_goal_tv_startpoint_one);
        tv_enter=(TextView)v.findViewById(R.id.fragment_goal_tv_enter);
        tv_rental=(TextView)v.findViewById(R.id.fragment_goal_tv_rental);
        tv_enter.setOnClickListener(this);
        tv_startpoint_one.setOnClickListener(this);
        tv_rental.setOnClickListener(this);
        et_user.addTextChangedListener(tw_user);
        setEnableTextView(tv_enter,false);
    }
    public void set_Values(){
        presenter = new Fragment_Main_Goal_Presenter(this);
        str_rental = new ArrayList<>();
        str_rental.add("산악회");str_rental.add("여행");str_rental.add("출퇴근");str_rental.add("수학여행");
        str_rental.add("현장학습");str_rental.add("기타");

        //////////////////////////////////초기화///////////////////////////
        app.setRental(rental);
        rental.setRental_reason(tv_rental.getText().toString());

        //////////////////////////////////////////////////////////////////
        }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fragment_goal_tv_rental){
            rentalList();
        }else if(v.getId()==R.id.fragment_goal_tv_startpoint_one){
            startpointone();
        }else if(v.getId()==R.id.fragment_goal_tv_enter){
            //다음화면
            app.setRental(rental);
            ////////////////////////////////////////////////////
            //TODO 다음화면
            Intent intent = new Intent(getContext(),Activity_Together_Reason.class);
            intent.putExtra("user",Integer.parseInt(et_user.getText().toString()));
            startActivity(intent);
        }
    }

    TextWatcher tw_user = new TextWatcher() { //user 명수 확인
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            rental.setUser_count(et_user.getText().toString());
            presenter.getis_Usercount(et_user.getText().toString());
            checkButton();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void startpointone(){
        if(is_startpoint_one) {
            final Dialog_base_two_button dialog = new Dialog_base_two_button(getContext(), "다시 설정하시겠습니까?");
            dialog.show();
            dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    is_startpoint_one = false;
                    Intent intent = new Intent(getActivity().getApplication(),Activity_SearchMap.class);
                    startActivityForResult(intent, 1);
                    dialog.dismiss();
                }
            });
            dialog.getTv_cancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }else {
            Intent intent = new Intent(getActivity().getApplication(),Activity_SearchMap.class);
            startActivityForResult(intent, 1);
        }
        checkButton();
    }

    public void rentalList(){

        final Dialog_listview dialog_listview = new Dialog_listview(getContext(),"대절 목적 선택",str_rental);
        dialog_listview.show();
        dialog_listview.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_rental.setText(str_rental.get(position));
                rental.setRental_reason(tv_rental.getText().toString());
                dialog_listview.dismiss();
            }
        });
    }


    public void checkButton(){
        if(is_startpoint_one==true&&is_user==true){
            setEnableTextView(tv_enter,true);
        }
    }

    public void setIs_startpoint_one(boolean is_startpoint_one) {
        this.is_startpoint_one = is_startpoint_one;
        if(is_startpoint_one){
            tv_startpoint_one.setTextColor(Color.BLACK);
        }
    }

    public void setIs_user(boolean is_user) {
        this.is_user = is_user;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) // 액티비티가 정상적으로 종료되었을 경우
        {
            String selectPlace = data.getStringExtra("selectPlace");
            String region = data.getStringExtra("region");
            if (requestCode == 1) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
                rental.setStart_region(region);
                tv_startpoint_one.setText(selectPlace);
                rental.setStart_point_one(selectPlace);
                presenter.getis_startpoint_one(tv_startpoint_one.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_start_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                    Log.d("test", tmapPoint_start_one.toString());
                }

            }
            checkButton();
        }
    }
}
