package com.bustacall.user.bustacall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacall.user.bustacall.model.Rental_List;
import com.bustacall.user.bustacall.R;

/**
 * Created by user on 2016-11-10.
 */
public class TogetherListAdapter extends BaseAdapter {
    Rental_List rental_list = new Rental_List();
    Context context;
    ViewHoder_Together viewHoder = new ViewHoder_Together();

    public TogetherListAdapter(Context context, Rental_List rentals) {
        this.rental_list = rentals;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rental_list.getRental_list().size();
    }

    @Override
    public Object getItem(int position) {
        return rental_list.getRental_list().get(position);
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

    public int getItemViewType_view(int position) {
        int wayType = rental_list.getRental_list().get(position).getType(); // 1 : 왕복, 2: 편도
        int togetherType = rental_list.getRental_list().get(position).getTogether().getFlag(); // 0 : 기본, 1 : 빈자리 타기, 2 : 같이타기
        int type = 0;
        if (wayType == 1 && togetherType == 1) { // 왕복 & 빈자리 타기
            type = 1;
        } else if (togetherType == 2) { // 같이타기 & 편도 무조건
            type = 2;
        } else if (wayType == 2 && togetherType == 1) { // 편도 & 빈자리 타기
            type = 3;
        }
        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        View v = convertView;

        if (v == null) {
            viewHoder = new ViewHoder_Together();
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.together_list_item, null);
            viewHoder.tv_day = (TextView) v.findViewById(R.id.together_list_item_tv_day);
            viewHoder.tv_money = (TextView) v.findViewById(R.id.together_list_item_tv_money);
            viewHoder.tv_start = (TextView) v.findViewById(R.id.together_list_item_tv_start);
            viewHoder.tv_end = (TextView) v.findViewById(R.id.together_list_item_tv_end);
            viewHoder.tv_reason = (TextView) v.findViewById(R.id.together_list_item_tv_reason);
            viewHoder.tv_time = (TextView) v.findViewById(R.id.together_list_item_tv_time);
            viewHoder.tv_sit = (TextView) v.findViewById(R.id.together_list_item_tv_sit_count); //잔여 좌석
            viewHoder.tv_user = (TextView) v.findViewById(R.id.together_list_item_tv_user); //지금 유저/전체 유저
            viewHoder.ll_image = (LinearLayout) v.findViewById(R.id.together_list_item_ll_sit_type);//sit type 최소 충당인지 아닌지
            viewHoder.ll_main = (LinearLayout) v.findViewById(R.id.together_list_item_ll_main);//전체를 감싼 것
            viewHoder.iv_imgae = (ImageView) v.findViewById(R.id.together_list_item_iv_image);
            viewHoder.iv_sit = (ImageView) v.findViewById(R.id.together_list_item_iv_sit);
            viewHoder.iv_line = (ImageView) v.findViewById(R.id.together_list_item_iv_line);
            viewHoder.tv_user_min = (TextView) v.findViewById(R.id.together_list_item_tv_user_min);

            v.setTag(viewHoder);

        } else {
            viewHoder = (ViewHoder_Together) v.getTag();
        }

        if (rental_list.getRental_list().get(position).getTogether().getFlag() == 2) { //같이 타기
            viewHoder.iv_imgae.setImageResource(R.drawable.together_icon);
            viewHoder.iv_line.setImageResource(R.drawable.between_icon); //편도
            viewHoder.iv_sit.setImageResource(R.drawable.sit_icon); //sit 위에 있는 그림인데
            viewHoder.ll_main.setBackgroundResource(R.drawable.mint_rectangle_box);
            viewHoder.tv_reason.setText(rental_list.getRental_list().get(position).getRental_reason());
            viewHoder.tv_start.setText(rental_list.getRental_list().get(position).getStart_point_one());
            viewHoder.tv_end.setText(rental_list.getRental_list().get(position).getEnd_point_one());
            viewHoder.tv_day.setText(rental_list.getRental_list().get(position).getDay_one());
            viewHoder.tv_time.setText(rental_list.getRental_list().get(position).getTime_one());
            viewHoder.tv_money.setText(rental_list.getRental_list().get(position).getTogether().getMoney() + "원");
            viewHoder.tv_sit.setText((rental_list.getRental_list().get(position).getTogether().getMax_user_count() - rental_list.getRental_list().get(position).getTogether().getCurrent_user_count()) + "석");
            viewHoder.tv_user.setText(rental_list.getRental_list().get(position).getTogether().getCurrent_user_count() + "/" + rental_list.getRental_list().get(position).getTogether().getMax_user_count());
            if (rental_list.getRental_list().get(position).getTogether().getCurrent_user_count() < 20)
                viewHoder.tv_user_min.setText("최소 20명");
            else {
                viewHoder.ll_image.setBackgroundResource(R.drawable.min_user_box);
                viewHoder.tv_user_min.setVisibility(View.GONE);
                viewHoder.tv_user.setVisibility(View.GONE);
                viewHoder.iv_sit.setVisibility(View.GONE);
            }
        }
        if (rental_list.getRental_list().get(position).getTogether().getFlag() == 1 && rental_list.getRental_list().get(position).getType() == 1) //빈자리 왕복
        {
            viewHoder.iv_line.setImageResource(R.drawable.between_icon_goandback); //왕복
            viewHoder.tv_user_min.setVisibility(View.GONE);
            setRental_list_empty(position);
        }
        if (rental_list.getRental_list().get(position).getTogether().getFlag() == 1 && rental_list.getRental_list().get(position).getType() == 2) { //빈자리 편도
            viewHoder.iv_line.setImageResource(R.drawable.between_icon);
            viewHoder.tv_user_min.setVisibility(View.GONE);
            setRental_list_empty(position);
        }
        return v;
    }

    public void setRental_list_empty(int position) { //빈자리 타기
        viewHoder.ll_main.setBackgroundResource(R.drawable.sit_box_blue);
        viewHoder.iv_imgae.setBackgroundResource(R.drawable.empty_icon); //바꿔야되는 부분
        viewHoder.iv_sit.setBackgroundResource(R.drawable.sit_icon); //바꿔야 되는 부분
        viewHoder.tv_reason.setText(rental_list.getRental_list().get(position).getRental_reason());
        viewHoder.tv_start.setText(rental_list.getRental_list().get(position).getStart_point_one());
        viewHoder.tv_end.setText(rental_list.getRental_list().get(position).getEnd_point_one());
        viewHoder.tv_day.setText(rental_list.getRental_list().get(position).getDay_one());
        viewHoder.tv_time.setText(rental_list.getRental_list().get(position).getTime_one());
        viewHoder.tv_money.setText(rental_list.getRental_list().get(position).getTogether().getMoney() + "원");
        viewHoder.tv_sit.setText((rental_list.getRental_list().get(position).getTogether().getMax_user_count() - rental_list.getRental_list().get(position).getTogether().getCurrent_user_count()) + "석");
        viewHoder.tv_user.setText(rental_list.getRental_list().get(position).getTogether().getCurrent_user_count() + "/" + rental_list.getRental_list().get(position).getTogether().getMax_user_count());
        viewHoder.tv_user_min.setVisibility(View.GONE);
    }

    class ViewHoder_Together {
        public LinearLayout ll_main, ll_image;
        public TextView tv_money, tv_start, tv_end, tv_reason, tv_time, tv_day, tv_sit, tv_user, tv_user_min;
        public ImageView iv_imgae, iv_sit, iv_line;
    }
}
