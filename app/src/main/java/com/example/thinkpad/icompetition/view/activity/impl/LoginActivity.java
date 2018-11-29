package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.event.LoginEvent;
import com.example.thinkpad.icompetition.presenter.impl.LoginPresenter;
import com.example.thinkpad.icompetition.view.activity.i.ILoginActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by a'su's on 2018/7/12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginActivity, View.OnClickListener{

    private EditText mUserNameEt;
    private EditText mUserPassWordEt;
    private Button mLoginBt;
    private RelativeLayout relativeLayout;
    private ImageView mImageView;
    private TextView mRegisterTV;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setListener();
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    private void setListener() {
        mLoginBt.setOnClickListener(this);
        mRegisterTV.setOnClickListener(this);
    }

    private void findView() {
        mUserNameEt = findViewById(R.id.et_username);
        mUserPassWordEt = findViewById(R.id.et_password);
        relativeLayout=findViewById(R.id.mRelativeLayout);
        mLoginBt =findViewById(R.id.btn_login);
        mImageView=findViewById(R.id.bg_login);
        mRegisterTV=findViewById(R.id.tv_register);
        loadBackground();
    }

    //Glide高斯模糊加载背景图
    private void loadBackground() {
        Glide.with(LoginActivity.this)
                .load(R.drawable.loginbackground)
                .dontAnimate()
                .error(R.drawable.loginbackground)
                .centerCrop()
                .crossFade(0)
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(this, 5))
                .into(mImageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                login();
                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                //finish();
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    private void login() {
//        if("".equals(mUserNameEt.getText().toString())){
//            showToast(getResources().getString(R.string.login_user_null));
//        }
//        else if("".equals(mUserPassWordEt.getText().toString())){
//            showToast(getResources().getString(R.string.login_password_null));
//        }else {
//            mLoginBt.setClickable(false);
//            mPresenter.login(mUserNameEt.getText().toString(), mUserPassWordEt.getText().toString());
//        }
        mPresenter.login("15681953321", "123456");
    }

    //登陆请求的回调
    @Override
    public void loginReturn(LoginEvent event) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {
        showToast(getResources().getString(R.string.not_network));
    }
}
