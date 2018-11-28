package com.example.thinkpad.icompetition.view.activity.impl;

import com.example.thinkpad.icompetition.R;
import com.example.thinkpad.icompetition.model.event.RegisterEvent;
import com.example.thinkpad.icompetition.presenter.impl.BasePresenter;
import com.example.thinkpad.icompetition.presenter.impl.RegisterPresenter;
import com.example.thinkpad.icompetition.view.activity.i.IRegisterActivity;

/**
 * Create By hjg on 2018/11/27
 * 注册界面
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter>
    implements IRegisterActivity {


    @Override
    protected RegisterPresenter getPresenter() {
        return new RegisterPresenter(this);
    }

    //注册成功的回调
    @Override
    public void registeredReturn(RegisterEvent event) {

    }

    @Override
    public void failBecauseNotNetworkReturn(int code) {
        showToast(getResources().getString(R.string.not_network));
    }
}
