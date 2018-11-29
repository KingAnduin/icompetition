package com.example.thinkpad.icompetition.presenter.impl;

import android.support.annotation.NonNull;

import com.example.thinkpad.icompetition.model.event.HomeHotEvent;
import com.example.thinkpad.icompetition.model.impl.BaseFragmentModel;
import com.example.thinkpad.icompetition.model.impl.HomeHotFragmentModel;
import com.example.thinkpad.icompetition.presenter.i.IHomeHotFragmentPresent;
import com.example.thinkpad.icompetition.view.fragment.impl.HomeHotFragment;

/**
 * Created by Hjg on 2018/11/26.
 */
public class HomeHotFragmentPresenter extends BaseFragmentPresenter<HomeHotFragment, HomeHotFragmentModel, HomeHotEvent> implements IHomeHotFragmentPresent {
    public HomeHotFragmentPresenter(HomeHotFragment view) {
        super(view);
    }

    @Override
    public void getExamInfo(int page_no, int page_size) {
        mModel.getItem(page_no, page_size);
    }

    @Override
    protected HomeHotFragmentModel getModel(@NonNull BaseFragmentModel.OnEventReceiveListener<HomeHotEvent> eventReceiveListener) {
        return new HomeHotFragmentModel(eventReceiveListener);
    }


    @Override
    public void eventReceive(HomeHotEvent event, int what) {
        if(event!=null){
            switch (what){
                case HomeHotEvent.GET_ITEM_OK:
                    mView.PagingQueryHomeHotResponse(event.getRoot());
                    break;
                case HomeHotEvent.GET_ITEM_FAIL:
                    mView.failBecauseNotNetworkReturn(what);
                    break;
//                    if(event.getRoot()!=null)
//                    {
//                        if(!TextUtils.isEmpty(event.getRoot().getMsg())){
//                            mView.showErrorMsg(event.getRoot());
//                        }
//                    }
            }
        }
    }
}
