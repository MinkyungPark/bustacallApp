package com.bustacall.user.bustacall.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2016-10-29.
 */
public class Rental implements Serializable {
    ArrayList<Bus> bus_list=new ArrayList<>(); //버스 리스트
    String nickname; //임시 닉네임
    String start_point_one,start_point_two,end_point_one,end_point_two; //출발지, 도착지
    String day_one,day_two,time_one,time_two; //날짜, 시간
    String rental_reason; //목적
    String user_count; //명수
    String user_max_count; //bus의 최종 명수
    String rental_money; //비용
    String start_region;// 도 구분
    String end_region;//도착지 구분
    String end_region_two;//도착지 시군구 구분
    int bus_45;//45인승 대형
    int bus_35;//35인승 중형
    int bus_28;//28인승 리무진
    int bus_25;//25인승 소형
    int type; //왕복,편도
    //1일때 왕복
    //2일때 편도
    int type_two; //예약, 기사 선택 하기, 입금 전, 상세보기, 이용완료 1,2,3,4,5
    int rental_num; //각각의 렌탈 고유 넘버
    String current_day;//예약한 날짜
    String end_day;//마감날짜
    int account_flag; //결제 되었는지 안되었는지 안되어있으면 0, 되어있으면 1
    Together together = new Together(); //합승 여부 클래스

    public String getEnd_region_two() {
        return end_region_two;
    }

    public void setEnd_region_two(String end_region_two) {
        this.end_region_two = end_region_two;
    }

    public String getEnd_region() {
        return end_region;
    }

    public void setEnd_region(String end_region) {
        this.end_region = end_region;
    }

    public int getAccount_flag() {
        return account_flag;
    }

    public void setAccount_flag(int account_flag) {
        this.account_flag = account_flag;
    }

    public Together getTogether() {
        return together;
    }

    public void setTogether(Together together) {
        this.together = together;
    }

    public String getUser_max_count() {
        return user_max_count;
    }

    public void setUser_max_count(String user_max_count) {
        this.user_max_count = user_max_count;
    }

    public String getEnd_day() {
        return end_day;
    }

    public void setEnd_day(String end_day) {
        this.end_day = end_day;
    }

    public String getCurrent_day() {
        return current_day;
    }

    public void setCurrent_day(String current_day) {
        this.current_day = current_day;
    }

    public ArrayList<Bus> getBus_list() {
        return bus_list;
    }

    public void setBus_list(ArrayList<Bus> bus_list) {
        this.bus_list = bus_list;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getBus_45() {
        return bus_45;
    }

    public void setBus_45(int bus_45) {
        this.bus_45 = bus_45;
    }

    public int getBus_35() {
        return bus_35;
    }

    public int getRental_num() {
        return rental_num;
    }

    public void setRental_num(int rental_num) {
        this.rental_num = rental_num;
    }

    public void setBus_35(int bus_35) {
        this.bus_35 = bus_35;
    }

    public int getBus_28() {
        return bus_28;
    }

    public void setBus_28(int bus_28) {
        this.bus_28 = bus_28;
    }

    public int getBus_25() {
        return bus_25;
    }

    public void setBus_25(int bus_25) {
        this.bus_25 = bus_25;
    }

    public String getStart_region() {
        return start_region;
    }

    public void setStart_region(String start_region) {
        this.start_region = start_region;
    }

    public Rental(){}

    public Rental(String start_point_one, String start_point_two, String end_point_one,
                  String end_point_two, String day_one, String day_two, String time_one, String time_two,
                  String rental_reason, String user_count,int type,String rental_money,int type_two,
                  String current_day,int bus_45,int bus_35, int bus_28, int bus_25,
                  String nickname,int rental_num,ArrayList<Bus> urls,String end_day,String user_max_count,
                  Together to,int account_flag,String start_region,String end_region) {
        this.start_point_one = start_point_one;
        this.start_point_two = start_point_two;
        this.end_point_one = end_point_one;
        this.end_point_two = end_point_two;
        this.day_one = day_one;
        this.day_two = day_two;
        this.time_one = time_one;
        this.time_two = time_two;
        this.rental_reason = rental_reason;
        this.type = type;
        this.user_count=user_count;
        this.rental_money=rental_money;
        this.type_two = type_two;
        this.current_day = current_day;
        this.bus_45 = bus_45;
        this.bus_35 = bus_35;
        this.bus_28 = bus_28;
        this.bus_25 = bus_25;
        this.nickname = nickname;
        this.rental_num = rental_num;
        this.bus_list = urls;
        this.end_day = end_day;
        this.user_max_count=user_max_count;
        this.together = to;
        this.account_flag = account_flag;
        this.start_region = start_region;
        this.end_region =end_region;
        this.account_flag = 0; //결제 안되어있는것
        this.type_two=0; //처음에 아무것도 안한상태.
    }

    public int getType_two() {
        return type_two;
    }

    public void setType_two(int type_two) {
        this.type_two = type_two;
    }
    public String getRental_money() {
        return rental_money;
    }

    public void setRental_money(String rental_money) {
        this.rental_money = rental_money;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public String getStart_point_one() {
        return start_point_one;
    }

    public void setStart_point_one(String start_point_one) {
        this.start_point_one = start_point_one;
    }

    public String getStart_point_two() {
        return start_point_two;
    }

    public void setStart_point_two(String start_point_two) {
        this.start_point_two = start_point_two;
    }

    public String getEnd_point_one() {
        return end_point_one;
    }

    public void setEnd_point_one(String end_point_one) {
        this.end_point_one = end_point_one;
    }

    public String getEnd_point_two() {
        return end_point_two;
    }

    public void setEnd_point_two(String end_point_two) {
        this.end_point_two = end_point_two;
    }

    public String getDay_one() {
        return day_one;
    }

    public void setDay_one(String day_one) {
        this.day_one = day_one;
    }

    public String getDay_two() {
        return day_two;
    }

    public void setDay_two(String day_two) {
        this.day_two = day_two;
    }

    public String getTime_one() {
        return time_one;
    }

    public void setTime_one(String time_one) {
        this.time_one = time_one;
    }

    public String getTime_two() {
        return time_two;
    }

    public void setTime_two(String time_two) {
        this.time_two = time_two;
    }

    public String getRental_reason() {
        return rental_reason;
    }

    public void setRental_reason(String rental_reason) {
        this.rental_reason = rental_reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
