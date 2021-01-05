package com.bustacall.user.bustacall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    Button bt;
    AppController app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.bustacall.user.bustacall.R.layout.activity_sliding_tourist);
        //bt=(Button)findViewById(R.id.activity_test_bt);
        //bt.setOnClickListener(this);
        app = (AppController)getApplicationContext();
    }

    //http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey=인증키&MobileOS=ETC&MobileApp=AppTesting

    //http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=VRyrW8MxOtTcNpDoBx%2B5KHCveEnGWiqPRqAQOHXr%2Bc%2FfTBeLD%2F15%2Fp29EplcH%2Brtrp34D9sc3izbEHgzzf8wfw%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&arrange=A&listYN=Y&contentTypeId=12&areaCode=1&sigunguCode=1&MobileOS=AND&MobileApp=Bustacll&_type=json
//    @Override
//    public void onClick(View v) {
//        if(v.getId() == R.id.activity_test_bt){
//            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.visitkorea.or.kr").addConverterFactory(GsonConverterFactory.create()).build();
//            Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
//            //Call<JsonObject> retrofitinfo = retrofit_user.request_tourregionlist(1,1,12);
//            Call<JsonObject> retrofitinfo = retrofit_user.request_tourregionlist(AppController.TOURAPI,10,10,1,1,"A","Y",12,1,1,"AND","BustaCall","json");
//            //http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=VRyrW8MxOtTcNpDoBx%2B5KHCveEnGWiqPRqAQOHXr%2Bc%2FfTBeLD%2F15%2Fp29EplcH%2Brtrp34D9sc3izbEHgzzf8wfw%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&arrange=A&listYN=Y&contentTypeId=12&
//            //areaCode=1&sigunguCode=1&MobileOS=AND&MobileApp=Bustacll&_type=json
//            retrofitinfo.enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                    if(response.isSuccessful()){
//
//                        Log.d("te",response.toString());
//                    }else{
//                        Log.d("te",response.toString());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JsonObject> call, Throwable t) {
//                    Log.d("te", t.toString());
//                }
//            });
//        }
//    }
}
