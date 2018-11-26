package com.example.thinkpad.icompetition.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.example.thinkpad.icompetition.model.impl.CompetitionModel;
import com.example.thinkpad.icompetition.presenter.i.IBasePresenter;
import com.example.thinkpad.icompetition.presenter.i.ICompetitionPresenter;
import com.example.thinkpad.icompetition.view.activity.impl.CompetitionInforActivity;

public class CompetitionInforPresenter extends BasePresenter<CompetitionInforActivity,CompetitionModel> implements IBasePresenter,ICompetitionPresenter{
    public CompetitionInforPresenter(CompetitionInforActivity view) {
        super(view);
    }

    @Override
    protected CompetitionModel getModel(Handler handler) {
        return new CompetitionModel(handler);
    }

    @Override
    protected void eventReceive(Message msg) {

    }
}
