package com.example.thinkpad.icompetition.view.activity.i;

import com.example.thinkpad.icompetition.model.event.LoginEvent;

/**
 * Created by Hjg on 2018/11/27.
 */
public interface ILoginActivity extends IBaseActivity{
    void loginReturn(LoginEvent event);    //登陆请求的回调
}
