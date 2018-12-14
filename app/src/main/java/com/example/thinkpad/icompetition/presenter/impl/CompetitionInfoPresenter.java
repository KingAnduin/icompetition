package com.example.thinkpad.icompetition.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.example.thinkpad.icompetition.model.entity.collection.CollectionRoot;
import com.example.thinkpad.icompetition.model.entity.collection.IsCollectionRoot;
import com.example.thinkpad.icompetition.model.event.CompetitionInfoEvent;
import com.example.thinkpad.icompetition.model.impl.CompetitionModel;
import com.example.thinkpad.icompetition.presenter.i.IBasePresenter;
import com.example.thinkpad.icompetition.presenter.i.ICompetitionPresenter;
import com.example.thinkpad.icompetition.view.activity.impl.CompetitionInfoActivity;

public class CompetitionInfoPresenter
        extends BasePresenter<CompetitionInfoActivity,CompetitionModel>
        implements IBasePresenter,ICompetitionPresenter{
    public CompetitionInfoPresenter(CompetitionInfoActivity view) {
        super(view);
    }

    @Override
    protected CompetitionModel getModel(Handler handler) {
        return new CompetitionModel(handler);
    }

    @Override
    protected void eventReceive(Message msg) {
        switch (msg.what){
            case CompetitionInfoEvent.ADD_OK:
                CollectionRoot root = ((CompetitionInfoEvent)msg.obj).getRoot();
                mView.addCollectionResponse(root);
                break;
            case CompetitionInfoEvent.ADD_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;
            case CompetitionInfoEvent.CANCEL_OK:
                CollectionRoot root1 = ((CompetitionInfoEvent)msg.obj).getRoot();
                mView.cancelCollectionResponse(root1);
                break;
            case CompetitionInfoEvent.CANCEL_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;
            case CompetitionInfoEvent.IS_OK:
                IsCollectionRoot root2 = ((CompetitionInfoEvent)msg.obj).getIsCollectionRoot();
                mView.getIsCollectionResponse(root2);
                break;
            case CompetitionInfoEvent.IS_FAIL:
                mView.failBecauseNotNetworkReturn(msg.what);
                break;
        }
    }

    @Override
    public void getIsCollection(String com_id) {
        mModel.getIsCollection(com_id);
    }

    @Override
    public void addCollection(String user_num, String com_id) {
        mModel.addCollection(user_num, com_id);
    }

    @Override
    public void cancelCollection(String com_id) {
        mModel.cancelCollection(com_id);
    }
}
