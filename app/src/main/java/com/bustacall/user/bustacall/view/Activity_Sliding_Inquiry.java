package com.bustacall.user.bustacall.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-10-09.
 */
public class Activity_Sliding_Inquiry extends BaseActivity implements View.OnClickListener{
    TextView tv_title,tv_enter;
    EditText et_text;
    AppController app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_inquiry);
        set_Values();
        set_Weight();
    }


    public void set_Values(){
        app = (AppController)getApplicationContext();
    }

    public void set_Weight(){
        tv_title = (TextView)findViewById(R.id.white_titlebar_sliding_tv_title);
        et_text = (EditText)findViewById(R.id.activity_sliding_inqulry_et_text);
        tv_title.setText("문의 하기");
        tv_enter = (TextView)findViewById(R.id.activity_sliding_inqulry_tv_enter);
        tv_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.activity_sliding_inqulry_tv_enter){
            Intent it = new Intent(Intent.ACTION_SEND);
            String[] mailaddr = {"goodys1011@naver.com"};

            it.setType("plaine/text");
            it.putExtra(Intent.EXTRA_EMAIL, mailaddr); // 받는사람
            it.putExtra(Intent.EXTRA_SUBJECT, "문의합니다"); // 제목
            it.putExtra(Intent.EXTRA_TEXT, "닉네임 : "+app.getUser().getUser_nickname()+"\n\n" + et_text.getText()); // 첨부내용

            startActivity(it);

            et_text.setText("");
            et_text.setHint("문의글을 써주세요.");
        }
    }
}
