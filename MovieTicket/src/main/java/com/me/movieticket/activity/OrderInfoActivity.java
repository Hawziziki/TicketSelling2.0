package com.me.movieticket.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.bmobdata.Order;
import com.me.movieticket.database.DataOrder;
import com.me.movieticket.entity.TicketItem;

import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class OrderInfoActivity extends AppCompatActivity {
    private ImageButton ib_back;
    private TicketItem ticketItem;
    private List<Integer> cur_order;

    private TextView tv_movie_name;
    private TextView tv_movie_time;
    private TextView tv_movie_location;
    private TextView tv_movie_seat;
    private TextView tv_order_data;
    private TextView tv_all_price;
    private Button order_pay;

    private DataOrder db_order;
    private SQLiteDatabase db_writter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        //接受电影票信息和当前预订座位
        initData();
        initView();
        order_pay= (Button) findViewById(R.id.order_pay);
        order_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存储Bmob数据
                storeBmobOrder();
                startActivity(new Intent(OrderInfoActivity.this,MyOrdering.class));
                OrderInfoActivity.this.finish();
            }
        });

        //存储本地数据库
        storeOrder();
    }

    private void storeBmobOrder() {
        Order order = new Order();
        order.setOrder_num("123132131311");
        order.setMovie_name(tv_movie_name.getText().toString());
        order.setMovie_time(tv_movie_time.getText().toString());
        order.setMovie_location(tv_movie_location.getText().toString());
        order.setmMovie_seat(tv_movie_seat.getText().toString());
        order.setOrder_data(tv_order_data.getText().toString());
        order.setOrder_money(tv_all_price.getText().toString());
        order.setIsused(false);
        order.setIspayed(false);
        order.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                   Toast.makeText(OrderInfoActivity.this,"提交成功，订单号为："+objectId,
                           Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(OrderInfoActivity.this,"创建数据失败：" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        Intent intent = this.getIntent();
        ticketItem = (TicketItem)intent.getSerializableExtra("ticket");
        cur_order = intent.getIntegerArrayListExtra("cur_order");
        Log.i("order", cur_order.toString());
    }

    private void initView() {
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderInfoActivity.this.finish();
            }
        });

        tv_movie_name = (TextView) findViewById(R.id.tv_movie_name);
        tv_movie_time = (TextView) findViewById(R.id.tv_movie_time);
        tv_movie_location = (TextView) findViewById(R.id.tv_movie_location);
        tv_movie_seat = (TextView) findViewById(R.id.tv_movie_seat);
        tv_order_data = (TextView) findViewById(R.id.order_data);
        tv_all_price = (TextView) findViewById(R.id.tv_all_price);

        tv_movie_name.setText(ticketItem.getMovie_name());
        tv_movie_time.setText(ticketItem.getMovie_time());
        tv_movie_location.setText(ticketItem.getMovie_location());
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        tv_order_data.setText(year+"-"+month+"-"+day+" "+hour+":"+minute);
        tv_movie_seat.setText(cur_order.toString());
        String price = ticketItem.getMovie_price();
        Log.i("price", price);
        String int_price = price.replace("元","");
        int money = Integer.parseInt(int_price);
        money = cur_order.size()*money;
        tv_all_price.setText(""+money);
        db_order = new DataOrder(this);
        db_writter = db_order.getWritableDatabase();
    }

    private void storeOrder() {
        db_order = new DataOrder(this);
        db_writter = db_order.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataOrder.MOVIE_NAME, tv_movie_name.getText().toString());
        cv.put(DataOrder.MOVIE_TIME, tv_movie_time.getText().toString());
        cv.put(DataOrder.MOVIE_LOCATION, tv_movie_location.getText().toString());
        cv.put(DataOrder.MOVIE_SEAT, tv_movie_seat.getText().toString());
        cv.put(DataOrder.MOVIE_MONEY, tv_all_price.getText().toString());
        db_writter.insert(DataOrder.TABLE_NAME_ORDER, null, cv);

        Toast.makeText(this, "预订成功！", Toast.LENGTH_SHORT).show();
        db_writter.close();
    }
}
