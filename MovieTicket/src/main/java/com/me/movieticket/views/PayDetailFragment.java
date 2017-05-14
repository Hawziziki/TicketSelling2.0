package com.me.movieticket.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.activity.AllOrder;
import com.me.movieticket.bmobdata.Order;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


/**
 * 底部弹窗Fragment
 */
public class PayDetailFragment extends DialogFragment {
    private RelativeLayout rePayWay, rePayDetail, reBalance;
    private LinearLayout LinPayWay,linPass;
    private ListView lv;
    private Button btnPay;
    private EditText gridPasswordView;
    private TextView pay_price,pay_need;
    private ImageView imageCloseOne,imageCloseTwo,imageCloseThree;
    private String money,id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_pay_detail);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
        window.setAttributes(lp);
        SharedPreferences pref = getContext().getSharedPreferences("test",Activity.MODE_PRIVATE);
        money = pref.getString("order_price","");//第二个参数为默认值
        Log.i("共享数据成功，money",money);
        id=pref.getString("order_num","");
        Log.i("共享数据成功，订单号",id);

        initView(dialog);




//        if (getDialog() != null) {
//            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
//                @Override
//                public boolean onKey(DialogInterface anInterface, int keyCode, KeyEvent event) {
//                    if(keyCode== KeyEvent.KEYCODE_ENTER&&event.getAction()== KeyEvent.ACTION_UP){
//                        if(!TextUtils.isEmpty(gridPasswordView.getText().toString().trim())){
//                            if("123456".equals(gridPasswordView.getText().toString().trim())){
//                                //TODO 跳转支付宝支付
//                                Toast.makeText(getContext(),"密码", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }else{
//                        Toast.makeText(getContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                    return false;
//                }
//            });
//        }

        gridPasswordView.addTextChangedListener(new TextWatcher() {
            CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp=s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(temp.length()==6){
                    //if(temp.equals("123456")) {
                        Toast.makeText(getContext(), "密码正确", Toast.LENGTH_LONG).show();
                        Order order_update = new Order();
                        order_update.setIspayed(true);
                        order_update.update(id, new UpdateListener() {

                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Log.i("bmob", "更新成功：" );
                                    Toast.makeText(getContext(), "订单"+id+"支付成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                                }
                            }
                        });
                        Intent intent = new Intent();
                        intent.setClass(getContext(), AllOrder.class);
                        startActivity(intent);
                        PayDetailFragment.this.dismiss();
                    //}else {
//                        Toast.makeText(getContext(), "密码错误", Toast.LENGTH_LONG).show();
//                        gridPasswordView.setText("");
//                    }
                }

            }
        });



        return dialog;
    }

    private void initView(Dialog dialog) {
        rePayWay = (RelativeLayout) dialog.findViewById(R.id.re_pay_way);
        rePayDetail = (RelativeLayout) dialog.findViewById(R.id.re_pay_detail);//付款详情
        LinPayWay = (LinearLayout) dialog.findViewById(R.id.lin_pay_way);//付款方式
        lv = (ListView) dialog.findViewById(R.id.lv_bank);//付款方式（银行卡列表）
        reBalance = (RelativeLayout) dialog.findViewById(R.id.re_balance);//付款方式（余额）
        btnPay = (Button) dialog.findViewById(R.id.btn_confirm_pay);
        gridPasswordView = (EditText) dialog.findViewById(R.id.pass_view);
        pay_need= (TextView) dialog.findViewById(R.id.pay_order_price);
        pay_price= (TextView) dialog.findViewById(R.id.pay_need_payed);
        pay_price.setText(money);
        pay_need.setText(money);
        linPass = (LinearLayout)dialog.findViewById(R.id.lin_pass);
        imageCloseOne= (ImageView) dialog.findViewById(R.id.close_one);
        imageCloseTwo= (ImageView) dialog.findViewById(R.id.close_two);
        imageCloseThree= (ImageView) dialog.findViewById(R.id.close_three);
        rePayWay.setOnClickListener(listener);
        reBalance.setOnClickListener(listener);
        btnPay.setOnClickListener(listener);
        imageCloseOne.setOnClickListener(listener);
        imageCloseTwo.setOnClickListener(listener);
        imageCloseThree.setOnClickListener(listener);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Animation slide_left_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left);
            Animation slide_right_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_to_left);
            Animation slide_left_to_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_right);
            Animation slide_left_to_left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left_in);
            switch (view.getId()) {
                case R.id.re_pay_way://选择方式
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    LinPayWay.startAnimation(slide_right_to_left);
                    LinPayWay.setVisibility(View.VISIBLE);
                    break;
                case R.id.re_balance:
                    rePayDetail.startAnimation(slide_left_to_left_in);
                    rePayDetail.setVisibility(View.VISIBLE);
                    LinPayWay.startAnimation(slide_left_to_right);
                    LinPayWay.setVisibility(View.GONE);
                    break;
                case R.id.btn_confirm_pay://确认付款
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    linPass.startAnimation(slide_right_to_left);
                    linPass.setVisibility(View.VISIBLE);
                    break;
                case R.id.close_one:
                    getDialog().dismiss();
                    break;
                case R.id.close_two:
                    getDialog().dismiss();
                    break;
                case R.id.close_three:
                    getDialog().dismiss();
                    break;
                default:
                    break;
            }
        }
    };
}
