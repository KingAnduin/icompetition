package com.example.thinkpad.icompetition.presenter.impl;

import android.support.annotation.NonNull;

import com.example.thinkpad.icompetition.model.event.HomeInterestEvent;
import com.example.thinkpad.icompetition.model.impl.BaseFragmentModel;
import com.example.thinkpad.icompetition.model.impl.HomeInterstModel;
import com.example.thinkpad.icompetition.view.fragment.i.IHomeInterestFragment;
import com.example.thinkpad.icompetition.view.fragment.impl.HomeInterestFragment;

public class HomeInterestFragmentPrestener extends BaseFragmentPresenter<HomeInterestFragment,HomeInterstModel,HomeInterestEvent> implements IHomeInterestFragment {
    public HomeInterestFragmentPrestener(HomeInterestFragment view) {
        super(view);
    }

    @Override
    protected HomeInterstModel getModel(@NonNull BaseFragmentModel.OnEventReceiveListener<HomeInterestEvent> eventReceiveListener) {
        return new HomeInterstModel(eventReceiveListener);
    }

    @Override
    public void eventReceive(HomeInterestEvent event, int what) {

    }
}
