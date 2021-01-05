package com.bustacall.user.bustacall.model;

import java.io.Serializable;

/**
 * Created by user on 2016-11-15.
 */
public class TourRegion implements Serializable {
    String areaCode; // 경기, 수원 도를 나누는 기준
    String sigunguCode; // 구,군,시를 나누는 기준
    String contentTypeId; //관광, 숙박 등 타입
    String title; //장소 이름
    String firstimage; //첫번째 이미지
    String firstimage2; //두번째 이미지
    String tel; //해당 장소 번호
    String addr1; //전체 지역

   public TourRegion(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSigunguCode() {
        return sigunguCode;
    }

    public void setSigunguCode(String sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    public String getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

}
