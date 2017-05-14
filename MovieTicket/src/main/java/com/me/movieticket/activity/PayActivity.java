package com.me.movieticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.me.movieticket.R;
import com.me.movieticket.views.PayDetailFragment;

/**
 * Created by MANITO on 2017/4/28.
 */

public class PayActivity extends AppCompatActivity {
    private Button mButton;
    private ImageButton mImageButton;
    //String money,num;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay);
//        Intent intent=getIntent();
//        Bundle bundle=intent.getExtras();
//        money=bundle.getString("order_price");
//        num=bundle.getString("order_num");
        initView();
    }

    private void initView() {
        mButton= (Button) this.findViewById(R.id.btn_pay);
        mImageButton= (ImageButton) this.findViewById(R.id.ib_return);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayDetailFragment payDetailFragment=new PayDetailFragment();
                payDetailFragment.show(getSupportFragmentManager(),"payDetailFragment");
            }
        });
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayActivity.this.finish();
            }
        });
    }
}
