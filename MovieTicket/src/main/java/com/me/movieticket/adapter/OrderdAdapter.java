package com.me.movieticket.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.activity.PayActivity;
import com.me.movieticket.bmobdata.Order;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class OrderdAdapter extends BaseAdapter {
    private Context context;
    private List<Order> order_list_data;
    LayoutInflater inflater;
    public OrderdAdapter(Context context, List<Order> order_list_data) {
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
            convertView = inflater.inflate(R.layout.list_item_orderd, null);
            viewHolder.tv_movie_name = (TextView) convertView.findViewById(R.id.newtv_movie_name);
            viewHolder.tv_movie_time = (TextView) convertView.findViewById(R.id.newtv_movie_time);
            viewHolder.tv_movie_location = (TextView) convertView.findViewById(R.id.newtv_movie_location);
            viewHolder.tv_movie_seat = (TextView) convertView.findViewById(R.id.newtv_movie_seat);
            viewHolder.tv_movie_price = (TextView) convertView.findViewById(R.id.newtv_movie_price);
            viewHolder.tv_order_num = (TextView) convertView.findViewById(R.id.newtv_order_num);
            viewHolder.ib_used = (Button) convertView.findViewById(R.id.now_use);
            viewHolder.ib_cancel = (Button) convertView.findViewById(R.id.reback_money);
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

        final String id = order_list_data.get(position).getObjectId();
        final String money=order_list_data.get(position).getOrder_money();
        //删除订单
        viewHolder.ib_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "订单"+id+"退款成功，请刷新", Toast.LENGTH_LONG).show();
                Order order_delete = new Order();
                order_delete.setObjectId(id);
                order_delete.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "成功");
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });

            }
        });
        //支付订单
        viewHolder.ib_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("请至影院自助取票机上凭订单号出票");
                builder.setTitle("出票方式");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        return convertView;

    }

    static class ViewHolder {
        public TextView tv_movie_name;
        public TextView tv_movie_time;
        public TextView tv_movie_location;
        public TextView tv_movie_seat;
        public TextView tv_movie_price;
        public TextView tv_order_num;
        public Button ib_cancel;
        public Button ib_used;
    }
}
