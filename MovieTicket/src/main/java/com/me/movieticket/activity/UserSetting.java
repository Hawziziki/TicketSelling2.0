package com.me.movieticket.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.bmobdata.Order;
import com.me.movieticket.bmobdata.User;
import com.me.movieticket.views.TabDiscover;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by MANITO on 2017/3/28.
 */

public class UserSetting extends AppCompatActivity {

    private EditText user_edit_name;
    private EditText user_edit_phone;
    private EditText user_edit_sex;
    private EditText user_edit_addr;
    private Button user_edit_save;
    private ImageButton back;
    private String[] sexArry = new String[]{"女孩", "男孩"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);
        initView();
        User user = BmobUser.getCurrentUser(User.class);
        user_edit_name.setText(user.getUsername());
        user_edit_phone.setText(user.getMobilePhoneNumber());
        user_edit_sex.setText(user.getSex());
        user_edit_addr.setText(user.getAddr());
        user_edit_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseSex();
            }
        });
        user_edit_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseAddr();
            }
        });
        user_edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveBmob();
                Intent intent=new Intent();
                intent.setClass(UserSetting.this,PersonCenter.class);
                startActivity(intent);
                TabDiscover discover=new TabDiscover(UserSetting.this);
                discover.initaccount();
                UserSetting.this.finish();
            }
        });

        back = (ImageButton) findViewById(R.id.ib_back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSetting.this.finish();
            }
        });

    }

    private void SaveBmob() {
        String name=user_edit_name.getText().toString();
        String sex=user_edit_sex.getText().toString();
        String addr=user_edit_addr.getText().toString();

        User newUser = new User();
        newUser.setUsername(name);
        newUser.setSex(sex);
        newUser.setAddr(addr);
        BmobUser bmobuser = BmobUser.getCurrentUser();
        newUser.update(bmobuser.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("修改用户信息成功");
                }else{
                    toast("修改用户信息失败:" + e.getMessage());
                }
            }
        });
    }
    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        user_edit_name = (EditText) findViewById(R.id.user_edit_name);
        user_edit_phone = (EditText) findViewById(R.id.user_edit_phone);
        user_edit_sex = (EditText) findViewById(R.id.user_edit_sex);
        user_edit_addr = (EditText) findViewById(R.id.user_edit_addr);
        user_edit_save= (Button) findViewById(R.id.user_edit_save);

    }

    private void ChooseSex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框  
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中  
            @Override

            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置  
                // showToast(which+"");  
                user_edit_sex.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消  
            }
        });
        builder.show();// 让弹出框显示  
    }

    private void ChooseAddr()
    {
        AlertDialog.Builder chooseaddr=new AlertDialog.Builder(this);
        final View viewDia= LayoutInflater.from(this).inflate(R.layout.dialog_choose_addr, null);
        chooseaddr.setTitle("请添加地址");
        chooseaddr.setView(viewDia);
        chooseaddr.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                EditText province=(EditText) viewDia.findViewById(R.id.province);
                EditText city=(EditText) viewDia.findViewById(R.id.city);
                user_edit_addr.setText(province.getText().toString()+"-"+city.getText().toString());
            }
        });
        chooseaddr.create().show();
    }



}
