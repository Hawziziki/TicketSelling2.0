package com.me.movieticket.activity;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.bmob.v3.Bmob;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.me.movieticket.R;
import com.me.movieticket.database.DataInit;
import com.me.movieticket.views.TabUserView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ViewPager mTabPager;
    private ImageView mTab1, mTab2, mTab3, mTab4;
    private LayoutInflater inflater;
    private PagerAdapter mPagerAdapter;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initDB();
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initDB() {
        Log.i("读取数据库", "正在读取");
        DataInit dataInit = new DataInit(this);
    }

    private void initView() {
        mTabPager = (ViewPager) findViewById(R.id.tabpager);
        mTab1 = (ImageView) findViewById(R.id.img_movie);
        mTab2 = (ImageView) findViewById(R.id.img_other);
        mTab3 = (ImageView) findViewById(R.id.img_me);
        mTab4 = (ImageView) findViewById(R.id.img_disc);

        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));
        //设置滑动切换底部图标
        mTabPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mTabPager.getCurrentItem();
                resetImg();
                switch (currentItem)
                {
                    case 0:
                        mTab1.setImageResource(R.mipmap.bottom_movieselscted);
                        break;
                    case 1:
                        mTab2.setImageResource(R.mipmap.bottom_otherselected);
                        break;
                    case 2:
                        mTab3.setImageResource(R.mipmap.bottom_discoverselscted);
                        break;
                    case 3:
                        mTab4.setImageResource(R.mipmap.bottom_meselected);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.main_tab_movie, null);
        View view2 = mLi.inflate(R.layout.main_tab_other, null);
        View view3 = mLi.inflate(R.layout.main_tab_me, null);
        View view4 = mLi.inflate(R.layout.main_tab_discover, null);


        //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        //填充ViewPager的数据适配器
        mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object  object) {
                return view == object;
            }

            //摧毁item
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }

            //初始化item
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }
        };
        mTabPager.setAdapter(mPagerAdapter);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {

            resetImg();
            mTabPager.setCurrentItem(index);
            switch (index) {
                case 0:
                    mTab1.setImageResource(R.mipmap.bottom_movieselscted);
                    break;
                case 1:
                    mTab2.setImageResource(R.mipmap.bottom_otherselected);
                    break;
                case 2:
                    mTab3.setImageResource(R.mipmap.bottom_discoverselscted);
                    break;
                case 3:
                    mTab4.setImageResource(R.mipmap.bottom_meselected);
                    break;

            }
        }
    }

    public void resetImg() {
        mTab1.setImageResource(R.mipmap.bottom_movie);
        mTab2.setImageResource(R.mipmap.bottom_other);
        mTab3.setImageResource(R.mipmap.bottom_discover);
        mTab4.setImageResource(R.mipmap.bottom_me);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        //TabUserView.instance.userResume();
//    }
}
