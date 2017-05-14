package com.me.movieticket.views;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.me.movieticket.R;
import com.me.movieticket.adapter.DiscoverListAdapter;
import com.me.movieticket.adapter.OrderAdapter;
import com.me.movieticket.database.DataOperate;
import com.me.movieticket.database.DataOrder;
import com.me.movieticket.entity.DiscoverItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class TabUserView extends RelativeLayout {
    private List<DiscoverItem> discover_list=new ArrayList<>();

    public TabUserView(Context context) {
        super(context);
    }

    public TabUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabUserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        DiscoverListAdapter adapter=new DiscoverListAdapter(getContext(), R.layout.list_item_discover,discover_list);
        InitDiscoverItem();
        ListView listView=(ListView) findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

    }

    public void InitDiscoverItem() {
        DiscoverItem cinema1=new DiscoverItem("immorkal","《X战警：天启》","9.0","标准好莱坞爆米花商业电影，大场面，duangduang满天飞的特效，然而除此之外就神马也没有了。三傻的凤凰蛮失败，距离原版的法米克詹森差的不是一点半点。快银片段不如上部来的好，被消费过度。金刚狼打了个酱油，与三傻的对戏尴尬满满。大反派白内障患者天启，完全是被自己给作死的，智商感人。 ");
        discover_list.add(cinema1);
        DiscoverItem cinema2=new DiscoverItem("芒果天天","《魔兽》","6.5","平庸的表演 灾难般的分镜设计 如果不是大段特效和借游戏名头得来的人气，实在是不值得去电影院。。。。。。高精和矮人只有几个镜头，原版的bgm一首都没有，全片只有苍白的人类演员的戏份，唯一能看得cg镜头被垃圾的分镜设计遮盖下去。整体还不如暴雪自己拍的那些cg片段");
        discover_list.add(cinema2);
        DiscoverItem cinema3=new DiscoverItem("你的益达","《北京遇上西雅图》","8.5","《北京遇上西雅图》，没有“拜金女”，没有“软饭男”。在这里，“小三”只是一种身份，“落魄”只是一种处境。 文佳佳初遇Frank，傲慢，任性，但却孤独。她要买最漂亮的衣服，要住最好的房间，会和其他孕妇争吵。但她也要独自面对陌生的生活，照顾腹中的孩子，等待远方的老钟... ");
        discover_list.add(cinema3);
        DiscoverItem cinema4=new DiscoverItem("心中阳光","《愤怒的小鸟》","7.0","两大船绿色小猪随身带着TNT炸药远道而来，蒙蔽了热情接待的东道主却被不知政治正确为何物的当地小鸟逼问到底是来探险的还是来定居的，然后果不其然地反客为主、恩将仇报，在身后留下废墟一片。慢点……这怎么看都不像是单纯的动画片啊…… \n");
        discover_list.add(cinema4);
        DiscoverItem cinema5=new DiscoverItem("KOBEBRANY","《大唐玄奘》","5.8","霍建起的问题仍然是在追求影像美感的同时，无法让自己的故事更富魅力，全片松散无趣。黄晓明的选择无疑是最大败笔，他完全无法演绎这样的角色，他的温柔与迷茫像个随时都要跑路的家伙，很难相信他会一人冒死西行十数年。而且，连印度语都是配音，口型都懒得对，相当应付，让人失望。");
        discover_list.add(cinema5);
    }
}
