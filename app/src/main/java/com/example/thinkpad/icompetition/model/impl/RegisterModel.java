package com.example.thinkpad.icompetition.model.impl;

import android.os.Handler;

import com.example.thinkpad.icompetition.model.i.IRegisterModel;
import com.example.thinkpad.icompetition.network.CallbackIntercept;
import com.example.thinkpad.icompetition.network.NetworkInterfaces;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Create By hjg on 2018/11/27
 */
public class RegisterModel extends BaseModel implements IRegisterModel {

    public RegisterModel(Handler handler) {
        super(handler);
    }

    //用户注册
    @Override
    public void userRegister(String name, String pwd) {
        Callback callback = new CallbackIntercept() {
            @Override
            public void onSuccess(Call call, String jsonBody) {

            }

            @Override
            public void onFail(Call call, Exception e) {

            }
        };
        mNetworkInterface.userRegister(callback, name, pwd);
    }

    @Override
    protected NetworkInterfaces getNetworkInterface() {
        return new NetworkInterfaces();
    }
}
