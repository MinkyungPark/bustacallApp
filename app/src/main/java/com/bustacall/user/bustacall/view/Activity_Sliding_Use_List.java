package com.bustacall.user.bustacall.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bustacall.user.bustacall.adapter.UserTogetherListBusAdapter;
import com.bustacall.user.bustacall.presenter.Activity_Sliding_Use_List_Presenter;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.adapter.UserListBusAdpater;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_Sliding_Use_List extends BaseActivity implements View.OnClickListener{
    TextView tv_title,tv_bus,tv_res;
    LinearLayout ll_buttonbar,ll_bus,ll_res;
    ListView listView;
    UserListBusAdpater adpater;
    UserTogetherListBusAdapter adapter_together;
    AppController app;
    Activity_Sliding_Use_List_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_use_list);
        set_Values();
        set_Weight();
    }

    public void set_Values(){
        app = (AppController)getApplicationContext();
        presenter = new Activity_Sliding_Use_List_Presenter(this);
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_bus = (TextView)findViewById(R.id.activity_sliding_use_list_tv_bus);
        tv_res = (TextView)findViewById(R.id.activity_sliding_use_list_tv_res);
        ll_buttonbar = (LinearLayout)findViewById(R.id.activity_sliding_use_list_ll_buttonbar);
        ll_bus = (LinearLayout)findViewById(R.id.activity_sliding_use_list_ll_bus);
        ll_res = (LinearLayout)findViewById(R.id.activity_sliding_use_list_ll_res);
        ll_bus.setOnClickListener(this);
        ll_res.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.activity_slding_use_list_lv);
        adpater = new UserListBusAdpater(app.getUser(),this);
        adapter_together = new UserTogetherListBusAdapter(this,app.getUser());

        tv_title.setText("이용 내역");

        goToBus();//초기화
    }

    public void goToBus() {
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_buttonbar));
        tv_bus.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_res.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        if (app.getUser().getRental_list().size() > 0) {
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adpater);
        }else{
            listView.setVisibility(View.GONE);
        }
    }

    public void goToRes(){
        ll_buttonbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_buttonbar));
        tv_bus.setTextColor(getResources().getColor(R.color.colorPrimaryGray));
        tv_res.setTextColor(getResources().getColor(R.color.colorPrimary));
        if(app.getUser().getRental_list_together().size()>0) {
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adapter_together);
        }else{
            listView.setVisibility(View.GONE);
        }
        //여기서 adpater 변환
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.activity_sliding_use_list_ll_bus){
            goToBus();
        }else if(v.getId()==R.id.activity_sliding_use_list_ll_res){
            goToRes();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adpater.notifyDataSetChanged();
        adapter_together.notifyDataSetChanged();
    }
}
