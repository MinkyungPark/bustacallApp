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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_Calendar;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.bustacall.user.bustacall.dialog.Dialog_listview;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.User;
import com.bustacall.user.bustacall.presenter.Fragment_Main_GoandBack_Presenter;
import com.skp.Tmap.TMapPoint;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 2016-10-25.
 */
public class Fragment_Main_GoandBack extends BaseFragment implements View.OnClickListener{
    EditText et_user; //몇명 이용
    TextView tv_rental,tv_time_one,tv_time_two,tv_day_one,tv_day_two; //목적, 시간, 날짜
    TextView tv_startpoint_one,tv_startpoint_two,tv_endpoint_one,tv_endpoint_two; //시작점, 도착점
    TextView tv_enter;
    AppController app;
    ArrayList<String> str_rental,str_time_one; //배열
    boolean is_user;
    boolean is_startpoint_one;
    boolean is_startpoint_two;
    boolean is_endpoint_one;
    boolean is_endpoint_two; //선택됬는지 유무
    Fragment_Main_GoandBack_Presenter presenter;
    User user;
    Rental rental = new Rental();
    String nowTime;

    TMapPoint tmapPoint_start_one, tmapPoint_start_two, tmapPoint_end_one, tmapPoint_end_two; // Activity_MapView에서 받아온 위치 정보 저장

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_goandback, container, false);
        set_Weight(v);
        set_Values();
        return v;
    }

    public void set_Values(){
        presenter = new Fragment_Main_GoandBack_Presenter(this);
        str_rental = new ArrayList<>();
        str_time_one = new ArrayList<>();
        str_rental.add("산악회");str_rental.add("여행");str_rental.add("출퇴근");str_rental.add("수학여행");
        str_rental.add("현장학습");str_rental.add("기타");

        str_time_one.add("오전 1시");str_time_one.add("오전 2시");str_time_one.add("오전 3시");str_time_one.add("오전 4시");str_time_one.add("오전 5시");
        str_time_one.add("오전 6시");str_time_one.add("오전 7시");str_time_one.add("오전 8시");str_time_one.add("오전 9시");str_time_one.add("오전 10시");
        str_time_one.add("오전 11시");str_time_one.add("오전 12시");
        str_time_one.add("오후 1시");str_time_one.add("오후 2시");str_time_one.add("오후 3시");str_time_one.add("오후 4시");str_time_one.add("오후 5시");str_time_one.add("오후 6시");
        str_time_one.add("오후 7시");str_time_one.add("오후 8시");str_time_one.add("오후 9시");str_time_one.add("오후 10시");str_time_one.add("오후 11시");str_time_one.add("오후 12시");

        ////////////////////////////////처음 초기화///////////////////////////////
        app.setRental(rental);

        nowTime=presenter.getnowtime();
        tv_day_one.setText(nowTime);
        tv_day_two.setText(nowTime);

        rental.setDay_one(tv_day_one.getText().toString());
        rental.setDay_two(tv_day_two.getText().toString());
        rental.setTime_one(tv_time_one.getText().toString());
        rental.setTime_two(tv_time_two.getText().toString());
        rental.setRental_reason(tv_rental.getText().toString());
        rental.setType(1);//왕복
        rental.setCurrent_day(presenter.getnowtime_two());
        rental.getTogether().setFlag(0);
        /////////////////////////////////////////////////////////////////////////////

        //시간 추가
    }

    public void set_Weight(View v){
        app = (AppController)getActivity().getApplicationContext();
        et_user = (EditText)v.findViewById(R.id.fragment_goandback_et_user);
        tv_rental = (TextView) v.findViewById(R.id.fragment_goandback_tv_rental);
        tv_time_one = (TextView) v.findViewById(R.id.fragment_goandback_tv_time);
        tv_time_two = (TextView) v.findViewById(R.id.fragment_goandback_tv_time_two);
        tv_day_one = (TextView)v.findViewById(R.id.fragment_goandback_tv_day_one);
        tv_day_two = (TextView)v.findViewById(R.id.fragment_goandback_tv_day_two);
        tv_startpoint_one = (TextView)v.findViewById(R.id.fragment_goandback_tv_startpoint_one);
        tv_endpoint_one = (TextView) v.findViewById(R.id.fragment_goandback_tv_endpoint_one);
        tv_startpoint_two = (TextView)v.findViewById(R.id.fragment_goandback_tv_startpoint_two);
        tv_endpoint_two=(TextView)v.findViewById(R.id.fragment_goandback_tv_endpoint_two);
        tv_enter=(TextView)v.findViewById(R.id.fragment_goandback_tv_enter);
        tv_enter.setOnClickListener(this);
        setEnableTextView(tv_enter,false);
        tv_rental.setOnClickListener(this);
        tv_time_one.setOnClickListener(this);
        tv_time_two.setOnClickListener(this);
        tv_day_one.setOnClickListener(this);
        tv_day_two.setOnClickListener(this);
        tv_startpoint_one.setOnClickListener(this);
        tv_startpoint_two.setOnClickListener(this);
        tv_endpoint_one.setOnClickListener(this);
        tv_endpoint_two.setOnClickListener(this);
        et_user.addTextChangedListener(tw_user);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fragment_goandback_tv_rental){
            rentalList();
        }else if(v.getId()==R.id.fragment_goandback_tv_time){
            timeOne();
        }else if(v.getId()==R.id.fragment_goandback_tv_time_two){
            timeTwo();
        }else if(v.getId()==R.id.fragment_goandback_tv_day_one){
            showDayOne();
        }else if(v.getId()==R.id.fragment_goandback_tv_day_two){
            showDayTwo();
        }else if(v.getId()==R.id.fragment_goandback_tv_startpoint_one){
            startpointone();
        }else if(v.getId()==R.id.fragment_goandback_tv_endpoint_one){
            startpointtwo_and_endpointone();
        }else if(v.getId()==R.id.fragment_goandback_tv_enter){
            //다음화면
//            app.getUser().getRental_list().set(app.RENTAL_COUNT,rental); //현재 유저의 렌탈 세팅.
            app.setRental(rental);
            ////////////////////////////////////////////////////////
            Intent intent = new Intent(getActivity().getApplication(), Activity_BudgetQuoteCal.class);
            intent.putExtra("start_one_latitude", tmapPoint_start_one.getLatitude());
            intent.putExtra("start_one_longitude", tmapPoint_start_one.getLongitude());
            intent.putExtra("endone_starttwo_latitude", tmapPoint_end_one.getLatitude());
            intent.putExtra("endone_starttwo_longitude", tmapPoint_end_one.getLongitude());
            intent.putExtra("end_two_latitude", tmapPoint_end_two.getLatitude());
            intent.putExtra("end_two_longitude", tmapPoint_end_two.getLongitude());
            startActivity(intent);
        }
    }

    public void checkButton(){
        if(is_user==true&&is_endpoint_one==true&&is_endpoint_two==true&&is_startpoint_one==true&&is_startpoint_two==true)
        {
            setEnableTextView(tv_enter,true);
        }
    }

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
    }

    public void startpointtwo_and_endpointone(){
        if(is_endpoint_one && is_startpoint_two) {
            final Dialog_base_two_button dialog = new Dialog_base_two_button(getContext(), "다시 설정하시겠습니까?");
            dialog.show();
            dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    is_endpoint_one = false;
                    is_startpoint_two = false;
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
    }

    public void timeOne(){

          final Dialog_listview dialog_listview = new Dialog_listview(getContext(),"출발 시간",str_time_one);
        dialog_listview.show();
        dialog_listview.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_time_one.setText(str_time_one.get(position));
                rental.setTime_one(tv_time_one.getText().toString());
                dialog_listview.dismiss();
            }
        });
    }

    public void timeTwo(){
        final Dialog_listview dialog_listview = new Dialog_listview(getContext(),"출발 시간",str_time_one);
        dialog_listview.show();
        dialog_listview.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_time_two.setText(str_time_one.get(position));
                rental.setTime_two(tv_time_two.getText().toString());
                dialog_listview.dismiss();
            }
        });
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

    public void showDayOne(){
        final Dialog_Calendar dialog_calendar = new Dialog_Calendar(getContext());
        dialog_calendar.show();
        dialog_calendar.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv_day_one.setText(year+". "+(month+1)+". "+dayOfMonth+". "+findDay(year,month,dayOfMonth));
                dialog_calendar.dismiss();
                rental.setDay_one(tv_day_one.getText().toString());
            }
        });
    }

    public void showDayTwo(){
        final Dialog_Calendar dialog_calendar = new Dialog_Calendar(getContext());
        dialog_calendar.show();
        dialog_calendar.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv_day_two.setText(year+". "+(month+1)+". "+dayOfMonth+". "+findDay(year,month,dayOfMonth));
                rental.setDay_two(tv_day_two.getText().toString());
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

    public void setIs_user(boolean is_user) {
        this.is_user = is_user;
    }

    public void setIs_startpoint_one(boolean is_startpoint_one) {
        this.is_startpoint_one = is_startpoint_one;
        if(is_startpoint_one){
            tv_startpoint_one.setTextColor(Color.BLACK);
        }
    }

    public void setIs_startpoint_two(boolean is_startpoint_two) {
        this.is_startpoint_two = is_startpoint_two;
    }

    public void setIs_endpoint_one(boolean is_endpoint_one) {
        this.is_endpoint_one = is_endpoint_one;
        if(is_endpoint_one){
            tv_endpoint_one.setTextColor(Color.BLACK);
        }
    }

    public void setIs_endpoint_two(boolean is_endpoint_two) {
        this.is_endpoint_two = is_endpoint_two;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) // 액티비티가 정상적으로 종료되었을 경우
        {
            String selectPlace = data.getStringExtra("selectPlace");
            String start_region = data.getStringExtra("region");
            String end_region_two = data.getStringExtra("region_two");

            if (requestCode == 1) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
                tv_startpoint_one.setText(selectPlace);
                rental.setStart_point_one(selectPlace);
                presenter.getis_startpoint_one(tv_startpoint_one.getText().toString());
                tv_endpoint_two.setText(selectPlace);
                rental.setEnd_point_two(selectPlace);
                rental.setStart_region(start_region);
                presenter.getis_endpoint_two(tv_endpoint_two.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_end_two = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                }
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_start_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                    Log.d("test", tmapPoint_start_one.toString());
                }

            } else if (requestCode == 2) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
                rental.setEnd_region_two(end_region_two);
                rental.setEnd_region(start_region);
                tv_endpoint_one.setText(selectPlace);
                tv_startpoint_two.setText(selectPlace);
                rental.setEnd_point_one(selectPlace);
                rental.setStart_point_two(selectPlace);
                presenter.getis_endpoint_one(tv_endpoint_one.getText().toString());
                presenter.getis_startpoint_two(tv_startpoint_two.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_end_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                    tmapPoint_start_two = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                }
            }
            checkButton();
        }
    }
}
//ap.getUser().getRental_list().set(app.RENTAL_COUNT,rental); 이걸 마지막에 해준다.