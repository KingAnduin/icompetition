package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.presenter.impl.BasePresenter;
import com.example.thinkpad.icompetition.presenter.impl.UserInforPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.activity.i.IUserInforActivity;

public class UserInforActivity extends BaseActivity<UserInforPresenter> implements IBaseActivity,IUserInforActivity,View.OnClickListener {
    private TextView mToolbarTitleTV;
    private Toolbar mToolbar;
    private Button mExitBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);
        findView();
        setListener();
    }

    private void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mExitBtn.setOnClickListener(this);
    }

    private void findView() {
        mExitBtn=findViewById(R.id.btn_user_exit);
        mToolbar=findViewById(R.id.toolbar_main);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbarTitleTV = findViewById(R.id.toolbar_title);
        mToolbarTitleTV.setText("个人信息");
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }

    @Override
    protected UserInforPresenter getPresenter() {
        return new UserInforPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_user_exit:
                Intent intent = new Intent(UserInforActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
