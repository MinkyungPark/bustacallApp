package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.presenter.Activity_BudgetQuoteCal_Presenter;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-10-30.
 */

public class Activity_BudgetQuoteCal extends BaseActivity implements View.OnClickListener{

    final double BAND = 0.18;    // 지도 zoom을 위한 위도경도 조정 허용 수치

    ImageView iv_btnBack; // 뒤로가기
    RelativeLayout mapLayout;   // 지도 레이아웃
    TextView tv_distance; // 거리, 통행료
    TextView tv_userCnt; // 탐승인원, 잔여좌석
    TextView tv_result; // 예상 견적
    TextView tv_enter;

    TMapView tmapview;
    TMapData tmapdata;
    double start_one_lat, start_and_end_lat; // 출발 위도, 출발 경도, 도착 위도, 도착 경도
    double start_one_lon, start_and_end_lon; // 도착 위도, 도착 경도, 출발 위도, 출발 경도
    Bitmap startPin, endPin; //출발지 아이콘, 도착지 아이콘

    Activity_BudgetQuoteCal_Presenter presenter;
    AppController app;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgetquotecal);
        init();
        tmapInit();
    }

    private void init() {
        app = (AppController)getApplicationContext();
        iv_btnBack = (ImageView)findViewById(R.id.activity_budgetquotecal_btnback);
        mapLayout = (RelativeLayout)findViewById(R.id.activity_budgetquotecal_mapview);
        tv_distance = (TextView)findViewById(R.id.activity_budgetquotecal_distance);
        tv_userCnt = (TextView)findViewById(R.id.activity_budgetquotecal_embark_user_cnt);
        tv_result = (TextView)findViewById(R.id.activity_budgetquotecal_result);
        tv_enter = (TextView)findViewById(R.id.activity_budgetquotecal_btnEnter);
        iv_btnBack.setOnClickListener(this);
        tv_enter.setOnClickListener(this);
        tmapdata = new TMapData();
        startPin = BitmapFactory.decodeResource(getResources(), R.drawable.start_marker);
        startPin = startPin.createScaledBitmap(startPin, startPin.getWidth() / 2, startPin.getHeight() / 2, true);
        endPin = BitmapFactory.decodeResource(getResources(), R.drawable.end_marker);
        endPin = endPin.createScaledBitmap(endPin, endPin.getWidth() / 2, endPin.getHeight() / 2, true);
        presenter = new Activity_BudgetQuoteCal_Presenter(this);

        tv_userCnt.setText(app.getRental().getUser_count());
        //////////////////////////////////////////
    }

    private void tmapInit() {
        tmapview = new TMapView(this);
        tmapview.setSKPMapApiKey("713a762b-2a5c-3013-81b7-0bf4360d6355");
        tmapview.setIconVisibility(true);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setMapType(TMapView.MAPTYPE_SATELLITE);
        tmapview.setCompassMode(false);
        tmapview.setTrackingMode(false);

        setTmapView();

        mapLayout.addView(tmapview);
    }

    /**인텐트로 받아온 위치 정보를 셋팅 */
    private void setTmapView() {
        settingOfgetIntent();
        final TMapPoint tmapStartOne = new TMapPoint(start_one_lat, start_one_lon);
        final TMapPoint tmapStartAndEnd = new TMapPoint(start_and_end_lat, start_and_end_lon);

        List<TMapPoint> pointList = new ArrayList<TMapPoint>();
        pointList.add(tmapStartOne);
        pointList.add(tmapStartAndEnd);
        setZoomLevel(tmapStartOne,tmapStartAndEnd);

        tmapdata.findPathData(tmapStartOne, tmapStartAndEnd, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(final TMapPolyLine tmappolyline) {
                tmappolyline.setLineColor(Color.BLUE);
                tmapview.setTMapPathIcon(startPin, endPin);
                tmapview.addTMapPath(tmappolyline);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                // 해당 작업을 처리함
                                tv_distance.setText(String.valueOf((int)tmappolyline.getDistance()/1000));
                                int distance = (int)tmappolyline.getDistance()/1000;
                                if(distance <= 100){
                                    tv_result.setText("550000");
                                }else if(distance <=150 ){
                                    tv_result.setText("650000");
                                }else if(distance <=200){
                                    tv_result.setText("750000");
                                }else if(distance <=250){
                                    tv_result.setText("850000");
                                }else if(distance <=300){
                                    tv_result.setText("900000");
                                }else if(distance <=350){
                                    tv_result.setText("950000");
                                }else if(distance <=400){
                                    tv_result.setText("1000000");
                                }else if(distance <=450){
                                    tv_result.setText("1050000");
                                } else if (distance <= 500) {
                                    tv_result.setText("1100000");
                                } else if(distance >500){
                                    tv_result.setText("1150000");
                                }
                                app.getRental().setRental_money(tv_result.getText().toString());
                            }
                        });
                    }
                }).start();
            }
        });

    }

    private void settingOfgetIntent() {
        Intent intent = getIntent();
        start_one_lat = intent.getDoubleExtra("start_one_latitude", 0);
        start_one_lon = intent.getDoubleExtra("start_one_longitude", 0);
        start_and_end_lat = intent.getDoubleExtra("endone_starttwo_latitude", 0);
        start_and_end_lon = intent.getDoubleExtra("endone_starttwo_longitude", 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_budgetquotecal_btnback:
                finish();
                break;
            case R.id.activity_budgetquotecal_btnEnter:
                presenter.request_lent(app.getUser().getUser_nickname(),app.getRental());

                break;
        }
    }

    /**핀 위치에 맞춰 줌레벨 설정*/
    private void setZoomLevel(TMapPoint StartPpoint,TMapPoint EndPoint) {
        tmapview.zoomToTMapPoint(StartPpoint, EndPoint);
        double lon = (StartPpoint.getLongitude()+ EndPoint.getLongitude())/2;
        double lat = (StartPpoint.getLatitude()+ EndPoint.getLatitude())/2;
        tmapview.setCenterPoint(lon, lat);
    }

    //돈 측정.
    public void setMoney(){
    }
}
