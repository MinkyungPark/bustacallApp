package com.bustacall.user.bustacall;

import android.util.Log;

import com.bustacall.user.bustacall.model.Rental_List;

/**
 * Created by user on 2016-11-12.
 */
public class RegionDivision {
    Rental_List rental_list = new Rental_List();
    Rental_List new_rental_list = new Rental_List(); //flag로 구분한 list
    Rental_List final_rental_list = new Rental_List(); //지역이 하나라도 같은 경우 들어가는 list
    Rental_List temp_rental_list = new Rental_List(); //같은게 하나도 없는 list
    Rental_List final_temp_rental_list = new Rental_List();

    String start_region,end_region;
    public RegionDivision(){}

    public RegionDivision(String start_region,String end_region, Rental_List rental_list){
        this.rental_list = rental_list;
        this.start_region = start_region;
        this.end_region = end_region;
    }

    public Rental_List setRegion_list_deivision(){

        /////////////////////////////////////빈자리 타기, 같이 타기///////////////////////////////////////////
        for(int i=0;i<rental_list.getRental_list().size();i++){
            if(rental_list.getRental_list().get(i).getTogether().getFlag() == 1)//빈자리 타기
            {
                new_rental_list.getRental_list().add(rental_list.getRental_list().get(i));
            }else if(rental_list.getRental_list().get(i).getTogether().getFlag()==2){//같이 타기
                new_rental_list.getRental_list().add(rental_list.getRental_list().get(i));
            }
        }

        ///////////////////////////////////지역 확인/////////////////////////////////////////////
        for(int i=0;i<new_rental_list.getRental_list().size();i++){
            if(start_region.equals(new_rental_list.getRental_list().get(i).getStart_region()) && end_region.equals(new_rental_list.getRental_list().get(i).getEnd_region())) //둘다 같은 경우
            {
                final_rental_list.getRental_list().add(new_rental_list.getRental_list().get(i));
                Log.d("text",final_rental_list.toString());
            }else{
                temp_rental_list.getRental_list().add(new_rental_list.getRental_list().get(i));
            }
        }

        for(int i=0;i<temp_rental_list.getRental_list().size();i++){
            if(start_region.equals(temp_rental_list.getRental_list().get(i).getStart_region())||end_region.equals(temp_rental_list.getRental_list().get(i).getEnd_region())) //하나라도 같은 경우
            {
                final_rental_list.getRental_list().add(temp_rental_list.getRental_list().get(i));
                Log.d("text",final_rental_list.toString());
            }else{
                final_temp_rental_list.getRental_list().add(temp_rental_list.getRental_list().get(i));
            }
        }

        for(int i=0;i<final_temp_rental_list.getRental_list().size();i++){
            final_rental_list.getRental_list().add(final_temp_rental_list.getRental_list().get(i));
        }

        return final_rental_list;
    }
}
