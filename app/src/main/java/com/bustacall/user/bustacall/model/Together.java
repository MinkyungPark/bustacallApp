package com.bustacall.user.bustacall.model;

import java.io.Serializable;

/**
 * Created by user on 2016-11-06.
 */
public class Together implements Serializable {
    int flag;//합승 여부 0 : 합승 안함, 1 : 빈자리 타기, 2 : 같이 타기
    int type_flag;//같이 타기 리스트 플래그 1: 입금전 2: 입금완료
    int max_user_count;//몇명이 합승할것인지
    int current_user_count;//현재까지 몇명이 합승중인지
    String money;//합승 가격.
    int rental_num;//고유 렌탈 num
    String text;//같이 타기 할때 내용

    public int getType_flag() {
        return type_flag;
    }

    public void setType_flag(int type_flag) {
        this.type_flag = type_flag;
    }

    public int getCurrent_user_count() {
        return current_user_count;
    }

    public void setCurrent_user_count(int current_user_count) {
        this.current_user_count = current_user_count;
    }

    public int getMax_user_count() {
        return max_user_count;
    }

    public void setMax_user_count(int max_user_count) {
        this.max_user_count = max_user_count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Together(int flag, int max_user_count, int current_user_count, String money) {
        this.flag = flag;
        this.money = money;
        this.max_user_count = max_user_count;
        this.current_user_count = current_user_count;
        this.current_user_count= 0;
        this.flag = 0;
        this.type_flag = 1; //입금전
    }

    public int getRental_num() {
        return rental_num;
    }

    public void setRental_num(int rental_num) {
        this.rental_num = rental_num;
    }

    public Together(){
        this.flag = 0;
        this.type_flag =1;
    }
}
