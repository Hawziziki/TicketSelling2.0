package com.me.movieticket.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.me.movieticket.R;
import com.me.movieticket.adapter.CinameShowAdapter;
import com.me.movieticket.entity.CinemaInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class TabAroundView extends RelativeLayout {
    private List<CinemaInfo> cinema_list=new ArrayList<>();

    public TabAroundView(Context context) {
        super(context);
    }

    public TabAroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabAroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        CinameShowAdapter adapter=new CinameShowAdapter(getContext(), R.layout.list_item_cinema,cinema_list);
        InitCinemaItem();
        ListView listView=(ListView) findViewById(R.id.cinema_list);
        listView.setAdapter(adapter);


    }

    public void InitCinemaItem() {
        CinemaInfo cinema1=new CinemaInfo("中北影院","9.5","太原市尖草坪区中北大学五道门对面");
        cinema_list.add(cinema1);
        CinemaInfo cinema2=new CinemaInfo("长风剧场","9.2","迎泽区柳巷北路铜锣湾对面");
        cinema_list.add(cinema2);
        CinemaInfo cinema3=new CinemaInfo("万达影院","8.5","太原市杏花岭区解放北路175号万达广场娱乐楼4层");
        cinema_list.add(cinema3);
        CinemaInfo cinema4=new CinemaInfo("山西剧院","6.8","太原市迎泽区柳巷北路25号");
        cinema_list.add(cinema4);
        CinemaInfo cinema5=new CinemaInfo("横店影视(王府井店)","7.3","太原市小店区亲贤北街99号阳光王府井购物中心6层");
        cinema_list.add(cinema5);
        CinemaInfo cinema6=new CinemaInfo("太原奥斯卡国际影城","7.3","太原市万柏林区迎泽西大街与千峰南路交汇处能源中心裙楼");
        cinema_list.add(cinema6);
        CinemaInfo cinema7=new CinemaInfo("太原金刚里影城","7.9","太原市金刚堰路柳溪街口金刚里商城4层");
        cinema_list.add(cinema7);
    }
}
