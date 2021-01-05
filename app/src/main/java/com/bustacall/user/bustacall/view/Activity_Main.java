package com.bustacall.user.bustacall.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.dialog.Dialog_base_two_button;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 엑티비티 메인 페이지
 * Created by user on 2016-10-09.
 */
public class Activity_Main extends BaseActivity implements View.OnClickListener {
    @Override
    public void onBackPressed() {//종료 버튼
        final Dialog_base_two_button dialog_base_two_button = new Dialog_base_two_button(this, "앱을 종료하시겠습니까?");
        dialog_base_two_button.show();
        dialog_base_two_button.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        dialog_base_two_button.getTv_cancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_base_two_button.dismiss();
            }
        });
    }

    LinearLayout ll_goandback, ll_go, ll_buttonbar;
    TextView tv_reservationbus, tv_reservationto, tv_goandback, tv_go;
    ImageView iv_goandback_image, iv_go_image, iv_goandback_checkpoint, iv_go_checkpoint, iv_menu;
    AppController app;

    SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_Weight();
        setMenu();
        set_Values();
    }

    public void setMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sliding_menu_frame, SlidingMenuFragment.newInstance())
                .commit();

    }

    public void set_Values() {
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_buttonbar));
        tv_goandback.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_go.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        iv_goandback_checkpoint.setImageResource(R.drawable.checkpoint);
        iv_go_checkpoint.setVisibility(View.INVISIBLE);

        /////////////////////////////처음//////////////////////////////////
        Fragment fragment = new Fragment_Main_GoandBack();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_framelayout, fragment);
        fragmentTransaction.commit();
        ///////////////////////////////////////////////////////////////////
    }

    public void set_Weight() {
        ll_buttonbar = (LinearLayout) findViewById(R.id.whitetitlebar_main_ll_buttonbar);
        app = (AppController) getApplicationContext();
        Log.d("test", app.toString());
        ll_goandback = (LinearLayout) findViewById(R.id.whitetitlebar_main_ll_goandback);
        ll_go = (LinearLayout) findViewById(R.id.whitetitlebar_main_ll_go);
        tv_go = (TextView) findViewById(R.id.whitetitlebar_main_tv_go_text);
        tv_goandback = (TextView) findViewById(R.id.whitetitlebar_main_tv_goandback_text);
        iv_goandback_image = (ImageView) findViewById(R.id.whitetitlebar_main_iv_goandback_image);
        iv_go_image = (ImageView) findViewById(R.id.whitetitlebar_main_iv_go_image);
        iv_go_checkpoint = (ImageView) findViewById(R.id.whitetitlebar_main_iv_go_checkpoint);
        iv_goandback_checkpoint = (ImageView) findViewById(R.id.whitetitlebar_main_iv_goandback_checkpoint);
        tv_reservationbus = (TextView) findViewById(R.id.whitetitlebar_main_tv_reservation_bus);
        tv_reservationto = (TextView) findViewById(R.id.whitetitlebar_main_tv_reservation_to);
        iv_menu = (ImageView) findViewById(R.id.activity_main_iv_menu);
        tv_reservationbus.setOnClickListener(this);
        tv_reservationto.setOnClickListener(this);
        ll_go.setOnClickListener(this);
        ll_goandback.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        /////////////////////////////////////////////////////////////////////////////////////////
    }

    public void goTogo() { //편도
        Fragment fragment = new Fragment_Main_Go();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_framelayout, fragment);

        fragmentTransaction.commit();
        tv_goandback.setText("왕복");
        tv_go.setText("편도");
        tv_go.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_goandback.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        iv_go_checkpoint.setImageResource(R.drawable.checkpoint);
        iv_goandback_checkpoint.setVisibility(View.INVISIBLE);
        iv_go_image.setImageResource(R.drawable.go_blue);
        iv_go_checkpoint.setVisibility(View.VISIBLE);
        iv_goandback_image.setImageResource(R.drawable.goandback_white);
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_buttonbar));

    }

    public void goTogoandback() { //왕복
        Fragment fragment = new Fragment_Main_GoandBack();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_framelayout, fragment);
        fragmentTransaction.commit();

        tv_goandback.setText("왕복");
        tv_go.setText("편도");
        tv_go.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        tv_goandback.setTextColor(getResources().getColor(R.color.colorPrimary));
        iv_goandback_checkpoint.setImageResource(R.drawable.checkpoint);
        iv_go_checkpoint.setVisibility(View.INVISIBLE);
        iv_go_image.setImageResource(R.drawable.go_white);
        iv_goandback_image.setImageResource(R.drawable.goandback_blue);
        iv_goandback_checkpoint.setVisibility(View.VISIBLE);
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_buttonbar));
    }

    public void goTodestination() { //목적지 검색
        Fragment fragment = new Fragment_Main_Destination();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_framelayout, fragment);

        fragmentTransaction.commit();

        tv_goandback.setText("목적지 검색");
        tv_go.setText("목적 검색");
        tv_go.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        tv_goandback.setTextColor(getResources().getColor(R.color.colorPrimary));
        iv_goandback_checkpoint.setVisibility(View.GONE);
        iv_go_checkpoint.setVisibility(View.GONE);
        iv_go_image.setImageResource(R.drawable.goal_white);
        iv_goandback_image.setImageResource(R.drawable.destination_blue);
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_buttonbar));
    }

    public void goTogoal() { //목적 검색
        Fragment fragment = new Fragment_Main_Goal();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_framelayout, fragment);

        fragmentTransaction.commit();

        tv_goandback.setText("목적지 검색");
        tv_go.setText("목적 검색");
        tv_go.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_goandback.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        iv_go_checkpoint.setVisibility(View.GONE);
        iv_goandback_checkpoint.setVisibility(View.GONE);
        iv_go_image.setImageResource(R.drawable.goal_blue);
        iv_goandback_image.setImageResource(R.drawable.destination_white);
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_buttonbar));
    }

    public void goToBus() { //버스
        tv_goandback.setText("왕복");
        tv_go.setText("편도");
        tv_reservationbus.setTextColor(getResources().getColor(R.color.AppColorWhite));
        tv_reservationto.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_reservationbus.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tv_reservationto.setBackgroundColor(getResources().getColor(R.color.AppColorLightGray));
        goTogoandback();
    }

    public void goToto() { //합승
        tv_goandback.setText("목적지 검색");
        tv_go.setText("목적 검색");
        tv_reservationbus.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_reservationto.setTextColor(getResources().getColor(R.color.AppColorWhite));
        tv_reservationbus.setBackgroundColor(getResources().getColor(R.color.AppColorLightGray));
        tv_reservationto.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //TODO 바로 프래그먼트 호출
        goTodestination();
    }

    public void goTomenu() {

        menu.showMenu();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.whitetitlebar_main_ll_go && tv_go.getText().toString().equals("편도")) {//편도
            goTogo(); //이게 편도로 보내는 프레그먼트

        } else if (v.getId() == R.id.whitetitlebar_main_ll_goandback && tv_goandback.getText().toString().equals("왕복")) {//왕복
            goTogoandback();
        } else if (v.getId() == R.id.whitetitlebar_main_ll_goandback && tv_goandback.getText().toString().equals("목적지 검색"))//목적지 검색
        {
            goTodestination();
        } else if (v.getId() == R.id.whitetitlebar_main_ll_go && tv_go.getText().toString().equals("목적 검색")) {//목적 검색
            goTogoal();
        }
        if (v.getId() == R.id.whitetitlebar_main_tv_reservation_bus) { //버스예약
            goToBus();
        } else if (v.getId() == R.id.whitetitlebar_main_tv_reservation_to) { //합승 예약
            goToto();
        } else if (v.getId() == R.id.activity_main_iv_menu) { //메뉴
            goTomenu();
        }
    }

}
