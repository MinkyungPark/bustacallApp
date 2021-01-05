package com.bustacall.user.bustacall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.model.Rental;
import com.bustacall.user.bustacall.model.User;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-10.
 */
public class UserTogetherListBusAdapter extends BaseAdapter {

    public User user = new User();
    ArrayList<Rental> rentals = new ArrayList<>();
    Context context;
    ViewHoder_Together viewHoder = new ViewHoder_Together();

    public UserTogetherListBusAdapter(Context context, User user) {
        this.user = user;
        this.context = context;
        this.rentals = user.getRental_list_together();
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
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        View v = convertView;

        if (v == null) {
            viewHoder = new ViewHoder_Together();
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.user_together_list_item, null);
            viewHoder.tv_day = (TextView) v.findViewById(R.id.user_together_list_item_tv_day);
            viewHoder.tv_money = (TextView) v.findViewById(R.id.user_together_list_item_tv_money);
            viewHoder.tv_start = (TextView) v.findViewById(R.id.user_together_list_item_tv_start);
            viewHoder.tv_end = (TextView) v.findViewById(R.id.user_together_list_item_tv_end);
            viewHoder.tv_time = (TextView) v.findViewById(R.id.user_together_list_item_tv_time);
            viewHoder.iv_type = (ImageView) v.findViewById(R.id.user_together_list_item_iv_type_type);
            viewHoder.iv_time_right = (ImageView) v.findViewById(R.id.user_together_list_item_iv_time_right);
            viewHoder.iv_type_right = (ImageView) v.findViewById(R.id.user_together_list_item_iv_type_right);
            viewHoder.tv_sit_min = (TextView) v.findViewById(R.id.user_together_list_item_tv_user_min);//최소 20명
            viewHoder.tv_user = (TextView) v.findViewById(R.id.user_together_list_item_tv_user); //" / "
            viewHoder.ll_type = (LinearLayout) v.findViewById(R.id.user_together_list_item_ll_type);
            viewHoder.iv_sit = (ImageView) v.findViewById(R.id.user_together_list_item_iv_sit);
            viewHoder.iv_line = (ImageView) v.findViewById(R.id.user_together_list_item_iv_line);
            v.setTag(viewHoder);

        } else {
            viewHoder = (ViewHoder_Together) v.getTag();
        }

        if (rentals.get(position).getTogether().getFlag() == 2) { //같이 타기
            if (rentals.get(position).getTogether().getType_flag() == 1) { //입금전
                viewHoder.iv_type_right.setImageResource(R.drawable.type_three);
            } else {
                viewHoder.iv_type_right.setImageResource(R.drawable.type_three_together);
            }
            viewHoder.iv_time_right.setImageResource(R.drawable.together_icon_two);
            viewHoder.iv_line.setImageResource(R.drawable.between_icon);
            viewHoder.iv_sit.setImageResource(R.drawable.sit_icon); //sit 위에 있는 그림인데
            viewHoder.tv_start.setText(rentals.get(position).getStart_point_one());
            viewHoder.tv_end.setText(rentals.get(position).getEnd_point_one());
            viewHoder.tv_day.setText(rentals.get(position).getDay_one());
            viewHoder.tv_time.setText(rentals.get(position).getTime_one());
            viewHoder.tv_money.setText(rentals.get(position).getTogether().getMoney());
            viewHoder.tv_user.setText(rentals.get(position).getTogether().getCurrent_user_count() + "/" + rentals.get(position).getTogether().getMax_user_count());
            if (rentals.get(position).getTogether().getCurrent_user_count() < 20) {
                viewHoder.tv_sit_min.setText("최소 20명");
                viewHoder.iv_type.setImageResource(R.drawable.empty_type_button_one);
            } else {
                viewHoder.iv_type.setImageResource(R.drawable.empty_type_button_two); //이상함
                viewHoder.ll_type.setBackgroundResource(R.drawable.min_user_box);
                viewHoder.tv_sit_min.setVisibility(View.GONE);
                viewHoder.tv_user.setVisibility(View.GONE);
                viewHoder.iv_sit.setVisibility(View.GONE);
            }
        }
        if (rentals.get(position).getTogether().getFlag() == 1 && rentals.get(position).getType() == 1) //빈자리 왕복
        {
            if (rentals.get(position).getTogether().getType_flag() == 1) { //입금전
                viewHoder.iv_type_right.setImageResource(R.drawable.type_three);
            } else {
                viewHoder.iv_type_right.setImageResource(R.drawable.type_three_together);
            }
            viewHoder.iv_line.setImageResource(R.drawable.between_icon_goandback); //왕복
            viewHoder.ll_type.setVisibility(View.INVISIBLE);
            setRental_list_empty(position);
        }
        if (rentals.get(position).getTogether().getFlag() == 1 && rentals.get(position).getType() == 2) { //빈자리 편도
            if (rentals.get(position).getTogether().getType_flag() == 1) { //입금전
                viewHoder.iv_type_right.setImageResource(R.drawable.type_three);
            } else {
                viewHoder.iv_type_right.setImageResource(R.drawable.type_three_together);
            }
            viewHoder.iv_line.setImageResource(R.drawable.between_icon); //왕복
            viewHoder.ll_type.setVisibility(View.INVISIBLE);
            setRental_list_empty(position);
        }
        return v;
    }

    public void setRental_list_empty(int position) { //빈자리 타기
        viewHoder.iv_type.setImageResource(R.drawable.type_four_button);
        viewHoder.iv_time_right.setImageResource(R.drawable.empty_icon_two);
        viewHoder.iv_sit.setImageResource(R.drawable.sit_icon);
        viewHoder.tv_start.setText(rentals.get(position).getStart_point_one());
        viewHoder.tv_end.setText(rentals.get(position).getEnd_point_one());
        viewHoder.tv_day.setText(rentals.get(position).getDay_one());
        viewHoder.tv_time.setText(rentals.get(position).getTime_one());
        viewHoder.tv_money.setText(rentals.get(position).getTogether().getMoney());
    }

    class ViewHoder_Together {
        public LinearLayout ll_type;
        public TextView tv_money, tv_start, tv_end, tv_time, tv_day, tv_user, tv_sit_min;
        public ImageView iv_time_right, iv_type_right, iv_sit, iv_line, iv_type;
    }
}
