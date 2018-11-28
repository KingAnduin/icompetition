package com.example.thinkpad.icompetition.view.activity.i;

import com.example.thinkpad.icompetition.model.event.RegisterEvent;

public interface IRegisterActivity extends IBaseActivity {
    void registeredReturn(RegisterEvent event);//注册接口的回调
}
