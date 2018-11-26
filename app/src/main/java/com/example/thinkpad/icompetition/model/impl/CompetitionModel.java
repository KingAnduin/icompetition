package com.example.thinkpad.icompetition.model.impl;

import android.os.Handler;

import com.example.thinkpad.icompetition.model.i.IBaseModel;
import com.example.thinkpad.icompetition.model.i.ICompetitionModel;
import com.example.thinkpad.icompetition.network.NetworkInterfaces;

public class CompetitionModel extends BaseModel implements IBaseModel,ICompetitionModel {
    public CompetitionModel(Handler handler) {
        super(handler);
    }

    @Override
    protected NetworkInterfaces getNetworkInterface() {
        return new NetworkInterfaces();
    }
}
