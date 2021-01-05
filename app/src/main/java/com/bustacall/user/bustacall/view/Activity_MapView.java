package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

/**
 * Created by user on 2016-10-29.
 */

public class Activity_MapView extends BaseActivity implements TMapView.OnClickListener {

    RelativeLayout mapLayout; // 지도를 보여줄 layout
    TextView tv_name, tv_addr; // 표시할 장소명, 주소
    TMapView tmapview;
    double lat, lon; // 인텐트로 받아온 위도, 경도
    String placeName; // 인텐트로 받아온 장소명
    String placeAddr; // 인텐트로 받아온 주소
    TMapPoint tmapPoint; // lat, lon 넣는다.
    TMapMarkerItem tmapMakerItem; // 위치 마커


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        init();
        tmapInit();
    }

    private void init() {
        mapLayout = (RelativeLayout)findViewById(R.id.activity_mapview_layout);
        tv_name = (TextView) findViewById(R.id.activity_mapview_name);
        tv_addr = (TextView) findViewById(R.id.activity_mapview_addr);
    }

    private void tmapInit() {
        tmapview = new TMapView(this);
        tmapview.setSKPMapApiKey("713a762b-2a5c-3013-81b7-0bf4360d6355");
        tmapview.setIconVisibility(true);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setZoomLevel(16);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(false);
        tmapview.setTrackingMode(false);
        mapLayout.addView(tmapview);
        setTmapMarkerItem();

        tmapview.setLocationPoint(lon, lat);
        tmapview.setCenterPoint(lon, lat, true);
        tmapview.addMarkerItem("위치", tmapMakerItem);
    }

    /**인텐트로 받아온 위치 정보를 셋팅 */
    private void setTmapMarkerItem() {
        settingOfgetIntent();
        tmapPoint = new TMapPoint(lat, lon);

        tmapMakerItem = new TMapMarkerItem();
        tmapMakerItem.setTMapPoint(tmapPoint);
        tmapMakerItem.setName(placeName);

        Bitmap markPin = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        markPin = markPin.createScaledBitmap(markPin, markPin.getWidth() / 2, markPin.getHeight() / 2, true);
        tmapMakerItem.setIcon(markPin);
    }

    private void settingOfgetIntent() {
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("latitude", 0);
        lon = intent.getDoubleExtra("longitude", 0);
        placeName = intent.getStringExtra("name");
        placeAddr = intent.getStringExtra("addr");
        tv_name.setText(placeName);
        tv_addr.setText(placeAddr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_mapview_submitbutton:
                // 확인 클릭
                finish();
        }
    }
}
