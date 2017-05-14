package com.me.movieticket.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.me.movieticket.R;
import com.me.movieticket.adapter.OrderAdapter;
import com.me.movieticket.database.DataOrder;

/**
 * Created by MANITO on 2017/3/28.
 */

public class MyOrder extends AppCompatActivity{
    private ListView lv_list;
    private SQLiteDatabase dbReader;
    private SQLiteDatabase dbWriter;
    private DataOrder dataOrder1;
    private Cursor cursor;
    private OrderAdapter orderAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        //打开数据库
        dataOrder1 = new DataOrder(MyOrder.this);
        lv_list = (ListView) findViewById(R.id.order_list);
        //读取订单信息
        dbReader = dataOrder1.getReadableDatabase();
        cursor = dbReader.query(DataOrder.TABLE_NAME_ORDER, null,null,null,
                null,null,null,null);
        orderAdapter = new OrderAdapter(MyOrder.this, cursor);
        lv_list.setAdapter(orderAdapter);
        dbReader.close();

    }
}
