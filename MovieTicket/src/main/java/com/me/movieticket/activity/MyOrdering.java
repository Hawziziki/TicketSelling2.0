package com.me.movieticket.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.me.movieticket.R;
import com.me.movieticket.adapter.NewOrderAdapter;
import com.me.movieticket.bmobdata.Order;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by MANITO on 2017/3/28.
 */

public class MyOrdering extends AppCompatActivity{
    private PullToRefreshListView lv_list;
    private NewOrderAdapter orderAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myordering);
        lv_list = (PullToRefreshListView) findViewById(R.id.neworder_list);
        //设置下拉刷新
        initData();

        lv_list.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = lv_list.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        //上拉
//        ILoadingLayout endLayout = lv_list.getLoadingLayoutProxy(false,true);
//        endLayout.setPullLabel("正在上拉刷新...");
//        endLayout.setRefreshingLabel("正在玩命加载中...");
//        endLayout.setReleaseLabel("已经到底了");

        lv_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                new MyOrdering.LoadDataAsyncTask(MyOrdering.this).execute();
                lv_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        orderAdapter.notifyDataSetChanged();
                        lv_list.onRefreshComplete();
                    }
                }, 1000);
            }

//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//
//            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //new LoadDataAsyncTask(MyOrderd.this).execute();
            }
        });
        //Log.i("shuju", order_list_data.get(0).getMovie_name());
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
        System.out.println("按下了back键onBackPressed()");
    }

    private void initData() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("ispayed", false);
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        //query.setLimit(50);
        //执行查询方法
        query.order("-createdAt")
                .findObjects(new FindListener<Order>() {

            @Override
            public void done(List<Order> order_list_data, BmobException e) {
                if(e==null){
                    lv_list = (PullToRefreshListView) findViewById(R.id.neworder_list);
                    orderAdapter = new NewOrderAdapter(MyOrdering.this,order_list_data);
                    lv_list.setAdapter(orderAdapter);
                    Toast.makeText(MyOrdering.this,"查询成功：共" + order_list_data.size() + "条数据。",Toast.LENGTH_SHORT);
//                    for (Order order : object) {
//                        //order_list_data.add(order);
//                    }

                }else{
                    Toast.makeText(MyOrdering.this,"查询订单失败",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private static class LoadDataAsyncTask extends AsyncTask<Void,Void,String> {

        private MyOrdering mainActivity;

        public LoadDataAsyncTask(MyOrdering mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
                mainActivity.initData();
                return "seccess";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 完成时的方法
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("seccess")){
//                mainActivity.lv_list.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mainActivity.orderAdapter.notifyDataSetChanged();
//                        mainActivity.lv_list.onRefreshComplete();
//                    }
//                }, 1000);
                mainActivity.orderAdapter.notifyDataSetChanged();
                mainActivity.lv_list.onRefreshComplete();//刷新完成
            }
        }
    }
}
