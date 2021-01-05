package com.bustacall.user.bustacall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-10-26.
 */
public class Dialog_base extends Dialog implements View.OnClickListener{
    Context context;
    TextView tv_title;
    TextView tv_button;
    public String str_text;

    public Dialog_base(Context context,String str_text) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_base);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true);
        this.context = context;
        this.str_text = str_text;
        init();
    }

    public void init(){
        tv_title = (TextView)findViewById(R.id.dialog_base_text);
        tv_button = (TextView)findViewById(R.id.dialog_base_enter);
        tv_title.setText(str_text);
        tv_button.setOnClickListener(this);
    }

    public TextView getTv_button() {
        return tv_button;
    }

    public void setTv_button(TextView tv_button) {
        this.tv_button = tv_button;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dialog_base_enter){
            this.dismiss();
        }
    }
}
