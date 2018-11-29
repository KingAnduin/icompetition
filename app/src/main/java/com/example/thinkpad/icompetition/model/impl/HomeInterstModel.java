package com.example.thinkpad.icompetition.model.impl;

import com.example.thinkpad.icompetition.model.event.HomeInterestEvent;
import com.example.thinkpad.icompetition.model.i.IHomeInterestModel;
import com.example.thinkpad.icompetition.network.NetworkInterfaces;

public class HomeInterstModel extends BaseFragmentModel<HomeInterestEvent> implements IHomeInterestModel {
    public HomeInterstModel(OnEventReceiveListener<HomeInterestEvent> eventReceiveListener) {
        super(eventReceiveListener);
    }

    @Override
    protected NetworkInterfaces getNetworkInterface() {
        return new NetworkInterfaces();
    }
}
