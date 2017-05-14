package com.me.movieticket.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.bmobdata.Order;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class AllOrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> order_list_data;
    LayoutInflater inflater;
    public AllOrderAdapter(Context context, List<Order> order_list_data) {
        this.context = context;
        this.order_list_data=order_list_data;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {

        return this.order_list_data!=null? this.order_list_data.size(): 0 ;

    }

    @Override
    public Object getItem(int position) {
        return this.order_list_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_allorder, null);
            viewHolder.tv_movie_name = (TextView) convertView.findViewById(R.id.newtv_movie_name);
            viewHolder.tv_movie_time = (TextView) convertView.findViewById(R.id.newtv_movie_time);
            viewHolder.tv_movie_location = (TextView) convertView.findViewById(R.id.newtv_movie_location);
            viewHolder.tv_movie_seat = (TextView) convertView.findViewById(R.id.newtv_movie_seat);
            viewHolder.state=(TextView) convertView.findViewById(R.id.order_state);
            viewHolder.tv_movie_price = (TextView) convertView.findViewById(R.id.newtv_movie_price);
            viewHolder.tv_order_num = (TextView) convertView.findViewById(R.id.allorder_num);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_movie_name.setText(order_list_data.get(position).getMovie_name());
        viewHolder.tv_movie_time.setText(order_list_data.get(position).getMovie_time());
        viewHolder.tv_movie_location.setText(order_list_data.get(position).getMovie_location());
        viewHolder.tv_movie_seat.setText(order_list_data.get(position).getMovie_seat());
        viewHolder.tv_movie_price.setText(order_list_data.get(position).getOrder_money());
        viewHolder.tv_order_num.setText(order_list_data.get(position).getObjectId());

        final boolean isused = order_list_data.get(position).getIsused();
        final boolean ispayed=order_list_data.get(position).getIspayde();
        if(ispayed){
            if(isused){
                viewHolder.state.setText("交易完成");
            }else {
                viewHolder.state.setText("已付款 待使用");
            }
        }else{
            viewHolder.state.setText("待付款");
        }

        return convertView;

    }

    static class ViewHolder {
        public TextView tv_movie_name;
        public TextView tv_movie_time;
        public TextView tv_movie_location;
        public TextView tv_movie_seat;
        public TextView tv_movie_price;
        public TextView tv_order_num;
        public TextView state;
    }
}
