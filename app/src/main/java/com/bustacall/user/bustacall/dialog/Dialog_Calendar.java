package com.bustacall.user.bustacall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2016-10-25.
 */
public class Dialog_Calendar extends Dialog {
    private Context mContext = null;
    CalendarView calendarView;

    public Dialog_Calendar(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_calendar);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true); //back키 눌렀을때 꺼지게
        mContext = context;
        init();
    }

    public void init(){
        calendarView = (CalendarView)findViewById(R.id.dialog_calender);
        Calendar c = Calendar.getInstance();

        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            calendarView.setMinDate(System.currentTimeMillis()-1000);
        } else {
            calendarView.setShowWeekNumber(false);
            calendarView.setSelectedWeekBackgroundColor(Color.WHITE);
        }

        calendarView.setDate(System.currentTimeMillis(),false,true);

    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }
}
