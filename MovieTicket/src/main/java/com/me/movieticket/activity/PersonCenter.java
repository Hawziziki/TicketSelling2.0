package com.me.movieticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.me.movieticket.R;
import com.me.movieticket.bmobdata.User;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by MANITO on 2017/3/12.
 */

public class PersonCenter extends Activity {
    EditText user_name;
    EditText user_phone;
    EditText user_sex;
    EditText user_addr;
    Button user_edit;
    Button user_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personcenter);
        initView();
        User user= BmobUser.getCurrentUser(User.class);
        user_name.setText(user.getUsername());
        user_phone.setText(user.getMobilePhoneNumber());
        user_sex.setText(user.getSex());
        user_addr.setText(user.getAddr());
        user_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonCenter.this,UserSetting.class);
                startActivity(intent);
                PersonCenter.this.finish();
            }
        });

        user_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonCenter.this,StartActivity.class);
                startActivity(intent);
                PersonCenter.this.finish();
            }
        });


    }

    private void initView() {
        user_name= (EditText) findViewById(R.id.user_name);
        user_phone= (EditText) findViewById(R.id.user_phone);
        user_sex= (EditText) findViewById(R.id.user_sex);
        user_addr= (EditText) findViewById(R.id.user_addr);
        user_edit= (Button) findViewById(R.id.user_edit);
        user_back= (Button) findViewById(R.id.user_back);
    }
}