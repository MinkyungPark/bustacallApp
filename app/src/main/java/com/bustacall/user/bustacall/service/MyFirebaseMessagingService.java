package com.bustacall.user.bustacall.service;

//import android.app.ActivityManager;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
//
//import com.bustacall.user.bustacall.AppController;
//import MainActivity;
//import com.bustacall.user.bustacall.R;
//import Dialog_Together;
//import Activity_Reservation_Clear;
//import Activity_Reservation_List;
//import Activity_Sliding_Use_List;
//import Activity_Splash;
//import com.google.firebase.messaging.RemoteMessage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.bustacall.user.bustacall.view.Activity_Reservation_List;
import com.bustacall.user.bustacall.view.Activity_Splash;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.view.Activity_Reservation_Clear;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by user on 2016-10-31.
 */
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    AppController app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = (AppController) getApplicationContext();
    }

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //추가한것
        String title;
        String message;
        String data;

        data = remoteMessage.getData().get("rental_num");
        title = remoteMessage.getNotification().getTitle();
        message = remoteMessage.getNotification().getBody();

        sendNotification(title, message, data);
    }

    public void setIntent(Intent intent, String title, String messageBody) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle("BustaCall")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification(String title, String messageBody,String data) {
        Intent intent = null;

        if (app != null) {//실행중
            if (messageBody.equals("입찰 완료!")) {
                app.setRental_num(data);
                intent = new Intent(this, Activity_Reservation_List.class);
                intent.putExtra("rental_num",0);// 푸쉬알람일때
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                setIntent(intent, title, messageBody);
            } else if (messageBody.equals("예약 완료!")) {
                ///일단 처음 화면으로
                app.setRental_num(data);
                intent = new Intent(this, Activity_Reservation_Clear.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("rental_num",0);//이건 푸쉬
                setIntent(intent, title, messageBody);
            } else if( messageBody.equals("합승 매물 입금 완료!"))
            {
                app.setRental_num(data);
                intent = new Intent(this, Activity_Splash.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("rental_num",0);//이건 푸쉬
                setIntent(intent, title, messageBody);
            }
        } else {//앱 꺼졌을 때
            intent = new Intent(this, Activity_Splash.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            setIntent(intent, title, messageBody);
        }
    }
}
