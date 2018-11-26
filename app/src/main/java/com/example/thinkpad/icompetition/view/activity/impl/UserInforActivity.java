package com.example.thinkpad.icompetition.view.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.presenter.impl.BasePresenter;
import com.example.thinkpad.icompetition.presenter.impl.UserInforPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IBaseActivity;
import com.example.thinkpad.icompetition.view.activity.i.IUserInforActivity;

public class UserInforActivity extends BaseActivity<UserInforPresenter> implements IBaseActivity,IUserInforActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_infor);
    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {

    }

    @Override
    protected UserInforPresenter getPresenter() {
        return new UserInforPresenter(this);
    }
}
