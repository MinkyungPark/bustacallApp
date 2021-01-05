package com.bustacall.user.bustacall.model;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.http.Url;

/**
 * Created by user on 2016-11-01.
 */
public class Bus implements Serializable {

    ArrayList<String> bus_url = new ArrayList<>(); //버스 사진
    String nickname; //닉네임
    String bus_career;//경력
    String bus_type;//버스 타입
    String money;//돈
    String bus_num;//차 번호
    String user_count;//예약 인원
    String phone_num;//핸드폰 번호
    ArrayList<Integer> money_list=new ArrayList<>();//톨비~부가세까지 포함 0, 미포함 1
    int account_flag; //결제 되었는지 안되었는지 안되어있으면 0, 되어있으면 1

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getAccount_flag() {
        return account_flag;
    }

    public void setAccount_flag(int account_flag) {
        this.account_flag = account_flag;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public ArrayList<String> getBus_url() {
        return bus_url;
    }

    public void setBus_url(ArrayList<String> bus_url) {
        this.bus_url = bus_url;
    }

    public String getBus_career() {
        return bus_career;
    }

    public void setBus_career(String bus_career) {
        this.bus_career = bus_career;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBus_type() {
        return bus_type;
    }

    public void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBus_num() {
        return bus_num;
    }

    public void setBus_num(String bus_num) {
        this.bus_num = bus_num;
    }

    public ArrayList<Integer> getMoney_list() {
        return money_list;
    }

    public void setMoney_list(ArrayList<Integer> money_list) {
        this.money_list = money_list;
    }

    public Bus(){}
    public Bus(ArrayList<String> busImageUrls, String nickname, String bus_type, String money,
               String bus_num, ArrayList<Integer> money_list,String bus_career,int account_flag,String phone_num) {
        this.bus_url = busImageUrls;
        this.nickname = nickname;
        this.bus_type = bus_type;
        this.money = money;
        this.bus_num = bus_num;
        this.money_list = money_list;
        this.bus_career=bus_career;
        this.account_flag = account_flag;
        this.phone_num = phone_num;
        this.account_flag = 0;
    }
}
