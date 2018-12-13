package com.example.thinkpad.icompetition.model.impl;

import android.os.Handler;

import com.example.thinkpad.icompetition.model.i.ISearchModel;
import com.example.thinkpad.icompetition.network.NetworkInterfaces;

public class SearchModel extends BaseModel implements ISearchModel {
    public SearchModel(Handler handler) {
        super(handler);
    }

    @Override
    protected NetworkInterfaces getNetworkInterface() {
        return new NetworkInterfaces();
    }
}
