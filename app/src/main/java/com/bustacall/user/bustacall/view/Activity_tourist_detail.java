package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bustacall.user.bustacall.dialog.Dialog_base;
import com.bustacall.user.bustacall.model.TourRegion;
import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_tourist_detail extends BaseActivity implements View.OnClickListener{

    ImageView iv_first,iv_first2,iv_profile,iv_phone_button;
    TextView tv_name,tv_phone_num,tv_addr,tv_title;
    TourRegion tourRegion = new TourRegion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourlist_detail);
        set_Values();
        set_Weight();
    }

    public void set_Values(){
        Intent intent = getIntent();
        tourRegion = (TourRegion)intent.getSerializableExtra("tourregion");
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.blutitlebar_login_back_tv_title);
        tv_title.setText("업소 정보");
        iv_first = (ImageView)findViewById(R.id.activity_tourlist_detail_iv_firstimage);
        iv_first2 = (ImageView)findViewById(R.id.activity_tourlist_detail_iv_firstimage2);
        iv_phone_button = (ImageView)findViewById(R.id.activity_tourlist_detail_iv_phone_button);
        iv_profile = (ImageView)findViewById(R.id.activity_tourlist_detail_iv_profile);
        tv_name = (TextView)findViewById(R.id.activity_tourlist_detail_tv_name);
        tv_phone_num = (TextView)findViewById(R.id.activity_tourlist_detail_tv_phone);
        tv_addr = (TextView)findViewById(R.id.activity_tourlist_detail_tv_addr);
        iv_phone_button.setOnClickListener(this);
        tv_name.setText(tourRegion.getTitle());
        tv_addr.setText(tourRegion.getAddr1());
        tv_phone_num.setText(tourRegion.getTel());
        Glide.with(this).load(tourRegion.getFirstimage()).into(iv_profile);
        Glide.with(this).load(tourRegion.getFirstimage()).into(iv_first);
        Glide.with(this).load(tourRegion.getFirstimage()).into(iv_first2);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_tourlist_detail_iv_phone_button){
            if(!tv_phone_num.getText().toString().equals("No phone num")){
                Uri uri= Uri.parse("tel:"+tv_phone_num.getText().toString()); //전화와 관련된 Data는 'Tel:'으로 시작. 이후는 전화번호
                Intent i= new Intent(Intent.ACTION_DIAL,uri); //시스템 액티비티인 Dial Activity의 action값
                startActivity(i);//액티비티 실행
            }else{
                Dialog_base dialog_base = new Dialog_base(this,"전화번호가 없습니다.");
                dialog_base.show();
            }
        }
    }
}
