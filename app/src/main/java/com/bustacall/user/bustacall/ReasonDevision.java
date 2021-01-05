package com.bustacall.user.bustacall;

import com.bustacall.user.bustacall.model.Rental_List;

/**
 * Created by user on 2016-11-12.
 */
public class ReasonDevision {
    Rental_List rental_list = new Rental_List();
    Rental_List new_rental_list = new Rental_List(); //flag로 구분한 list
    Rental_List reason_rental_list = new Rental_List(); //목적이 같은 list
    Rental_List temp_rental_list = new Rental_List(); //같은게 하나도 없는 list

    String start_region,reason;
    public ReasonDevision(){

    }

    public ReasonDevision(String start_region, String reason, Rental_List rental_list){
        this.rental_list = rental_list;
        this.start_region = start_region;
        this.reason = reason;
    }

    public Rental_List setRegion_list_reason(){

        /////////////////////////////////////빈자리 타기, 같이 타기///////////////////////////////////////////
        for(int i=0;i<rental_list.getRental_list().size();i++){
            if(rental_list.getRental_list().get(i).getTogether().getFlag() == 1)//빈자리 타기
            {
                new_rental_list.getRental_list().add(rental_list.getRental_list().get(i));
            }else if(rental_list.getRental_list().get(i).getTogether().getFlag()==2){//같이 타기
                new_rental_list.getRental_list().add(rental_list.getRental_list().get(i));
            }
        }

        ///////////////////////////////////목적 확인/////////////////////////////////////////////
        for(int i=0;i<new_rental_list.getRental_list().size();i++){
            if(new_rental_list.getRental_list().get(i).getRental_reason().equals(reason)) //목적이 같으면
            {
                reason_rental_list.getRental_list().add(new_rental_list.getRental_list().get(i));
            }else{ //목적이 다르면
                temp_rental_list.getRental_list().add(new_rental_list.getRental_list().get(i));
            }
        }
        ///////////////////////////////////목적이 같으면서 지역이 같으면(start_region)/////////////////////////////////////////////
//
//        for(int i=0;i<reason_rental_list.getRental_list().size();i++){
//            if(start_region.equals(reason_rental_list.getRental_list().get(i).getStart_region())) //목적이 같으면서 지역이 같은 list
//            {
//                final_reason_rental_list.getRental_list().add(reason_rental_list.getRental_list().get(i));
//            }else{
//                final_temp_rental_list.getRental_list().add(reason_rental_list.getRental_list().get(i));
//            }
//        }

//        ////////////////////////////////////////목적이 같으면서 지역이 다른경우 그냥 대입//////////////////////////////////////////
//        for(int i=0;i<final_temp_rental_list.getRental_list().size();i++){
//            final_reason_rental_list.getRental_list().add(final_temp_rental_list.getRental_list().get(i));
//        }
//
//        ////////////////////////////////////////나머지 다 대입///////////////////////////////////////
//        for(int i=0;i<temp_rental_list.getRental_list().size();i++){
//            final_reason_rental_list.getRental_list().add(temp_rental_list.getRental_list().get(i));
//        }

        return reason_rental_list;
    }
}
