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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinkpad.icompetition.IcompetitionApplication;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.user.LoginRoot;
import com.example.thinkpad.icompetition.model.entity.user.UserInforRoot;
import com.example.thinkpad.icompetition.presenter.impl.LoginPresenter;
import com.example.thinkpad.icompetition.util.NetWorkHelper;
import com.example.thinkpad.icompetition.view.activity.i.ILoginActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by a'su's on 2018/7/12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginActivity, View.OnClickListener{
    private String mUserNum;
    private String mUserPassword;
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
        if(judgeUserNumAndPassword()) {
            if(NetWorkHelper.isNetworkAvailable(this)) {
                mPresenter.login(mUserNum, mUserPassword);
            }else {
                mLoginBt.setClickable(true);
                showSnackBar(mLoginBt,getString(R.string.not_have_network),getMainColor());
            }
        }
    }

    //判断用户是否输入了账户和密码
    private boolean judgeUserNumAndPassword() {
        mLoginBt.setClickable(false);
        mUserNum = mUserNameEt.getText().toString();
        mUserPassword=mUserPassWordEt.getText().toString();
        if(mUserNum.isEmpty()){
            showSnackBar(mLoginBt,getResources().getString(R.string.login_user_null),getMainColor());
            mLoginBt.setClickable(true);
            return false;
        }
        if(mUserPassword.isEmpty()){
            showSnackBar(mLoginBt,getResources().getString(R.string.login_password_null),getMainColor());
            mLoginBt.setClickable(true);
            return false;
        }
        return true;
    }

    private void getUserInfor(){
        mPresenter.getUserInfor(mUserNum);
    }

    //登陆请求的回调
    @Override
    public void loginReturn(LoginRoot root) {
        if(root.getCode()==200) {
            ((IcompetitionApplication) getApplication()).setToken(root.getData().get(0).get_$Token251());//保存token至sharedpreferences
            getUserInfor();
        }
        if(root.getCode()==1)
        {
            mLoginBt.setClickable(true);
            showSnackBar(mLoginBt,"账户尚未注册",getMainColor());
        }
        if(root.getCode()==2)
        {
            mLoginBt.setClickable(true);
            showSnackBar(mLoginBt,"账户密码错误",getMainColor());
        }
    }

    //请求返回用户数据的回调
    @Override
    public void getUserInforReturn(UserInforRoot root) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        //Log.d("LYactivity", "count"+((IcompetitionApplication) ActivityManager.getActivityManager() );
        finish();
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {
        mLoginBt.setClickable(true);
        showToast(getResources().getString(R.string.not_network));
    }

    public void failBecauseNullPointer() {
        mLoginBt.setClickable(true); //若登陆失败则将登录按钮重构设为可点击
        Toast.makeText(this, "登录过程中出现了一些问题，如果您在使用过程中出现问题的话建议您重新登录", Toast.LENGTH_LONG).show();
    }
}
