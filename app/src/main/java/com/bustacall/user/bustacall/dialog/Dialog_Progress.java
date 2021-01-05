package com.bustacall.user.bustacall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-10-28.
 */
public class Dialog_Progress extends Dialog {

    public Dialog_Progress(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progresscircle);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }
}
