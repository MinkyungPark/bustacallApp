package com.bustacall.user.bustacall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-10-26.
 */
public class Dialog_Text extends Dialog implements View.OnClickListener{
    Context context;
    EditText et_text;
    TextView tv_button;
    public String str_text;

    public Dialog_Text(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_text);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true);
        this.context = context;
        init();
    }

    public void init(){
        et_text = (EditText) findViewById(R.id.dialog_et_text);
        tv_button = (TextView)findViewById(R.id.dialog_text_tv_enter);
        tv_button.setOnClickListener(this);
    }

    public TextView getTv_button() {
        return tv_button;
    }

    public void setTv_button(TextView tv_button) {
        this.tv_button = tv_button;
    }

    public EditText getEt_text() {
        return et_text;
    }

    public void setEt_text(EditText et_text) {
        this.et_text = et_text;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dialog_base_enter){
            this.dismiss();
        }
    }
}
