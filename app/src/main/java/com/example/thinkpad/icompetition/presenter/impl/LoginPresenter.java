package com.example.thinkpad.icompetition.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.example.thinkpad.icompetition.model.event.LoginEvent;
import com.example.thinkpad.icompetition.model.impl.LoginModel;
import com.example.thinkpad.icompetition.presenter.i.ILoginPresenter;
import com.example.thinkpad.icompetition.view.activity.impl.LoginActivity;

/**
 * Created by Hjg on 2018/11/27.
 */
public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel> implements ILoginPresenter {
    public LoginPresenter(LoginActivity view) {
        super(view);
    }

    @Override
    public void login(String name, String pwd) {
        mModel.userLogin(name, pwd);
    }

    @Override
    protected LoginModel getModel(Handler handler) {
        return new LoginModel(handler);
    }

    @Override
    protected void eventReceive(Message msg) {
        switch (msg.what){
            case LoginEvent.LOGIN_OK:
                mView.loginReturn(((LoginEvent) msg.obj));
                break;
            case LoginEvent.LOGIN_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;
        }
    }
}
