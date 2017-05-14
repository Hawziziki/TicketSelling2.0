package com.me.movieticket.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.database.DataUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private Button login_reback_btn;
    private EditText login_user_edit;
    private EditText login_passwd_edit;
    private EditText login_repasswd_edit;
    private Button login_register_btn;
    private DataUser dataUser;
    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        login_reback_btn = (Button) findViewById(R.id.login_reback_btn);
        login_user_edit = (EditText) findViewById(R.id.login_user_edit);
        login_passwd_edit = (EditText) findViewById(R.id.login_passwd_edit);
        login_repasswd_edit = (EditText) findViewById(R.id.login_repasswd);
        login_register_btn = (Button) findViewById(R.id.login_register_btn);

        dataUser = new DataUser(this);
        dbReader = dataUser.getReadableDatabase();
        dbWriter = dataUser.getWritableDatabase();

        login_reback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        login_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = login_user_edit.getText().toString();
                String userPass = login_passwd_edit.getText().toString();
                String userrePass = login_repasswd_edit.getText().toString();
                if ("".equals(userrePass)) {
                    Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                }
                if ("".equals(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else if ("".equals(userPass)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    if (userrePass.equals(userrePass)) {
                        registerUser(userName, userPass);
                    }else {
                        login_repasswd_edit.setText("");
                        Toast.makeText(RegisterActivity.this, "两次输入密码不相同",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

//    private void registerUser(String userName, String userPass) {
//        boolean isExit = changeIfUserExit(userName);
//        Log.i("存在：", isExit+"");
//        if (isExit == true) {
//            Toast.makeText(RegisterActivity.this, "账号已经存在", Toast.LENGTH_SHORT).show();
//        } else {
//            ContentValues cv = new ContentValues();
//            cv.put(DataUser.USER_NAME, userName);
//            cv.put(DataUser.USER_PASSWORD, userPass);
//            dbWriter.insert(DataUser.TABLE_NAME_USER, null, cv);
//            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//            RegisterActivity.this.finish();
//        }
//    }
//
//    private boolean changeIfUserExit(String userName) {
//        cursor = dbReader.query(DataUser.TABLE_NAME_USER, null,null,null,
//                null,null,null,null);
//
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToPosition(i);
//            if (cursor.getString(cursor.getColumnIndex(DataUser.USER_NAME)).toString()
//                    .equals(userName)) {
//                return true;
//            }
//        }
//        return false;
//    }

    private void registerUser(String userName, String userPass) {
        Intent intent=getIntent();
        String phone=intent.getStringExtra("phone");
        BmobUser bu = new BmobUser();
        bu.setUsername(userName);
        bu.setPassword(userPass);
        bu.setMobilePhoneNumber(phone);
		bu.setMobilePhoneNumberVerified(true);
        //bu.setEmail("sendi@163.com");
        //注意：不能用save方法进行注册
        bu.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();


                    RegisterActivity.this.finish();
                }else{
                    //loge(e);
                    Toast.makeText(RegisterActivity.this, "该用户名已经被注册", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        dbReader.close();
//        dbWriter.close();
//    }
}
