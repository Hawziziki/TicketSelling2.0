package com.me.movieticket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.activity.PayActivity;
import com.me.movieticket.bmobdata.Order;
import com.me.movieticket.views.TabUserView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import rx.Subscription;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class NewOrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> order_list_data;
    LayoutInflater inflater;
    public NewOrderAdapter(Context context, List<Order> order_list_data) {
        this.context = context;
        //this.cursor = cursor;
        this.order_list_data=order_list_data;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        Log.i("getCount方法运行","yes");
        //Log.i("getCount方法运行", String.valueOf(order_list_data.size()));
        return this.order_list_data!=null? this.order_list_data.size(): 0 ;

    }

    @Override
    public Object getItem(int position) {
        Log.i("getItem方法运行", String.valueOf(position));
        return this.order_list_data.get(position);
        //cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("getView方法运行","yes");
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_neworder, null);
            viewHolder.tv_movie_name = (TextView) convertView.findViewById(R.id.newtv_movie_name);
            viewHolder.tv_movie_time = (TextView) convertView.findViewById(R.id.newtv_movie_time);
            viewHolder.tv_movie_location = (TextView) convertView.findViewById(R.id.newtv_movie_location);
            viewHolder.tv_movie_seat = (TextView) convertView.findViewById(R.id.newtv_movie_seat);
            viewHolder.tv_movie_price = (TextView) convertView.findViewById(R.id.newtv_movie_price);
            viewHolder.tv_order_num = (TextView) convertView.findViewById(R.id.newtv_order_num);
            viewHolder.ib_payed = (Button) convertView.findViewById(R.id.neworder_pay);
            viewHolder.ib_cancel = (Button) convertView.findViewById(R.id.newib_cancel);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        cursor.moveToPosition(position);
//        viewHolder.tv_movie_name.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_NAME)));
//        viewHolder.tv_movie_time.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_TIME)));
//        viewHolder.tv_movie_location.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_LOCATION)));
//        viewHolder.tv_movie_seat.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_SEAT)));
//        viewHolder.tv_movie_price.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_MONEY)));
//
//        final int id = cursor.getInt(cursor.getColumnIndex(DataOrder.ID));
//        final String seatInfo = cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_SEAT));
//        final String movieName = cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_NAME));
//        final String movieTime = cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_TIME));
//        viewHolder.ib_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "取消了订单", Toast.LENGTH_SHORT).show();
//                //TabUserView.instance.cancelOrder(id, seatInfo, movieName, movieTime);
//
//            }
//        });

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
                Toast.makeText(context, "删除了"+id+"号订单，请刷新", Toast.LENGTH_SHORT).show();
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
        viewHolder.ib_payed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
//                intent.putExtra("order_num",id);
//                intent.putExtra("order_price",money);
                SharedPreferences mySharedPreferences= context.getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                //实例化SharedPreferences.Editor对象（第二步）
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                //用putString的方法保存数据
                editor.putString("order_num", id);
                editor.putString("order_price", money);
                editor.commit();
                intent.setClass(context, PayActivity.class);
                context.startActivity(intent);

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
        public Button ib_payed;
    }
}
