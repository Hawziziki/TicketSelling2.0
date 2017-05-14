package com.me.movieticket.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.me.movieticket.R;

import com.me.movieticket.activity.AllOrder;
import com.me.movieticket.activity.MyOrder;
import com.me.movieticket.activity.MyOrderd;
import com.me.movieticket.activity.MyOrdering;
import com.me.movieticket.activity.PersonCenter;
import com.me.movieticket.activity.UserSetting;
import com.me.movieticket.adapter.UserCenterAdapter;
import com.me.movieticket.entity.UserItems;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;


/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class TabDiscover extends RelativeLayout {
    private List<UserItems> userItemsList=new ArrayList<>();
    private Button userset;
    public static TabDiscover instance;
    Context mcontext=(Activity)getContext();

    public TabDiscover(Context context) {
        super(context);
        //this.mcontext=context;
    }

    public TabDiscover(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public TabDiscover(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        instance=this;
        final Intent intent=new Intent();
        userset=(Button) findViewById(R.id.userset);
        userset.setText(initaccount());
        userset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(mcontext, PersonCenter.class);
                mcontext.startActivity(intent);
            }
        });
        UserCenterAdapter adapter=new UserCenterAdapter(getContext(), R.layout.usercenter,userItemsList);
        initUserItem();
        ListView listview=(ListView) findViewById(R.id.me_list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        intent.setClass(mcontext, AllOrder.class);
                        break;
                    case 1:
                        intent.setClass(mcontext, MyOrderd.class);
                        break;
                    case 2:
                        intent.setClass(mcontext, MyOrdering.class);
                        break;
                }
                mcontext.startActivity(intent);
            }
        });
    }

    private void initUserItem() {
        UserItems userorder=new UserItems("我的订单",0);
        userItemsList.add(userorder);
        UserItems userorderd=new UserItems("待使用订单",0);
        userItemsList.add(userorderd);
        UserItems userordering=new UserItems("待支付订单",0);
        userItemsList.add(userordering);
    }

    public String initaccount(){
        BmobUser bu3=BmobUser.getCurrentUser();
        String name=bu3.getUsername();
        return name;
    }
}
