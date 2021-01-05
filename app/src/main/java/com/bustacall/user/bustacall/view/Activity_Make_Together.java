package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bustacall.user.bustacall.dialog.Dialog_Text;
import com.bustacall.user.bustacall.presenter.Activity_Make_Together_Presenter;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_Calendar;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.bustacall.user.bustacall.dialog.Dialog_listview;
import com.bustacall.user.bustacall.model.Rental;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 2016-11-12.
 */
public class Activity_Make_Together extends BaseActivity implements View.OnClickListener {
    TextView tv_title;
    Spinner sp_bus_type;
    ArrayList<String> str_rental = new ArrayList<>();
    ArrayList<String> str_time_one = new ArrayList<>();
    TextView tv_start_point, tv_end_point, tv_day, tv_text, tv_phone_num, tv_money, tv_enter,tv_reason,tv_time;
    Boolean is_startpoint_one=false;
    Boolean is_endpoint_one=false;
    Boolean is_bus_type=false;
    Boolean is_text=false;
    TMapPoint tmapPoint_start_one, tmapPoint_end_one; // Activity_MapView에서 받아온 위치 정보 저장
    TMapData tMapData;
    Activity_Make_Together_Presenter presenter;
    Rental rental = new Rental();
    AppController app;
    String nowTime;
    ArrayList<String> sp_type = new ArrayList<>();
    ArrayAdapter<String> sp_adapter;
    String bus_type_num=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_together);
        set_Values();
        set_Weight();
    }

    public void set_Values() {
        presenter = new Activity_Make_Together_Presenter(this);
        app = (AppController)getApplicationContext();
    }

    public void set_Weight() {
        sp_type.add("25인승");sp_type.add("28인승");sp_type.add("35인승");sp_type.add("45인승");
        tv_time = (TextView)findViewById(R.id.activity_make_together_tv_time_one);
        tv_time.setOnClickListener(this);
        tv_reason = (TextView)findViewById(R.id.activity_make_together_tv_reason);
        tv_reason.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("합승 예약 모집");
        sp_bus_type = (Spinner) findViewById(R.id.activity_make_together_sp_bus_type);
        tv_start_point = (TextView) findViewById(R.id.activity_make_together_tv_startpoint_one);
        tv_end_point = (TextView) findViewById(R.id.activity_make_together_tv_endpoint_one);
        tv_day = (TextView) findViewById(R.id.activity_make_together_tv_day_one);
        tv_text = (TextView) findViewById(R.id.activity_make_together_tv_text);
        tv_phone_num = (TextView) findViewById(R.id.activity_make_together_tv_phone_num);
        tv_phone_num.setText(app.getUser().getUser_phonenum());
        tv_money = (TextView) findViewById(R.id.activity_make_together_tv_money);
        tv_enter = (TextView) findViewById(R.id.activity_make_together_tv_enter);
        tv_enter.setOnClickListener(this);
        tv_start_point.setOnClickListener(this);
        tv_end_point.setOnClickListener(this);
        tv_day.setOnClickListener(this);
        tv_text.setOnClickListener(this);
        setEnableTextView(tv_enter,false);
        bus_type_num = "25인승";
        str_rental = new ArrayList<>();str_rental.add("산악회");str_rental.add("여행");str_rental.add("출퇴근");str_rental.add("수학여행");
        str_rental.add("현장학습");str_rental.add("기타");
        str_time_one.add("오전 1시");str_time_one.add("오전 2시");str_time_one.add("오전 3시");str_time_one.add("오전 4시");str_time_one.add("오전 5시");
        str_time_one.add("오전 6시");str_time_one.add("오전 7시");str_time_one.add("오전 8시");str_time_one.add("오전 9시");str_time_one.add("오전 10시");
        str_time_one.add("오전 11시");str_time_one.add("오전 12시");
        str_time_one.add("오후 1시");str_time_one.add("오후 2시");str_time_one.add("오후 3시");str_time_one.add("오후 4시");str_time_one.add("오후 5시");str_time_one.add("오후 6시");
        str_time_one.add("오후 7시");str_time_one.add("오후 8시");str_time_one.add("오후 9시");str_time_one.add("오후 10시");str_time_one.add("오후 11시");str_time_one.add("오후 12시");

        sp_adapter = new ArrayAdapter<String>(this,R.layout.spinner_bus_type_item,sp_type);
        sp_bus_type.setAdapter(sp_adapter);
        sp_bus_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bus_type_num = (String)sp_bus_type.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //////////////////////////////////////////////////////////////////
        nowTime=presenter.getnowtime();
        tv_day.setText(nowTime);
        rental.setType(2);
        rental.setDay_one(tv_day.getText().toString());
        rental.getTogether().setFlag(2);//같이 타기
        rental.getTogether().setMoney(tv_money.getText().toString());
        rental.setRental_reason(tv_reason.getText().toString());
        rental.setTime_one(tv_time.getText().toString());
        tMapData = new TMapData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_make_together_tv_startpoint_one) {
            startpoint();
        } else if (v.getId() == R.id.activity_make_together_tv_endpoint_one) {
            endpoint();
        } else if (v.getId() == R.id.activity_make_together_tv_day_one) {
            dayOne();
        } else if (v.getId() == R.id.activity_make_together_tv_text) {
            setText();
        } else if (v.getId() == R.id.activity_make_together_tv_enter) {
            nextTopage();
        } else if(v.getId() == R.id.activity_make_together_tv_reason){
            setReason();
        } else if(v.getId() == R.id.activity_make_together_tv_time_one){
            timeOne();
        }
    }

    public void timeOne(){
        final Dialog_listview dialog_listview = new Dialog_listview(this,"출발 시간",str_time_one);
        dialog_listview.show();
        dialog_listview.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_time.setText(str_time_one.get(position));
                rental.setTime_one(tv_time.getText().toString());
                dialog_listview.dismiss();
            }
        });
    }

    public void setReason(){
            final Dialog_listview dialog_listview = new Dialog_listview(this,"대절 목적 선택",str_rental);
            dialog_listview.show();
            dialog_listview.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    tv_reason.setText(str_rental.get(position));
                    rental.setRental_reason(tv_reason.getText().toString());
                    dialog_listview.dismiss();
                }
            });
    }

    public void startpoint() {
        if(is_startpoint_one) {
            final Dialog_base_two_button dialog = new Dialog_base_two_button(this, "다시 설정하시겠습니까?");
            dialog.show();
            dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    is_startpoint_one = false;
                    Intent intent = new Intent(getApplicationContext(),Activity_SearchMap.class);
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
            Intent intent = new Intent(this,Activity_SearchMap.class);
            startActivityForResult(intent, 1);
        }
    }

    public void endpoint() {
        if(is_endpoint_one) {
            final Dialog_base_two_button dialog = new Dialog_base_two_button(this, "다시 설정하시겠습니까?");
            dialog.show();
            dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    is_endpoint_one = false;
                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), Activity_SearchMap.class);
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
            Intent intent = new Intent(this.getApplication(), Activity_SearchMap.class);
            startActivityForResult(intent, 2);
        }
    }

    public void dayOne() {
        final Dialog_Calendar dialog_calendar = new Dialog_Calendar(this);
        dialog_calendar.show();
        dialog_calendar.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv_day.setText(year+". "+(month+1)+". "+dayOfMonth+". "+findDay(year,month,dayOfMonth));
                rental.setDay_one(tv_day.getText().toString());
                dialog_calendar.dismiss();
            }
        });
    }

    public void setText() {
        final Dialog_Text dialog_text = new Dialog_Text(this);
        dialog_text.show();
        dialog_text.getTv_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rental.getTogether().setText(dialog_text.getEt_text().getText().toString());
                tv_text.setText(dialog_text.getEt_text().getText().toString());
                dialog_text.dismiss();
                presenter.getis_text(tv_text.getText().toString());
                tv_text.setTextColor(Color.parseColor("#000000"));
                checkButton();
            }
        });

    }

    public void nextTopage() {

        switch (bus_type_num){
            case "25인승":
                bus_type_num = "25";
                break;
            case "28인승":
                bus_type_num = "28";
                break;
            case "35인승":
                bus_type_num = "35";
                break;
            case "45인승":
                bus_type_num = "45";
                break;
        }
        rental.getTogether().setMax_user_count(Integer.parseInt(bus_type_num));
        rental.getTogether().setCurrent_user_count(1);
        rental.getTogether().setType_flag(1);
        app.getUser().getRental_list_together().add(rental);
        presenter.request_set_rental_together(rental);
    }

    public void setRental_money(){
        tMapData.findPathData(tmapPoint_start_one, tmapPoint_end_one, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(final TMapPolyLine tmappolyline) {
                tmappolyline.setLineColor(Color.BLUE);
//                tmapview.setTMapPathIcon(startPin, endPin);
//                tmapview.addTMapPath(tmappolyline);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                // 해당 작업을 처리함
                                String money = null;
                                int distance = (int)tmappolyline.getDistance()/1000;
                                if(distance <= 100){
                                    money = "550000";
                                }else if(distance <=150 ){
                                    money= "650000";
                                }else if(distance <=200){
                                    money = "750000";
                                }else if(distance <=250){
                                    money = "850000";
                                }else if(distance <=300){
                                    money = "900000";
                                }else if(distance <=350){
                                    money = "950000";
                                }else if(distance <=400){
                                    money = "1000000";
                                }else if(distance <=450){
                                    money = "1050000";
                                } else if (distance <= 500) {
                                    money = "1100000";
                                }
                                rental.setRental_money(money);
                            }
                        });
                    }
                }).start();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) // 액티비티가 정상적으로 종료되었을 경우
        {
            String selectPlace = data.getStringExtra("selectPlace");
            String start_region = data.getStringExtra("region");

            if (requestCode == 1) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
                tv_start_point.setText(selectPlace);
                tv_start_point.setTextColor(Color.parseColor("#000000"));
                rental.setStart_point_one(selectPlace);
                rental.setStart_region(start_region);
                presenter.getis_startpoint_one(tv_start_point.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_start_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                    Log.d("test", tmapPoint_start_one.toString());
                }

            } else if (requestCode == 2) // requestCode==1 로 호출한 경우에만 처리합니다.
            {
                rental.setEnd_region(start_region);
                tv_end_point.setTextColor(Color.parseColor("#000000"));
                tv_end_point.setText(selectPlace);
                rental.setEnd_point_one(selectPlace);
                presenter.getis_endpoint_one(tv_end_point.getText().toString());
                if (!selectPlace.equals("설정하기")) {
                    tmapPoint_end_one = new TMapPoint(data.getDoubleExtra("latitude", 0), data.getDoubleExtra("longitude", 0));
                }
                setRental_money();
            }
            checkButton();
        }
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
    public void checkButton(){
        if(is_endpoint_one==true&&is_startpoint_one==true&&is_text==true)
        {
            setEnableTextView(tv_enter,true);
        }
    }
    public Boolean getIs_startpoint_one() {
        return is_startpoint_one;
    }

    public void setIs_startpoint_one(Boolean is_startpoint_one) {
        this.is_startpoint_one = is_startpoint_one;
    }

    public Boolean getIs_endpoint_one() {
        return is_endpoint_one;
    }

    public void setIs_endpoint_one(Boolean is_endpoint_one) {
        this.is_endpoint_one = is_endpoint_one;
    }

    public Boolean getIs_bus_type() {
        return is_bus_type;
    }

    public void setIs_bus_type(Boolean is_bus_type) {
        this.is_bus_type = is_bus_type;
    }

    public Boolean getIs_text() {
        return is_text;
    }

    public void setIs_text(Boolean is_text) {
        this.is_text = is_text;
    }

}