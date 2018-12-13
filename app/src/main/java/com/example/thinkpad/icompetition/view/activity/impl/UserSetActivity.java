package com.example.thinkpad.icompetition.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.thinkpad.icompetition.IcompetitionApplication;
import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.entity.user.UserInforBean;
import com.example.thinkpad.icompetition.presenter.impl.UserSetPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;

import java.util.List;

import greendao.gen.DaoSession;
import greendao.gen.UserInforBeanDao;

/**
 * created by a’su's
 */
public class UserSetActivity extends BaseActivity<UserSetPresenter> implements View.OnClickListener, IBaseActivity {
    private TextView mRoleIdTV;
    private TextView mUserNumTV;
    private Toolbar mToolbar;
    private TextView mToolTitleTV;
    private Button mExitBtn;
    private DaoSession mDaoSession;
    private UserInforBean mUserBean;
    private ImageView mChangeScreat;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordinary_set);
        findView();
        setListener();
        getInformationFromDao();
    }

    @Override
    protected UserSetPresenter getPresenter() {
        return new UserSetPresenter(this);
    }

    private void getInformationFromDao() {
        mDaoSession=((IcompetitionApplication)getApplication()).getDaoSession();
        UserInforBeanDao userInforBeanDao = mDaoSession.getUserInforBeanDao();
        List<UserInforBean> list = userInforBeanDao.loadAll();
        if(list.get(0)!=null) {
            mUserBean = list.get(0);
        }
        if(!TextUtils.isEmpty(String.valueOf(mUserBean.getUser_roleid())))
        mRoleIdTV.setText(String.valueOf(mUserBean.getUser_roleid()));
        if(!TextUtils.isEmpty(String.valueOf(mUserBean.getUser_num())))
        mUserNumTV.setText(String.valueOf(mUserBean.getUser_num()));
    }

    private void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mExitBtn.setOnClickListener(this);
        mChangeScreat.setOnClickListener(this);
    }

    private void findView() {
        mChangeScreat = findViewById(R.id.iv_change_screat);
        mRoleIdTV = findViewById(R.id.tv_role_id);
        mUserNumTV=findViewById(R.id.tv_user_num);
        mExitBtn=findViewById(R.id.btn_set_exit);
        mToolbar=findViewById(R.id.toolbar_main);
        mToolTitleTV=findViewById(R.id.toolbar_title);
        mToolTitleTV.setText("通用设置");
        mToolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btn_set_exit:
                intent = new Intent(UserSetActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.iv_change_screat:
                intent = new Intent(UserSetActivity.this,ChangePasswordActivity.class);
                intent.putExtra("user_num",mUserNumTV.getText().toString());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }
}
