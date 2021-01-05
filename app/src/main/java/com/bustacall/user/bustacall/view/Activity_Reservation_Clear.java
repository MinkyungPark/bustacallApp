package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.adapter.TourAdapter;
import com.bustacall.user.bustacall.dialog.Dialog_Together;
import com.bustacall.user.bustacall.presenter.Activity_Reservation_Clear_Presenter;
import com.bustacall.user.bustacall.model.Bus;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.TourRegion;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-05.
 */
public class Activity_Reservation_Clear extends BaseActivity implements View.OnClickListener{

    TextView tv_title,tv_day,tv_time,tv_user,tv_nickname,tv_type,tv_career,tv_phonenum,tv_money,tv_startpoint,tv_endpoint;
    LinearLayout ll_together,ll_main,ll_driver;
    ImageView iv_profile;
    AppController app;
    String end_region_firest,end_region_second;
    Bus bus_temp = new Bus();
    Rental rental_temp = new Rental();
    Activity_Reservation_Clear_Presenter presenter;
    Button bt_tour, bt_motel, bt_food;
    ScrollView main_box_scrollview;
    int flag=12;
    int area_code, sigungo_code=0;

    ListView lv_list;
    ArrayList<TourRegion> tourRegion = new ArrayList<>();
    ArrayList<String> region_area = new ArrayList<>();
    ArrayList<String> region_code_one = new ArrayList<>();
    ArrayList<String> region_code_two = new ArrayList<>();
    ArrayList<String> region_code_three = new ArrayList<>();
    ArrayList<String> region_code_four = new ArrayList<>();
    ArrayList<String> region_code_five = new ArrayList<>();
    ArrayList<String> region_code_six = new ArrayList<>();
    ArrayList<String> region_code_seven = new ArrayList<>();
    ArrayList<String> region_code_eight = new ArrayList<>();
    ArrayList<String> region_code_thirtyone = new ArrayList<>();
    ArrayList<String> region_code_thirtytwo = new ArrayList<>();
    ArrayList<String> region_code_thirtythree = new ArrayList<>();
    ArrayList<String> region_code_thirtyfour = new ArrayList<>();
    ArrayList<String> region_code_thirtyfive = new ArrayList<>();
    ArrayList<String> region_code_thirtysix = new ArrayList<>();
    ArrayList<String> region_code_thirtyseven = new ArrayList<>();
    ArrayList<String> region_code_thirtyeight = new ArrayList<>();
    ArrayList<String> region_code_thirtynine = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_clear);
        set_Values();
    }

    public void set_Values(){
        ll_main = (LinearLayout)findViewById(R.id.activity_reservation_clear_list_ll_main);
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("이용 내역");

        app = (AppController)getApplicationContext();
        Log.d("test",app.toString());
        presenter = new Activity_Reservation_Clear_Presenter(this);

        Intent intent = getIntent();
        int rental_num = intent.getIntExtra("rental_num",1000);

        if(rental_num == 0) //푸쉬알람일때
        {
            presenter.request_reservinfo_clear(Integer.parseInt(app.getRental_num()));
        }else { //리스트에 띄울때
            presenter.request_reservinfo_clear(rental_num);
        }

        presenter.request_reservinfo_clear(rental_num);
        ll_main.setVisibility(View.GONE);
        setregion_code_one();
        setregion_code_two();
        setregion_code_three();
        setregion_code_four();
        setregion_code_five();
        setregion_code_six();
        setregion_code_seven();
        setregion_code_eight();
        setregion_code_thirtyone();
        setregion_code_thirtytwo();
        setregion_code_thirtythree();
        setregion_code_thirtyfour();
        setregion_code_thirtyfive();
        setregion_code_thirtysix();
        setregion_code_thirtyseven();
        setregion_code_thirtyeight();
        setregion_code_thirtynine();
    }

    public void set_Weight(final Rental rental,final Bus bus){
        rental_temp = rental;
        end_region_firest = rental_temp.getEnd_region();
        end_region_second = rental_temp.getEnd_region_two();
        bus_temp = bus;
        tv_day = (TextView)findViewById(R.id.activity_reservation_clear_tv_day);
        tv_time = (TextView)findViewById(R.id.activity_reservation_clear_tv_time);
        tv_user = (TextView) findViewById(R.id.activity_reservation_clear_tv_user);
        tv_nickname = (TextView)findViewById(R.id.activity_reservation_clear_tv_nickname);
        tv_type = (TextView)findViewById(R.id.activity_reservation_clear_tv_type);
        tv_career = (TextView)findViewById(R.id.activity_reservation_clear_tv_career);
        tv_phonenum = (TextView)findViewById(R.id.activity_reservation_clear_tv_phonenum);
        tv_money = (TextView)findViewById(R.id.activity_reservation_clear_tv_money);
        tv_startpoint = (TextView)findViewById(R.id.activity_reservation_clear_tv_startpoint);
        tv_endpoint = (TextView)findViewById(R.id.activity_reservation_clear_tv_endpoint);
        ll_together = (LinearLayout)findViewById(R.id.activity_reservation_clear_ll_together);
        ll_driver = (LinearLayout)findViewById(R.id.activity_reservation_clear_ll_driver);
        iv_profile = (ImageView)findViewById(R.id.activity_reservation_clear_iv_profile);
        bt_food = (Button)findViewById(R.id.activity_reservation_clear_bt_food);
        bt_motel = (Button)findViewById(R.id.activity_reservation_clear_bt_motel);
        bt_tour = (Button)findViewById(R.id.activity_reservation_clear_bt_tour);
        lv_list = (ListView)findViewById(R.id.activity_reservation_clear_lv_list);
        main_box_scrollview = (ScrollView)findViewById(R.id.main_box_scrollview);
        bt_food.setOnClickListener(this);
        bt_motel.setOnClickListener(this);
        bt_tour.setOnClickListener(this);
        ll_main.setVisibility(View.VISIBLE);
        ll_together.setOnClickListener(this);
        ll_driver.setOnClickListener(this);

        tv_user.setText(rental.getUser_count());
        tv_money.setText(bus.getMoney()+"원");
        tv_startpoint.setText(rental.getStart_point_one());
        tv_endpoint.setText(rental.getEnd_point_one());
        tv_phonenum.setText(bus.getPhone_num());
        tv_day.setText(rental.getDay_one());
        tv_time.setText(rental.getTime_one());
        tv_type.setText(bus.getBus_type());
        Glide.with(this).load(bus.getBus_url().get(0)).into(iv_profile);
        tv_career.setText(bus.getBus_career());
        tv_nickname.setText(bus.getNickname());

        setOnAreadSigugo(rental_temp.getEnd_region(),rental_temp.getEnd_region_two());
        ll_main.setVisibility(View.VISIBLE);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(),Activity_tourist_detail.class);
                intent.putExtra("tourregion",tourRegion.get(position));
                startActivity(intent);
            }
        });

        lv_list.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                main_box_scrollview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        setOnTour();
    }

    public void setOnAreadSigugo(String area,String sigu){
        if(area.equals("서울")){
            area_code = 1;
            for(int i=0;i<region_code_one.size();i++)
            {
                if(sigu.equals(region_code_one.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("인천")){
            area_code = 2;
            for(int i=0;i<region_code_two.size();i++)
            {
                if(sigu.equals(region_code_two.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("대전")){
            area_code = 3;
            for(int i=0;i<region_code_three.size();i++)
            {
                if(sigu.equals(region_code_three.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("대구")){
            area_code = 4;
            for(int i=0;i<region_code_four.size();i++)
            {
                if(sigu.equals(region_code_four.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("광주")){
            area_code = 5;
            for(int i=0;i<region_code_five.size();i++)
            {
                if(sigu.equals(region_code_five.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("부산")){
            area_code = 6;
            for(int i=0;i<region_code_six.size();i++)
            {
                if(sigu.equals(region_code_six.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("울산")){
            area_code = 7;
            for(int i=0;i<region_code_seven.size();i++)
            {
                if(sigu.equals(region_code_seven.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("세종시")){
            area_code = 8;
            for(int i=0;i<region_code_eight.size();i++)
            {
                if(sigu.equals(region_code_eight.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("경기")){
            area_code = 31;
            for(int i=0;i<region_code_thirtyone.size();i++)
            {
                if(sigu.equals(region_code_thirtyone.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("강원")){
            area_code = 32;
            for(int i=0;i<region_code_thirtytwo.size();i++)
            {
                if(sigu.equals(region_code_thirtytwo.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("충북")){
            area_code = 33;
            for(int i=0;i<region_code_thirtythree.size();i++)
            {
                if(sigu.equals(region_code_thirtythree.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("충남")){
            area_code = 34;
            for(int i=0;i<region_code_thirtyfour.size();i++)
            {
                if(sigu.equals(region_code_thirtyfour.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("경북")){
            area_code = 35;
            for(int i=0;i<region_code_thirtyfive.size();i++)
            {
                if(sigu.equals(region_code_thirtyfive.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("경남")){
            area_code =36;
            for(int i=0;i<region_code_thirtysix.size();i++)
            {
                if(sigu.equals(region_code_thirtysix.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("전북")){
            area_code = 37;
            for(int i=0;i<region_code_thirtyseven.size();i++)
            {
                if(sigu.equals(region_code_thirtyseven.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("전남")){
            area_code = 38;
            for(int i=0;i<region_code_thirtyeight.size();i++)
            {
                if(sigu.equals(region_code_thirtyeight.get(i))){
                    sigungo_code=i+1;
                }
            }
        }else if(area.equals("제주")){
            area_code = 39;
            for(int i=0;i<region_code_thirtynine.size();i++)
            {
                if(sigu.equals(region_code_thirtynine.get(i))){
                    sigungo_code=i+1;
                }
            }
        }
        if(sigungo_code==0){
            Log.d("string","test");
        }
    }
    public void setDialog(){ //다이알로그시 통신.
        final Dialog_Together dialog_together = new Dialog_Together(this);

        String sit_count = null;
        if(bus_temp.getBus_type().equals("45인승 대형")){
            sit_count = "45";
        }else if(bus_temp.getBus_type().equals("35인승 중형")){
            sit_count = "35";
        }else if(bus_temp.getBus_type().equals("28인승 리무진")){
            sit_count = "28";
        }else if(bus_temp.getBus_type().equals("25인승 소형")){
            sit_count = "25";
        }else if(bus_temp.getBus_type().equals("45인승"))
            sit_count = "45";
        final int count = Integer.parseInt(sit_count)-Integer.parseInt(rental_temp.getUser_count()); //합승 가능한 좌석 수.
        dialog_together.getTv_sit_count().setText("/"+String.valueOf(count)+" 석");
        dialog_together.show();
        final String sit = sit_count;
        dialog_together.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = dialog_together.getFlag();
                if(flag == 0){//합승 불가
                    rental_temp.getTogether().setFlag(flag);
                }else if(flag == 1){//합승 가능
                    if(dialog_together.getEt_sit_count().getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"합승 가능 인원을 써주세요.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(dialog_together.getEt_money_count().getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"가격을 써주세요.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(Integer.parseInt(dialog_together.getEt_sit_count().getText().toString()) > count){
                        Toast.makeText(getApplicationContext(),"합승 가능 인원보다 초과됩니다.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    rental_temp.getTogether().setFlag(flag);
                    rental_temp.getTogether().setMoney(dialog_together.getEt_money_count().getText().toString()+"000");
                    rental_temp.getTogether().setCurrent_user_count(Integer.parseInt(rental_temp.getUser_count())+Integer.parseInt(dialog_together.getEt_sit_count().getText().toString()));
                    rental_temp.getTogether().setMax_user_count(Integer.parseInt(sit)); //전체 sit석
                    rental_temp.getTogether().setType_flag(1);
                }



                rental_temp.getTogether().setRental_num(rental_temp.getRental_num());
                presenter.request_together(rental_temp.getTogether());

                dialog_together.dismiss();
            }
        });
    }

    public void setOnTour() {
        bt_tour.setBackgroundResource(R.drawable.tour_box_on);
        bt_food.setBackgroundResource(R.drawable.tour_box_off);
        bt_motel.setBackgroundResource(R.drawable.tour_box_off);

        flag = 12;

        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            bt_tour.setTextColor(getColor(R.color.AppColorWhite));
            bt_food.setTextColor(getColor(R.color.AppColorGray));
            bt_motel.setTextColor(getColor(R.color.AppColorGray));
        } else {
            bt_tour.setTextColor(getResources().getColor(R.color.AppColorWhite));
            bt_tour.setTextColor(getResources().getColor(R.color.AppColorGray));
            bt_motel.setTextColor(getResources().getColor(R.color.AppColorGray));
        }

        presenter.request_tourregionlist(area_code, sigungo_code, flag);
    }

    public void setOnMotel() {
        bt_motel.setBackgroundResource(R.drawable.tour_box_on);
        bt_tour.setBackgroundResource(R.drawable.tour_box_off);
        bt_food.setBackgroundResource(R.drawable.tour_box_off);

        flag = 32;

        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            bt_tour.setTextColor(getColor(R.color.AppColorGray));
            bt_food.setTextColor(getColor(R.color.AppColorGray));
            bt_motel.setTextColor(getColor(R.color.AppColorWhite));
        } else {
            bt_tour.setTextColor(getResources().getColor(R.color.AppColorGray));
            bt_food.setTextColor(getResources().getColor(R.color.AppColorGray));
            bt_motel.setTextColor(getResources().getColor(R.color.AppColorWhite));
        }

        presenter.request_tourregionlist(area_code, sigungo_code, flag);
    }

    public void setOnFood() {
        bt_food.setBackgroundResource(R.drawable.tour_box_on);
        flag = 39;
        bt_tour.setBackgroundResource(R.drawable.tour_box_off);
        bt_motel.setBackgroundResource(R.drawable.tour_box_off);

        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            bt_tour.setTextColor(getColor(R.color.AppColorGray));
            bt_food.setTextColor(getColor(R.color.AppColorWhite));
            bt_motel.setTextColor(getColor(R.color.AppColorGray));
        } else {
            bt_tour.setTextColor(getResources().getColor(R.color.AppColorGray));
            bt_food.setTextColor(getResources().getColor(R.color.AppColorWhite));
            bt_motel.setTextColor(getResources().getColor(R.color.AppColorGray));
        }

        presenter.request_tourregionlist(area_code, sigungo_code, flag);
    }


    public void setListView(final ArrayList<TourRegion> tourRegions) {
        if (tourRegions.size() > 0) {
            TourAdapter adapter = new TourAdapter(tourRegions, this);
            lv_list.setVisibility(View.VISIBLE);
            lv_list.setAdapter(adapter);
            tourRegion = tourRegions;

        }else{
            lv_list.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.activity_reservation_clear_ll_together){
            setDialog();
        }else if(v.getId()==R.id.activity_reservation_clear_ll_driver){
            Intent intent = new Intent(this,Activity_driver_info.class);
            intent.putExtra("Bus", bus_temp);
            startActivity(intent);
        }else if(v.getId() == R.id.activity_reservation_clear_bt_food){
            setOnFood();
        }else if(v.getId() == R.id.activity_reservation_clear_bt_motel){
            setOnMotel();
        }else if(v.getId()==R.id.activity_reservation_clear_bt_tour){
            setOnTour();
        }
    }


    public void setregion_code_one() {
        region_code_one.add("강남구");
        region_code_one.add("강동구");
        region_code_one.add("강북구");
        region_code_one.add("강서구");
        region_code_one.add("관악구");
        region_code_one.add("광진구");
        region_code_one.add("구로구");
        region_code_one.add("금천구");
        region_code_one.add("노원구");
        region_code_one.add("도봉구");
    }

    public void setregion_code_two() {
        region_code_two.add("강화군");
        region_code_two.add("계양구");
        region_code_two.add("남구");
        region_code_two.add("남동구");
        region_code_two.add("동구");
        region_code_two.add("부평구");
        region_code_two.add("서구");
        region_code_two.add("연수구");
        region_code_two.add("옹진군");
        region_code_two.add("중구");
    }

    public void setregion_code_three() {
        region_code_three.add("대덕구");
        region_code_three.add("동구");
        region_code_three.add("서구");
        region_code_three.add("유성구");
        region_code_three.add("중구");
    }

    public void setregion_code_four() {
        region_code_four.add("남구");
        region_code_four.add("달서구");
        region_code_four.add("달성구");
        region_code_four.add("동구");
        region_code_four.add("북구");
        region_code_four.add("서구");
        region_code_four.add("수성구");
        region_code_four.add("중구");
    }

    public void setregion_code_five() {
        region_code_five.add("광산구");
        region_code_five.add("남구");
        region_code_five.add("동구");
        region_code_five.add("북구");
        region_code_five.add("서구");
    }

    public void setregion_code_six() {
        region_code_six.add("강서구");
        region_code_six.add("금정구");
        region_code_six.add("기장구");
        region_code_six.add("남구");
        region_code_six.add("동구");
        region_code_six.add("동래구");
        region_code_six.add("부산진구");
        region_code_six.add("북구");
        region_code_six.add("사상구");
        region_code_six.add("사하구");
        region_code_six.add("서구");
        region_code_six.add("수영구");
        region_code_six.add("연제구");
        region_code_six.add("영도구");
        region_code_six.add("중구");
        region_code_six.add("해운대구");
    }

    public void setregion_code_seven() {
        region_code_seven.add("중구");
        region_code_seven.add("남구");
        region_code_seven.add("동구");
        region_code_seven.add("북구");
        region_code_seven.add("울주군");
    }

    public void setregion_code_eight() {
        region_code_eight.add("세종특별자치시");
    }

    public void setregion_code_thirtyone() {
        region_code_thirtyone.add("가평군");
        region_code_thirtyone.add("고양시");
        region_code_thirtyone.add("과천시");
        region_code_thirtyone.add("광명시");
        region_code_thirtyone.add("광주시");
        region_code_thirtyone.add("구리시");
        region_code_thirtyone.add("군포시");
        region_code_thirtyone.add("김포시");
        region_code_thirtyone.add("남양주시");
        region_code_thirtyone.add("동두천시");
        region_code_thirtyone.add("부천시");
        region_code_thirtyone.add("성남시");
        region_code_thirtyone.add("수원시");
        region_code_thirtyone.add("시흥시");
        region_code_thirtyone.add("안산시");
        region_code_thirtyone.add("안성시");
        region_code_thirtyone.add("안양시");
        region_code_thirtyone.add("양주시");
        region_code_thirtyone.add("양평군");
        region_code_thirtyone.add("여주시");
        region_code_thirtyone.add("연천군");
        region_code_thirtyone.add("오산시");
        region_code_thirtyone.add("용인시");
        region_code_thirtyone.add("의왕시");
        region_code_thirtyone.add("의정부시");
        region_code_thirtyone.add("이천시");
        region_code_thirtyone.add("파주시");
        region_code_thirtyone.add("평택시");
        region_code_thirtyone.add("포천시");
        region_code_thirtyone.add("하남시");
        region_code_thirtyone.add("화성시");
    }


    public void setregion_code_thirtytwo() {
        region_code_thirtytwo.add("강릉시");
        region_code_thirtytwo.add("고성군");
        region_code_thirtytwo.add("동해시");
        region_code_thirtytwo.add("삼척시");
        region_code_thirtytwo.add("속초시");
        region_code_thirtytwo.add("양구군");
        region_code_thirtytwo.add("양양군");
        region_code_thirtytwo.add("영월군");
        region_code_thirtytwo.add("원주시");
        region_code_thirtytwo.add("인제군");
        region_code_thirtytwo.add("정선군");
        region_code_thirtytwo.add("철원군");
        region_code_thirtytwo.add("춘천시");
        region_code_thirtytwo.add("태백시");
        region_code_thirtytwo.add("평창군");
        region_code_thirtytwo.add("홍천군");
        region_code_thirtytwo.add("화천군");
        region_code_thirtytwo.add("횡성군");
    }

    public void setregion_code_thirtythree() {
        region_code_thirtythree.add("괴산군");
        region_code_thirtythree.add("단양군");
        region_code_thirtythree.add("보은군");
        region_code_thirtythree.add("영동군");
        region_code_thirtythree.add("옥천군");
        region_code_thirtythree.add("음성군");
        region_code_thirtythree.add("제천시");
        region_code_thirtythree.add("진천군");
        region_code_thirtythree.add("청원군");
        region_code_thirtythree.add("청주시");
        region_code_thirtythree.add("충주시");
        region_code_thirtythree.add("증편군");
    }

    public void setregion_code_thirtyfour() {
        region_code_thirtyfour.add("공주시");
        region_code_thirtyfour.add("금산군");
        region_code_thirtyfour.add("논산시");
        region_code_thirtyfour.add("당진시");
        region_code_thirtyfour.add("보령시");
        region_code_thirtyfour.add("부여군");
        region_code_thirtyfour.add("서산시");
        region_code_thirtyfour.add("서천군");
        region_code_thirtyfour.add("아산시");
        region_code_thirtyfour.add("예산군");
        region_code_thirtyfour.add("천안시");
        region_code_thirtyfour.add("청양군");
        region_code_thirtyfour.add("태안군");
        region_code_thirtyfour.add("홍성군");
        region_code_thirtyfour.add("계룡시");
    }

    public void setregion_code_thirtyfive() {
        region_code_thirtyfive.add("경산시");
        region_code_thirtyfive.add("경주시");
        region_code_thirtyfive.add("고령군");
        region_code_thirtyfive.add("구미시");
        region_code_thirtyfive.add("군위군");
        region_code_thirtyfive.add("김천시");
        region_code_thirtyfive.add("문경시");
        region_code_thirtyfive.add("봉화군");
        region_code_thirtyfive.add("상주시");
        region_code_thirtyfive.add("성주군");
        region_code_thirtyfive.add("안동시");
        region_code_thirtyfive.add("영덕군");
        region_code_thirtyfive.add("영양군");
        region_code_thirtyfive.add("영주시");
        region_code_thirtyfive.add("영천시");
        region_code_thirtyfive.add("예천군");
        region_code_thirtyfive.add("울릉군");
        region_code_thirtyfive.add("울진군");
        region_code_thirtyfive.add("의성군");
        region_code_thirtyfive.add("청도군");
        region_code_thirtyfive.add("청송군");
        region_code_thirtyfive.add("칠곡군");
        region_code_thirtyfive.add("포항시");
    }

    public void setregion_code_thirtysix() {
        region_code_thirtysix.add("거제시");
        region_code_thirtysix.add("거창군");
        region_code_thirtysix.add("고성군");
        region_code_thirtysix.add("김해시");
        region_code_thirtysix.add("남해군");
        region_code_thirtysix.add("마산시");
        region_code_thirtysix.add("밀양시");
        region_code_thirtysix.add("사천시");
        region_code_thirtysix.add("산청군");
        region_code_thirtysix.add("양산시"); //10
        region_code_thirtysix.add("의령군"); //12
        region_code_thirtysix.add("진주시");
        region_code_thirtysix.add("진해시");
        region_code_thirtysix.add("창녕군");
        region_code_thirtysix.add("창원시");
        region_code_thirtysix.add("통영시");
        region_code_thirtysix.add("하동군");
        region_code_thirtysix.add("함안군");
        region_code_thirtysix.add("함양군");
        region_code_thirtysix.add("합천군"); //21
    }

    public void setregion_code_thirtyseven() {
        region_code_thirtyseven.add("고창군");
        region_code_thirtyseven.add("군산시");
        region_code_thirtyseven.add("김제시");
        region_code_thirtyseven.add("남원시");
        region_code_thirtyseven.add("무주군");
        region_code_thirtyseven.add("부안군");
        region_code_thirtyseven.add("순창군");
        region_code_thirtyseven.add("완주군");
        region_code_thirtyseven.add("익산시");
        region_code_thirtyseven.add("임실군");
        region_code_thirtyseven.add("장수군");
        region_code_thirtyseven.add("전주시");
        region_code_thirtyseven.add("정읍시");
        region_code_thirtyseven.add("진안군");
    }

    public void setregion_code_thirtyeight() {
        region_code_thirtyeight.add("강진군");
        region_code_thirtyeight.add("고흥군");
        region_code_thirtyeight.add("곡성군");
        region_code_thirtyeight.add("광양시");
        region_code_thirtyeight.add("구례군");
        region_code_thirtyeight.add("나주시");
        region_code_thirtyeight.add("담양군");
        region_code_thirtyeight.add("목포시");
        region_code_thirtyeight.add("무안군");
        region_code_thirtyeight.add("보성군");
        region_code_thirtyeight.add("순천시");
        region_code_thirtyeight.add("신안군");
        region_code_thirtyeight.add("여수시");
        region_code_thirtyeight.add("영광군");
        region_code_thirtyeight.add("영암군");
        region_code_thirtyeight.add("완도군");
        region_code_thirtyeight.add("장성군");
        region_code_thirtyeight.add("장흥군");
        region_code_thirtyeight.add("진도군");
        region_code_thirtyeight.add("함평군");
        region_code_thirtyeight.add("해남군");
        region_code_thirtyeight.add("화순군");
    }

    public void setregion_code_thirtynine() {
        region_code_thirtynine.add("남제주군");
        region_code_thirtynine.add("북제주군");
        region_code_thirtynine.add("서귀포시");
        region_code_thirtynine.add("제주시");
    }
}
