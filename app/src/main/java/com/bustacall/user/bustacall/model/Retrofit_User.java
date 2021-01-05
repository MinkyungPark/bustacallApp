package com.bustacall.user.bustacall.model;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 2016-10-03.
 */
public interface Retrofit_User {

    @GET("/smssend.php")
    Call<Void> request_phonerequest(@Query("phonenum") String phonenum);

    @GET("/overlapnickname.php")
    Call<Void> request_overlapNickname(@Query("nickname") String nickname);

    @GET("/login.php")
    Call<Void> request_login(@Query("nickname") String nickname,@Query("phonenum") String phonenum, @Query("certificationnum") String certificationnum,@Query("token")String token);

    @GET("/mainlogin.php")
    Call<User> request_mainlogin(@Query("nickname") String nickname);

    @POST("/lent.php")
    Call<JsonObject> request_lent(@Body Rental rental);

    @GET("/reservinfo.php") //입찰 완료 푸쉬 왔을 때
    Call<Rental> request_reservinfo(@Query("rental_num")String rental_num);

    @GET("/payment.php") //이기사님으로 예약하기
    Call<Void> request_payment(@Query("nickname")String nickname,@Query("rental_num")int rental_num,@Query("bus_num")String bus_num);

    @GET("/reservinfo_clear.php") //예약완료
    Call<Rental> request_reservinfo_clear(@Query("rental_num")int rental_num);

    @POST("/together.php")
    Call<Void> request_together(@Body Together together);

    @GET("/get_rental_two.php")//렌탈 리스트
    Call<Rental_List> request_get_rental();

    @GET("/set_rental_together.php")//같이타기
    Call<Void> request_set_rental_together(@Query("start_point") String start_point,@Query("end_point") String end_point,@Query("start_region") String start_region,
                                           @Query("end_region") String end_region,@Query("text") String text,
                                           @Query("nickname") String nickname,@Query("max_user_count") int max_user_count,@Query("current_user_count") int current_user_count,
                                           @Query("money") String money,@Query("flag") int flag,@Query("type")int type,@Query("day_one") String start_day,
                                           @Query("reason") String reason,@Query("time") String time,@Query("rental_money")String rental_money,@Query("type_flag")int type_flag);

    @GET("/add_user_together.php")
    Call<JsonObject> request_add_user_together(@Query("nickname")String nickname,@Query("rental_num")int rental_num,@Query("user")int user);

    @GET("/openapi/service/rest/KorService/areaBasedList")
    Call<JsonObject> request_tourregionlist(@Query(value="serviceKey", encoded=true)String ServiceKey, @Query("numOfRows") int numofRows, @Query("pageSize") int pageSize, @Query("pageNo") int pageNo, @Query("startPage")int startPage, @Query("arrange")String arrange, @Query("listYN") String listYN, @Query("contentTypeId")int contentTypeId
            , @Query("areaCode") int areaCode, @Query("sigunguCode") int sigunguCode, @Query("MobileOS") String MobileOS, @Query("MobileApp") String MobileApp, @Query("_type")String json);

     @GET("/setting_user.php")
    Call<Void> request_setting_user(@Query("orgin_nickname")String nickname,@Query("bank_name")String bank_name,@Query("bank_account")String bank_account);

    @GET("/send_notice_onoff_user.php") // 알림 켜기, 끄기 여부 보내기
    Call<Void> request_notice_onoff(@Query("nickname")String nickname,@Query("notice")int flag);

    @GET("/delete_user.php")
    Call<Void> request_delete_user(@Query("nickname")String nickname);

    @GET("/exchange_user.php") // 환전 신청
    Call<Void> request_send_exchange(@Query("nickname") String nickname, @Query("point") int exchangePoint);
}
