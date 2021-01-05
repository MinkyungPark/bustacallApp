package com.bustacall.user.bustacall.presenter;

import android.util.Log;

import com.bustacall.user.bustacall.model.Retrofit_User;
import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.dialog.Dialog_Progress;
import com.bustacall.user.bustacall.model.TourRegion;
import com.bustacall.user.bustacall.view.Activity_Sliding_Tourist;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016-11-17.
 */
public class Activity_Sliding_Tourist_Presenter {
    Activity_Sliding_Tourist view;

    public Activity_Sliding_Tourist_Presenter(Activity_Sliding_Tourist view) {
        this.view = view;
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
                    if (jsonObject2.get("totalCount").getAsInt() == 0) {
                        ArrayList<TourRegion> tour_list2 = new ArrayList<TourRegion>();
                        view.setListView(tour_list2);
                    } else {
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
                                    if (object.get("tel") == null) {
                                        tourRegion.setTel("No phone num");
                                    } else {
                                        tourRegion.setTel(object.get("tel").getAsString());
                                    }
                                    tour_list.add(tourRegion);
                                }
                                view.setListView(tour_list);
                            }
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
                            if (jsonObject4.get("tel") == null) {
                                tourRegion.setTel("No phone num");
                            } else {
                                tourRegion.setTel(jsonObject4.get("tel").getAsString());
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
