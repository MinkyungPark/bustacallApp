package com.bustacall.user.bustacall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-25.
 */
public class Dialog_listview extends Dialog {
    private String title;
    private ArrayList<String> str_text;
    private Context mContext = null;
    ListView listView;
    TextView tv_title;

    public Dialog_listview(Context context, String title, ArrayList<String> str_text) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_listview);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true); //back키 눌렀을때 꺼지게
        this.title = title;
        this.str_text = str_text;
        mContext = context;
        init();
    }

    public void init(){
        tv_title = (TextView) findViewById(R.id.dialog_listview_tv_title);
        listView = (ListView)findViewById(R.id.dialog_listview_lt);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.listview_item, str_text) ;
        listView.setAdapter(adapter);
        tv_title.setText(title);
    }

    public ListView getListView() {
        return listView;
    }

}
