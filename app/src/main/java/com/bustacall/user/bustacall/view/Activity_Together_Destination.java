package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bustacall.user.bustacall.adapter.TogetherListAdapter;
import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.Rental_List;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.presenter.Activity_Together_Destination_Presenter;

/**
 * Created by user on 2016-11-10.
 */
public class Activity_Together_Destination extends BaseActivity implements View.OnClickListener{
    TextView tv_user,tv_enter,tv_title;
    ListView lv_list;
    TogetherListAdapter adapter;
    Activity_Together_Destination_Presenter presenter;
    AppController app;
    int user_count;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_together_destination);
        set_Values();
    }

    public void set_Values(){
        Intent intent = getIntent();
        user_count = intent.getIntExtra("user",1);
        presenter = new Activity_Together_Destination_Presenter(this);
        app = (AppController)getApplicationContext();
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        tv_title.setText("목적지 검색");
        tv_user = (TextView)findViewById(R.id.activity_together_destination_tv_user_count);
        tv_user.setText(user_count+" 명");
        presenter.request_get_rental(app.getRental().getStart_region(),app.getRental().getEnd_region());
    }

    public void set_Weight(final Rental_List rental_list){
        tv_enter = (TextView)findViewById(R.id.activity_together_destination_tv_enter);
        tv_enter.setOnClickListener(this);
        lv_list = (ListView)findViewById(R.id.activity_together_destination_lv_list);
        adapter = new TogetherListAdapter(this,rental_list);
        if(rental_list.getRental_list().size() > 0) {
            lv_list.setAdapter(adapter);
            lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() { //눌렀을때 다음창
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (rental_list.getRental_list().get(position).getTogether().getFlag() == 1) {//빈자리 타기
                        if ((rental_list.getRental_list().get(position).getTogether().getMax_user_count() - rental_list.getRental_list().get(position).getTogether().getCurrent_user_count()) < user_count) {
                            Dialog_base dialog_base = new Dialog_base(Activity_Together_Destination.this, "인원 초과입니다.");
                            dialog_base.show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), Activity_Empty_Sit_Detail.class);
                            intent.putExtra("rental", rental_list.getRental_list().get(position));
                            intent.putExtra("user", tv_user.getText().toString());
                            startActivity(intent);
                        }
                    } else {// 같이타기
                        if ((rental_list.getRental_list().get(position).getTogether().getMax_user_count() - rental_list.getRental_list().get(position).getTogether().getCurrent_user_count()) < user_count) {
                            Dialog_base dialog_base = new Dialog_base(Activity_Together_Destination.this, "인원 초과입니다.");
                            dialog_base.show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), Activity_Together_Sit_Detail.class);
                            intent.putExtra("rental", rental_list.getRental_list().get(position));
                            intent.putExtra("user", tv_user.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_together_destination_tv_enter){
            Intent intent = new Intent(this,Activity_Make_Together.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.request_get_rental(app.getRental().getStart_region(),app.getRental().getEnd_region());
    }
}
