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
public class Dialog_base_two_button extends Dialog implements View.OnClickListener{
    Context context;
    TextView tv_title;

    public TextView getTv_enter() {
        return tv_enter;
    }

    public TextView getTv_cancel() {
        return tv_cancel;
    }

    TextView tv_enter;
    TextView tv_cancel;
    public String str_text;

    public Dialog_base_two_button(Context context, String str_text) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_base_twobutton);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true);
        this.context = context;
        this.str_text = str_text;
        init();
    }

    public void init(){
        tv_title = (TextView)findViewById(R.id.dialog_base_two_button_text);
        tv_cancel = (TextView)findViewById(R.id.dialog_base_two_button_cancel);
        tv_enter = (TextView)findViewById(R.id.dialog_base_two_button_enter);
        tv_title.setText(str_text);
        tv_cancel.setOnClickListener(this);
        tv_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dialog_base_two_button_cancel){
            this.dismiss();
        }
        //enter이면 따로 구현
    }
}
