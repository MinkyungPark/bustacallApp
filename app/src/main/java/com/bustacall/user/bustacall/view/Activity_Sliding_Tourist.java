package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bustacall.user.bustacall.adapter.TourAdapter;
import com.bustacall.user.bustacall.presenter.Activity_Sliding_Tourist_Presenter;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.TourRegion;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_Sliding_Tourist extends BaseActivity implements View.OnClickListener {
    Activity_Sliding_Tourist_Presenter presenter;
    ArrayList<TourRegion> tourRegion = new ArrayList<>();
    int area_code, sigungo_code;
    TextView tv_title;
    Button bt_tour, bt_motel, bt_food;
    Spinner sp_area, sp_sigungo;
    ListView lv_list;
    ArrayAdapter<String> area_adapter;
    ArrayAdapter<String> code_adapter;
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

    int flag = 12; //처음에 관광으로

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tourist);
        set_Values();
        set_Weight();
    }

    public void set_Values() {
        presenter = new Activity_Sliding_Tourist_Presenter(this);
    }

    public void set_Weight() {
        area_code = 1;
        sigungo_code = 1;
        tv_title = (TextView) findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("관광지 정보");
        bt_food = (Button) findViewById(R.id.activity_sliding_tourist_bt_food);
        bt_motel = (Button) findViewById(R.id.activity_sliding_tourist_bt_motel);
        bt_tour = (Button) findViewById(R.id.activity_sliding_tourist_bt_tour);
        bt_food.setOnClickListener(this);
        bt_motel.setOnClickListener(this);
        bt_tour.setOnClickListener(this);
        lv_list = (ListView) findViewById(R.id.activity_sliding_tourist_lv_list);
        sp_area = (Spinner) findViewById(R.id.activity_sliding_tourist_sp_areacode);
        sp_sigungo = (Spinner) findViewById(R.id.activity_sliding_tourist_sp_sigungo);
        region_area.add("서울"); //1
        region_area.add("인천"); //2
        region_area.add("대전"); //3
        region_area.add("대구"); //4
        region_area.add("광주"); //5
        region_area.add("부산"); //6
        region_area.add("울산"); //7
        region_area.add("세종특별자치시"); //8
        region_area.add("경기도"); //31
        region_area.add("강원도"); //32
        region_area.add("충청북도"); //33
        region_area.add("충청남도"); //34
        region_area.add("경상북도"); //35
        region_area.add("경상남도"); //36
        region_area.add("전라북도"); //37
        region_area.add("전라남도"); //38
        region_area.add("제주도"); //39
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

        area_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, region_area);
        code_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, region_code_one);
        sp_area.setAdapter(area_adapter);
        sp_sigungo.setAdapter(code_adapter);

        sp_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_one);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 1:

                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_two);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 2:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_three);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 3:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_four);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 4:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_five);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 5:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_six);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 6:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_seven);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 7:
                        area_code = position + 1;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_eight);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 8:
                        area_code = 31;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtyone);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 9:

                        area_code = 32;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtytwo);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 10:
                        area_code = 33;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtythree);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 11:
                        area_code = 34;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtyfour);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 12:
                        area_code = 35;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtyfive);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 13:
                        area_code = 36;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtysix);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 14:
                        area_code = 37;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtyseven);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 15:
                        area_code = 38;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtyeight);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                    case 16:
                        area_code = 39;
                        code_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, region_code_thirtynine);
                        sp_sigungo.setAdapter(code_adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_sigungo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sigungo_code = position + 1;
                setOnTour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(),Activity_tourist_detail.class);
                intent.putExtra("tourregion",tourRegion.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_sliding_tourist_bt_tour) {
            setOnTour();
        } else if (v.getId() == R.id.activity_sliding_tourist_bt_motel) {
            setOnMotel();
        } else if (v.getId() == R.id.activity_sliding_tourist_bt_food) {
            setOnFood();
        }
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
