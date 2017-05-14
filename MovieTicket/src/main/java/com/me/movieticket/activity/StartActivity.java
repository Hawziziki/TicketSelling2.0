package com.me.movieticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.me.movieticket.R;
import com.me.movieticket.database.DataUser;

import cn.bmob.v3.Bmob;

public class StartActivity extends AppCompatActivity {

    private Button main_login_btn;
    private Button main_regist_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Bmob.initialize(this, "41ba4ab6aa7ee996b178942752952c96");
        initView();
    }

    private void initView() {
        main_login_btn = (Button) findViewById(R.id.main_login_btn);
        main_regist_btn = (Button) findViewById(R.id.main_regist_btn);

        main_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LogingAvtivity.class);
                startActivity(intent);
            }
        });
        main_regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, UserBindPhoneActivity.class);
                startActivity(intent);
            }
        });
    }
}
