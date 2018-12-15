package com.example.thinkpad.icompetition.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.example.thinkpad.icompetition.model.entity.search.IsConcernRoot;
import com.example.thinkpad.icompetition.model.event.IsConcernEvent;
import com.example.thinkpad.icompetition.model.impl.UserBySearchModel;
import com.example.thinkpad.icompetition.presenter.i.IUserBySearchPresenter;
import com.example.thinkpad.icompetition.view.activity.impl.UserBySearchActivity;

public class UserBySearchPresenter extends BasePresenter<UserBySearchActivity,UserBySearchModel> implements IUserBySearchPresenter {
    public UserBySearchPresenter(UserBySearchActivity view) {
        super(view);
    }

    @Override
    protected UserBySearchModel getModel(Handler handler) {
        return new UserBySearchModel(handler);
    }

    @Override
    protected void eventReceive(Message msg) {
        switch (msg.what){
            case IsConcernEvent.GET_ISCONCERN_OK:
                mView.getConcernReturn(((IsConcernEvent)msg.obj).getRoot());
                break;
            case IsConcernEvent.GET_ISCONCERN_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;
            case IsConcernEvent.ADD_CONCERN_OK:
                mView.getAddConcernReturn(((IsConcernEvent)msg.obj).getRoot());
                break;
            case IsConcernEvent.ADD_CONCERN_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;

            case IsConcernEvent.DELETE_CONCERN_OK:
                mView.getDeleteConcernReturn(((IsConcernEvent)msg.obj).getRoot());
                break;
            case IsConcernEvent.DELETE_CONCERN_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;
        }
    }

    @Override
    public void getIsConcern(String other_num) {
        mModel.getIsConcern(other_num);
    }

    @Override
    public void addConcern(String other_num) {
        mModel.addConcern(other_num);
    }

    @Override
    public void deleteConcern(String other_num) {
        mModel.deleteConcern(other_num);
    }
}
