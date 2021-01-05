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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.dialog.Dialog_Calendar;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.presenter.Fragment_Main_Destination_Presenter;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.skp.Tmap.TMapPoint;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 2016-10-26.
 */
public class Fragment_Main_Destination extends BaseFragment implements View.OnClickListener{
    EditText et_user; //몇명 이용
    TextView tv_day_one; //시간
    TextView tv_startpoint_one,tv_endpoint_one; //시작점, 도착점
    TextView tv_enter;
    AppController app;
    ArrayList<String> str_time_one; //배열
    boolean is_user;
    boolean is_startpoint_one;
    boolean is_endpoint_one; //선택됬는지 유무
    Fragment_Main_Destination_Presenter presenter;
    Rental rental = new Rental();
    String nowTime;

    TMapPoint tmapPoint_start_one,tmapPoint_end_one; // Activity_MapView에서 받아온 위치 정보 저장

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_destination, container, false);
        set_Weight(v);
        set_Values();
        return v;
    }
    public void set_Weight(View v){
        app = (AppController)getActivity().getApplicationContext();
        et_user = (EditText)v.findViewById(R.id.fragment_destination_et_user);
        tv_day_one = (TextView)v.findViewById(R.id.fragment_destination_tv_day_one);
        tv_startpoint_one = (TextView)v.findViewById(R.id.fragment_destination_tv_startpoint_one);
        tv_endpoint_one = (TextView) v.findViewById(R.id.fragment_destination_tv_endpoint_one);
        tv_enter=(TextView)v.findViewById(R.id.fragment_destination_tv_enter);
        tv_enter.setOnClickListener(this);
        tv_day_one.setOnClickListener(this);
        tv_startpoint_one.setOnClickListener(this);
        tv_endpoint_one.setOnClickListener(this);
        et_user.addTextChangedListener(tw_user);
        setEnableTextView(tv_enter,false);
    }
    public void set_Values(){
        presenter = new Fragment_Main_Destination_Presenter(this);
        str_time_one = new ArrayList<>();
        str_time_one.add("오전 1시");str_time_one.add("오전 2시");str_time_one.add("오전 3시");str_time_one.add("오전 4시");str_time_one.add("오전 5시");
        str_time_one.add("오전 6시");str_time_one.add("오전 7시");str_time_one.add("오전 8시");str_time_one.add("오전 9시");str_time_one.add("오전 10시");
        str_time_one.add("오전 11시");str_time_one.add("오전 12시");
        str_time_one.add("오후 1시");str_time_one.add("오후 2시");str_time_one.add("오후 3시");str_time_one.add("오후 4시");str_time_one.add("오후 5시");str_time_one.add("오후 6시");
        str_time_one.add("오후 7시");str_time_one.add("오후 8시");str_time_one.add("오후 9시");str_time_one.add("오후 10시");str_time_one.add("오후 11시");str_time_one.add("오후 12시");

        //////////////////////////////////초기화///////////////////////////
        app.setRental(rental);

        nowTime=presenter.getnowtime();
        tv_day_one.setText(nowTime);

        //////////////////////////////////////////////////////////////////
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fragment_destination_tv_day_one){
            showDayOne();
        }else if(v.getId()==R.id.fragment_destination_tv_startpoint_one){
            startpointone();
        }else if(v.getId()==R.id.fragment_destination_tv_endpoint_one){
            endpointone();
        }else if(v.getId()==R.id.fragment_destination_tv_enter){
            //다음화면
            app.setRental(rental);
            ////////////////////////////////////////////////////////
            //TODO 다음 화면
            Intent intent = new Intent(getContext(),Activity_Together_Destination.class);
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

    public void endpointone(){
        if(is_endpoint_one) {
            final Dialog_base_two_button dialog = new Dialog_base_two_button(getContext(), "다시 설정하시겠습니까?");
            dialog.show();
            dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    is_endpoint_one = false;
                    dialog.dismiss();
                    Intent intent = new Intent(getActivity().getApplication(), Activity_SearchMap.class);
                    startActivityForResult(intent, 2);
                }
            });
            dialog.getTv_cancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }else {
            Intent intent = new Intent(getActivity().getApplication(), Activity_SearchMap.class);
            startActivityForResult(intent, 2);
        }
        checkButton();
    }

    public void checkButton(){
        if(is_endpoint_one==true&&is_startpoint_one==true&&is_user==true){
            setEnableTextView(tv_enter,true);
        }
    }
    public void showDayOne(){
        final Dialog_Calendar dialog_calendar = new Dialog_Calendar(getContext());
        dialog_calendar.show();
        dialog_calendar.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv_day_one.setText(year+". "+(month+1)+". "+dayOfMonth+". "+findDay(year,month,dayOfMonth));
                dialog_calendar.dismiss();
            }
        });
    }

    public String findDay(int year,int month,int dayOfMonth){
        Calendar cal= Calendar.getInstance ();
        String str="";
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, dayOfMonth);
        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case 1:
                str = "일";
                return str;
            case 2:
                str = "월";
                return str;
            case 3:
                str = "화";
                return str;
            case 4:
                str = "수";
                return str;
            case 5:
                str = "목";
                return str;
            case 6:
                str = "금";
                return str;
            case 7:
                str = "토";
                return str;
        }
        return str;
    }

    public void setIs_endpoint_one(boolean is_endpoint_one) {
        this.is_endpoint_one = is_endpoint_one;
        if(is_endpoint_one){
            tv_endpoint_one.setTextColor(Color.BLACK);
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
                presenter.getis_startpoint_one(tv_startpoint_one.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_start_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                    Log.d("test", tmapPoint_start_one.toString());
                }
            } else if (requestCode == 2) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
                rental.setEnd_region(region);
                tv_endpoint_one.setText(selectPlace);
                presenter.getis_endpoint_one(tv_endpoint_one.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_end_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                }
            }
            checkButton();
        }
    }
}
