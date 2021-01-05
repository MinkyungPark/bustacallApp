package com.bustacall.user.bustacall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.User;
import com.bustacall.user.bustacall.view.Activity_Reservation_Clear;
import com.bustacall.user.bustacall.view.Activity_Reservation_List;
import com.bustacall.user.bustacall.R;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-01.
 */
public class UserListBusAdpater extends BaseAdapter {

    public User user = new User();
    ArrayList<Rental> rentals = new ArrayList<>();
    public Context context;
    int count = 0;
    ViewHoder viewHoder = null;

    public UserListBusAdpater(User user, Context context) {
        this.user = user;
        this.context = context;
        rentals = user.getRental_list();
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return rentals.size();
    }

    @Override
    public Object getItem(int position) {
        return rentals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (v == null) {
            viewHoder = new ViewHoder();
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.user_list_item, null); //TODO 에러확인
            viewHoder.iv_type_two = (ImageView) v.findViewById(R.id.user_list_item_lv_type_two);
            viewHoder.iv_type = (ImageView) v.findViewById(R.id.user_list_item_lv_type);
            viewHoder.tv_money = (TextView) v.findViewById(R.id.user_list_item_tv_money);
            viewHoder.tv_start = (TextView) v.findViewById(R.id.user_list_item_tv_start);
            viewHoder.tv_end = (TextView) v.findViewById(R.id.user_list_item_tv_end);
            viewHoder.tv_time = (TextView) v.findViewById(R.id.user_list_item_tv_time);
            viewHoder.tv_day = (TextView) v.findViewById(R.id.user_list_item_tv_day);
            viewHoder.bt_type = (Button) v.findViewById(R.id.user_list_item_bt_type);

            v.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) v.getTag();
        }
        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        viewHoder.iv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.user_list_item_lv_type) {
                    if (rentals.get(pos).getType() == 1) {//왕복
                        if (rentals.get(pos).getType_two() == 2) { //기사선택하기
                            Intent intent = new Intent(context, Activity_Reservation_List.class);
                            intent.putExtra("rental_num",rentals.get(pos).getRental_num());
                            context.startActivity(intent);
                        } else if (rentals.get(pos).getType_two() == 3) { //입금전

                        } else if (rentals.get(pos).getType_two() == 4) { //상세보기
                            Intent intent = new Intent(context, Activity_Reservation_Clear.class);
                            intent.putExtra("rental_num",rentals.get(pos).getRental_num());//0이 그냥 들어갔을때
                            context.startActivity(intent);
                        } else if (rentals.get(pos).getType_two() == 5) { //이용완료

                        }
                    } else if(rentals.get(pos).getType() == 2) { //편도
                        if (rentals.get(pos).getType_two() == 2) { //기사선택하기
                            Intent intent = new Intent(context, Activity_Reservation_List.class);
                            intent.putExtra("rental_num",rentals.get(pos).getRental_num());
                            context.startActivity(intent);
                        } else if (rentals.get(pos).getType_two() == 3) { //입금전

                        } else if (rentals.get(pos).getType_two() == 4) { //상세보기
                            Intent intent = new Intent(context, Activity_Reservation_Clear.class);
                            intent.putExtra("rental_num",rentals.get(pos).getRental_num());//0이 그냥 들어갔을때
                            context.startActivity(intent);
                        } else if (rentals.get(pos).getType_two() == 5) { //이용완료

                        }
                    }
                }
            }
        });

        if (rentals.get(pos).getType() == 1) { //왕복
            viewHoder.iv_type_two.setImageResource(R.drawable.between_icon_goandback);
            if (rentals.get(pos).getType_two() == 1) {//입찰중
                viewHoder.iv_type.setImageResource(R.drawable.type_one);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_one_button);
            } else if (rentals.get(pos).getType_two() == 2) {//기사선택하기, 입찰완료
                viewHoder.iv_type.setImageResource(R.drawable.type_two);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_twoandthree_button);
            } else if (rentals.get(pos).getType_two() == 3) {//입금전 ,입찰완료
                viewHoder.iv_type.setImageResource(R.drawable.type_three);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_twoandthree_button);
            } else if (rentals.get(pos).getType_two() == 4) {//상세보기
                viewHoder.iv_type.setImageResource(R.drawable.type_four);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_four_button);
            } else if (rentals.get(pos).getType_two() == 5) {//이용완료
                viewHoder.iv_type.setImageResource(R.drawable.type_five);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_five_button);
            }
        } else {//편도
            viewHoder.iv_type_two.setImageResource(R.drawable.between_icon);
            if (rentals.get(pos).getType_two() == 1) {//입찰중
                viewHoder.iv_type.setImageResource(R.drawable.type_one);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_one_button);
            } else if (rentals.get(pos).getType_two() == 2) {//기사선택하기, 입찰완료
                viewHoder.iv_type.setImageResource(R.drawable.type_two);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_twoandthree_button);
            } else if (rentals.get(pos).getType_two() == 3) {//입금전 ,입찰완료
                viewHoder.iv_type.setImageResource(R.drawable.type_three);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_twoandthree_button);
            } else if (rentals.get(pos).getType_two() == 4) {//상세보기
                viewHoder.iv_type.setImageResource(R.drawable.type_four);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_four_button);
            } else if (rentals.get(pos).getType_two() == 5) {//이용완료
                viewHoder.iv_type.setImageResource(R.drawable.type_five);
                setRental(pos);
                viewHoder.bt_type.setBackgroundResource(R.drawable.type_five_button);
            }
        }

        return v;
    }

    public void setRental(int pos){
        viewHoder.tv_money.setText(rentals.get(pos).getRental_money());
        viewHoder.tv_start.setText(rentals.get(pos).getStart_point_one());
        viewHoder.tv_end.setText(rentals.get(pos).getEnd_point_one());
        viewHoder.tv_time.setText(rentals.get(pos).getTime_one());
        viewHoder.tv_day.setText(rentals.get(pos).getDay_one());
    }

    class ViewHoder {
        public TextView tv_money, tv_start, tv_end, tv_time, tv_day;
        public Button bt_type;
        public ImageView iv_type, iv_type_two;
    }
}
