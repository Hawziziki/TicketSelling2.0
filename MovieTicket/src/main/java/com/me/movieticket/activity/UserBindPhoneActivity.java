package com.me.movieticket.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.me.movieticket.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**  
 * 绑定手机号  
 * @class  UserBindPhoneActivity  
 * @author smile   
 * @date   2015-6-5 下午3:08:53  
 *   
 */
public class UserBindPhoneActivity extends BaseActivity {
	
	@InjectView(R.id.iv_left)
    ImageView iv_left;
	@InjectView(R.id.et_number)
    EditText et_number;
	@InjectView(R.id.et_input)
    EditText et_input;
	
	@InjectView(R.id.tv_title)
    TextView tv_title;
	
	@InjectView(R.id.tv_send)
    TextView tv_send;
	
	@InjectView(R.id.tv_bind)
    TextView tv_bind;
	
	MyCountTimer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind);
		ButterKnife.inject(this);
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("绑定手机号");
	}
	
	class MyCountTimer extends CountDownTimer {
		  
        public MyCountTimer(long millisInFuture, long countDownInterval) {  
            super(millisInFuture, countDownInterval);  
        }  
		@Override
        public void onTick(long millisUntilFinished) {  
			tv_send.setText((millisUntilFinished / 1000) +"秒后重发");  
        }  
        @Override
        public void onFinish() {  
        	tv_send.setText("重新发送验证码");  
        }  
    }  
	

	@OnClick(R.id.iv_left)
	public void back() {
		finish();
	}
	
	@OnClick(R.id.tv_send)
	public void send() {
		requestSMSCode();
	}
	
	@OnClick(R.id.tv_bind)
	public void bind() {
		verifyOrBind();
	}
		
	private void requestSMSCode() {
		String number = et_number.getText().toString();
		if (!TextUtils.isEmpty(number)) {
			timer = new MyCountTimer(60000, 1000);   
			timer.start();
			BmobSMS.requestSMSCode(number, "中北影院短信验证",new QueryListener<Integer>() {
				public void done(Integer integer, BmobException e) {

					// TODO Auto-generated method stub
					if (e == null) {// 验证码发送成功
						toast("验证码发送成功");// 用于查询本次短信发送详情
					}else{//如果验证码发送错误，可停止计时
						timer.cancel();
					}
				}
			});
		} else {
			toast("请输入手机号码");
		}
	}
	
	private void verifyOrBind(){
		final String phone = et_number.getText().toString();
		String code = et_input.getText().toString();
		if (TextUtils.isEmpty(phone)) {
			showToast("手机号码不能为空");
			return;
		}

		if (TextUtils.isEmpty(code)) {
			showToast("验证码不能为空");
			return;
		}
		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("正在验证短信验证码...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		// V3.3.9提供的一键注册或登录方式，可传手机号码和验证码
		BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
			
			@Override
			public void done(BmobException ex) {
				// TODO Auto-generated method stub
				progress.dismiss();
				if(ex==null){
					toast("验证通过");
					bindMobilePhone(phone);
				}else{
					toast("验证失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage());
				}
			}
		});
	}
	
	private void bindMobilePhone(String phone){
		//开发者在给用户绑定手机号码的时候需要提交两个字段的值：mobilePhoneNumber、mobilePhoneNumberVerified
//		BmobUser user =new BmobUser();
//		user.setMobilePhoneNumber(phone);
//		user.setMobilePhoneNumberVerified(true);
//		user.update(new UpdateListener() {
//
//			@Override
//			public void done(BmobException e) {
//				if(e==null){
//					Toast.makeText(UserBindPhoneActivity.this, "手机号码绑定成功", Toast.LENGTH_SHORT).show();
//					Intent intent=new Intent();
//					intent.setClass(UserBindPhoneActivity.this,RegisterActivity.class);
//					startActivity(intent);
//					UserBindPhoneActivity.this.finish();
//
//				}else{
//					//loge(e);
//					Toast.makeText(UserBindPhoneActivity.this, "手机号码绑定失败", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
		Intent intent=new Intent();
		intent.putExtra("phone",phone);
		intent.setClass(UserBindPhoneActivity.this,RegisterActivity.class);
		startActivity(intent);
		UserBindPhoneActivity.this.finish();

	}
	
}
