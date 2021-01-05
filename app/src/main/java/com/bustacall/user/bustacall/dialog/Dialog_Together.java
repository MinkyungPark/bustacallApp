package com.bustacall.user.bustacall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-10-26.
 */
public class Dialog_Together extends Dialog implements View.OnClickListener{
    Context context;
    TextView tv_allow;
    TextView tv_disallow;
    TextView tv_sit_count;
    TextView tv_enter;
    TextView tv_text1,tv_text2,tv_text3,tv_text4;
    EditText et_sit_count,et_money_count;
    int flag=0; //0이면 합승 x, 1이면 합승
    String count=null;
    String money=null;

    public Dialog_Together(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_together);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true);
        this.context = context;
        init();
    }

    public void init(){
        tv_text1 = (TextView)findViewById(R.id.dialog_together_tv_text1);
        tv_text2 = (TextView)findViewById(R.id.dialog_together_tv_text2);
        tv_text3 = (TextView)findViewById(R.id.dialog_together_tv_text3);
        tv_text4 = (TextView)findViewById(R.id.dialog_together_tv_text4);
        tv_allow = (TextView)findViewById(R.id.dialog_together_tv_allow);
        tv_disallow = (TextView)findViewById(R.id.dialog_together_tv_disallow);
        tv_sit_count = (TextView)findViewById(R.id.dialog_together_tv_sit_count);
        tv_enter = (TextView)findViewById(R.id.dialog_together_tv_enter);
        et_money_count = (EditText) findViewById(R.id.dialog_together_et_money);
        et_sit_count = (EditText)findViewById(R.id.dialog_together_et_sit_count);
        tv_enter.setOnClickListener(this);
        tv_allow.setOnClickListener(this);
        tv_disallow.setOnClickListener(this);
        goToallow();
    }

    public void goToallow(){//가능
        //<color name="AppColorLightGray">#dcdddd</color>
        //<color name="AppColorLightGrayTwo">#f3f4f6</color>
        tv_text1.setTextColor(Color.parseColor("#2978B0"));//파란색
        tv_text2.setTextColor(Color.parseColor("#000000"));//검은색
        tv_text3.setTextColor(Color.parseColor("#000000"));
        tv_text4.setTextColor(Color.parseColor("#dcdddd"));

        tv_allow.setTextColor(Color.parseColor("#ffffff"));
        tv_allow.setBackgroundColor(Color.parseColor("#2978B0"));
        tv_disallow.setTextColor(Color.parseColor("#dcdddd"));
        tv_disallow.setBackgroundColor(Color.parseColor("#f3f4f6"));

        tv_sit_count.setTextColor(Color.parseColor("#dcdddd"));
        et_money_count.setFocusable(true);
        et_money_count.setClickable(true);
        et_money_count.setBackgroundColor(Color.parseColor("#dcdddd"));
        et_money_count.setTextColor(Color.parseColor("#000000"));
        et_sit_count.setFocusable(true);
        et_sit_count.setClickable(true);
        et_sit_count.setBackgroundColor(Color.parseColor("#dcdddd"));
        et_sit_count.setTextColor(Color.parseColor("#000000"));
        et_money_count.setText("");
        et_sit_count.setText("");
        flag = 1;
    }
    public void goTodisallow() {//불가

        tv_allow.setTextColor(Color.parseColor("#dcdddd"));
        tv_allow.setBackgroundColor(Color.parseColor("#f3f4f6"));
        tv_disallow.setTextColor(Color.parseColor("#ffffff"));
        tv_disallow.setBackgroundColor(Color.parseColor("#2978B0"));

        String col = "#f3f4f6";
        tv_text1.setTextColor(Color.parseColor(col));
        tv_text2.setTextColor(Color.parseColor(col));
        tv_text3.setTextColor(Color.parseColor(col));
        tv_text4.setTextColor(Color.parseColor(col));
        tv_sit_count.setTextColor(Color.parseColor(col));
        et_money_count.setFocusable(false);
        et_money_count.setClickable(false);
        et_money_count.setBackgroundColor(Color.parseColor(col));
        et_money_count.setTextColor(Color.parseColor(col));
        et_sit_count.setFocusable(false);
        et_sit_count.setClickable(false);
        et_sit_count.setBackgroundColor(Color.parseColor(col));
        et_sit_count.setTextColor(Color.parseColor(col));
        et_money_count.setText("");
        et_sit_count.setText("");
        flag = 0;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dialog_together_tv_enter){

        }else if(v.getId()==R.id.dialog_together_tv_allow){
            goToallow();

        }else if(v.getId()==R.id.dialog_together_tv_disallow){
            goTodisallow();
        }
    }

    public TextView getTv_enter() {
        return tv_enter;
    }

    public void setTv_enter(TextView tv_enter) {
        this.tv_enter = tv_enter;
    }

    public TextView getTv_sit_count() {
        return tv_sit_count;
    }

    public void setTv_sit_count(TextView tv_sit_count) {
        this.tv_sit_count = tv_sit_count;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public EditText getEt_sit_count() {
        return et_sit_count;
    }

    public void setEt_sit_count(EditText et_sit_count) {
        this.et_sit_count = et_sit_count;
    }

    public EditText getEt_money_count() {
        return et_money_count;
    }

    public void setEt_money_count(EditText et_money_count) {
        this.et_money_count = et_money_count;
    }
}
