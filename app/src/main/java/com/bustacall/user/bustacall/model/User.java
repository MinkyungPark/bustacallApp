package com.bustacall.user.bustacall.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2016-10-23.
 */
public class User implements Serializable {
    String user_point; //포인트
    String user_nickname; //닉네임
    String user_phonenum; //폰넘버
    String user_account; //계좌번호
    String user_bank; //은행 이름
    String pushtoken;
    ArrayList<Rental> rental_list = new ArrayList<>(); //전부 합승이 아닌 대절용.
    ArrayList<Rental> rental_list_together = new ArrayList<>(); //only 합승용.

    public ArrayList<Rental> getRental_list_together() {
        return rental_list_together;
    }

    public void setRental_list_together(ArrayList<Rental> rental_list_together) {
        this.rental_list_together = rental_list_together;
    }

    public String getUser_bank() {
        return user_bank;
    }

    public void setUser_bank(String user_bank) {
        this.user_bank = user_bank;
    }

    public String getPushtoken() {
        return pushtoken;
    }

    public void setPushtoken(String pushtoken) {
        this.pushtoken = pushtoken;
    }

    public User(){
        this.user_point ="0";
    }

    public User(String user_point, String user_nickname, String user_phonenum, String user_account, ArrayList<Rental> rental_list,String pushtoken,String bank,
                ArrayList<Rental> rental_list_together) {
        this.user_point = user_point;
        this.user_nickname = user_nickname;
        this.user_phonenum = user_phonenum;
        this.user_account = user_account;
        this.rental_list = rental_list;
        this.pushtoken = pushtoken;
        this.user_bank = bank;
        this.rental_list_together = rental_list_together;
    }

    public ArrayList<Rental> getRental_list() {
        return rental_list;
    }

    public void setRental_list(ArrayList<Rental> rental_list) {
        this.rental_list = rental_list;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_phonenum() {
        return user_phonenum;
    }

    public void setUser_phonenum(String user_phonenum) {
        this.user_phonenum = user_phonenum;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_point() {
        return user_point;
    }

    public void setUser_point(String user_point) {
        this.user_point = user_point;
    }
}
