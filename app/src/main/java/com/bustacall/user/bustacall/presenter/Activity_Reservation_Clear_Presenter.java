package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.dialog.Dialog_Progress;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.Together;
import com.bustacall.user.bustacall.model.TourRegion;
import com.bustacall.user.bustacall.view.Activity_Reservation_Clear;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-05.
 */
public class Activity_Reservation_Clear_Presenter {
    Activity_Reservation_Clear view;
    AppController app;

    public Activity_Reservation_Clear_Presenter(Activity_Reservation_Clear view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    public void request_reservinfo_clear(int rental_num){
        final Dialog_Progress dialog_progress = new Dialog_Progress(view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental> retrofitinfo = retrofit_user.request_reservinfo_clear(rental_num);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Rental>() { //예약 완료 인데, 여기도 아마 구분자가 필요할거 같은데?
            @Override
            public void onResponse(Call<Rental> call, Response<Rental> response) {
                if(response.isSuccessful()){
                    for (int i = 0; i < app.getUser().getRental_list().size(); i++) {
                        if (response.body().getRental_num() == app.getUser().getRental_list().get(i).getRental_num()) {
                            app.getUser().getRental_list().get(i).setBus_list(response.body().getBus_list()); //여기서 받아와서 num과 동일한 놈한테 집어넣는다.
                            for(int j=0;j<app.getUser().getRental_list().get(i).getBus_list().size();j++)
                                if(app.getUser().getRental_list().get(i).getBus_list().get(j).getAccount_flag()==1)
                                {
                                    Log.d("test",app.toString());
                                    app.getUser().getRental_list().get(i).setType_two(4);
                                    view.set_Weight(app.getUser().getRental_list().get(i),app.getUser().getRental_list().get(i).getBus_list().get(j));
                                }
                        }
                    }
                }else{
                    Log.d("test",response.toString());
                }
                dialog_progress.dismiss();
                //view.set_Weight(); 여기서 확인
            }

            @Override
            public void onFailure(Call<Rental> call, Throwable t) {
                Log.d("test",t.toString());
            }
        });
    }

    public void request_together(Together together){
        final Dialog_Progress dialog_progress = new Dialog_Progress(view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_together(together);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    dialog_progress.dismiss();
                }else{
                    dialog_progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }

    public void request_tourregionlist(int area_code, int sign_code, final int flag) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.visitkorea.or.kr").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        final Dialog_Progress dialog_progress = new Dialog_Progress(view);
        Call<JsonObject> retrofitinfo = retrofit_user.request_tourregionlist(AppController.TOURAPI, 10, 1, 1, 1, "B", "Y", flag, area_code, sign_code, "AND", "BustaCall", "json");
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("te", response.toString());
                    JsonObject jsonObject = response.body();
                    JsonObject jsonObject1 = (JsonObject) jsonObject.getAsJsonObject("response");
                    JsonObject jsonObject2 = jsonObject1.getAsJsonObject("body");
                    JsonObject jsonObject3 = jsonObject2.getAsJsonObject("items");
                    if (jsonObject3.has("item")) {
                        if ((jsonObject3.get("item") instanceof JsonArray)) {
                            final JsonArray jsonArray = (JsonArray) jsonObject3.getAsJsonArray("item");
                            ArrayList<TourRegion> tour_list = new ArrayList<TourRegion>();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                TourRegion tourRegion = new TourRegion();
                                JsonObject object = (JsonObject) jsonArray.get(i);
                                tourRegion.setAddr1(object.get("addr1").getAsString());
                                if (object.get("firstimage") == null)
                                    continue;
                                tourRegion.setFirstimage(object.get("firstimage").getAsString());
                                tourRegion.setFirstimage2(object.get("firstimage2").getAsString());
                                tourRegion.setTitle(object.get("title").getAsString());
                                if (flag != 12)
                                    tourRegion.setTel(object.get("tel").getAsString());
                                else {
                                    tourRegion.setTel("No phone num");
                                }
                                tour_list.add(tourRegion);
                            }
                            view.setListView(tour_list);
                        } else {
                            JsonObject jsonObject4 = jsonObject3.getAsJsonObject("item");
                            ArrayList<TourRegion> tour_list = new ArrayList<TourRegion>();
                            TourRegion tourRegion = new TourRegion();
                            tourRegion.setAddr1(jsonObject4.get("addr1").getAsString());
                            if (jsonObject4.get("firstimage") == null) {
                                dialog_progress.dismiss();
                                view.setListView(tour_list);
                                return;
                            }
                            tourRegion.setFirstimage(jsonObject4.get("firstimage").getAsString());
                            tourRegion.setFirstimage2(jsonObject4.get("firstimage2").getAsString());
                            tourRegion.setTitle(jsonObject4.get("title").getAsString());
                            if (flag != 12)
                                tourRegion.setTel(jsonObject4.get("tel").getAsString());
                            else {
                                tourRegion.setTel("No phone num");
                            }
                            tour_list.add(tourRegion);
                            view.setListView(tour_list);
                        }
                    }
                    dialog_progress.dismiss();
                } else {
                    Log.d("te", response.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("te", t.toString());
            }
        });
    }
}
