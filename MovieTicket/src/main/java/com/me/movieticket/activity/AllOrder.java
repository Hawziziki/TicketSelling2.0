package com.me.movieticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.adapter.AllOrderAdapter;
import com.me.movieticket.adapter.NewOrderAdapter;
import com.me.movieticket.adapter.OrderdAdapter;
import com.me.movieticket.bmobdata.Order;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by MANITO on 2017/3/28.
 */

public class AllOrder extends AppCompatActivity{
    private ListView lv_list;
    private AllOrderAdapter orderAdapter;
    private List<Order> order_list_data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        initData();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
        System.out.println("按下了back键onBackPressed()");
    }

    private void initData() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.setLimit(20);
        //执行查询方法
        query.order("-createdAt")
                .findObjects(new FindListener<Order>() {

                    @Override
                    public void done(List<Order> order_list_data, BmobException e) {
                        if(e==null){
                            lv_list = (ListView) findViewById(R.id.order_list);
                            orderAdapter = new AllOrderAdapter(AllOrder.this,order_list_data);
                            lv_list.setAdapter(orderAdapter);
                            Toast.makeText(AllOrder.this,"查询成功：共" + order_list_data.size() + "条数据。",Toast.LENGTH_SHORT);

                        }else{
                            Toast.makeText(AllOrder.this,"查询订单失败",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}
