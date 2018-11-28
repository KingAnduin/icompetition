package com.example.thinkpad.icompetition.model.impl;

import android.util.Log;

import com.example.thinkpad.icompetition.model.entity.exam.ExamRecordRoot;
import com.example.thinkpad.icompetition.model.event.HomeHotEvent;
import com.example.thinkpad.icompetition.model.i.IHomeHotFragmentModel;
import com.example.thinkpad.icompetition.network.CallbackIntercept;
import com.example.thinkpad.icompetition.network.NetworkInterfaces;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Hjg on 2018/11/26.
 */
public class HomeHotFragmentModel
        extends BaseFragmentModel<HomeHotEvent> implements IHomeHotFragmentModel {

    public HomeHotFragmentModel(OnEventReceiveListener<HomeHotEvent> eventReceiveListener) {
        super(eventReceiveListener);
    }

    @Override
    public void getItem(int page_no, int page_size) {
        Callback callback = new CallbackIntercept(){
            @Override
            public void onSuccess(Call call, String jsonBody) {
                //Log.d("hjg", "onSuccess: "+jsonBody);
                HomeHotEvent event = new HomeHotEvent();
                Gson gson = new Gson();
                ExamRecordRoot recordRoot = gson.fromJson(jsonBody, ExamRecordRoot.class);
                if(recordRoot!=null && recordRoot.getCode() != 0){
                    event.setWhat(HomeHotEvent.GET_ITEM_OK);
                    event.setRoot(recordRoot);
                }else {
                    event.setWhat(HomeHotEvent.GET_ITEM_FAIL);
                }

                postEvent(event);
            }

            @Override
            public void onFail(Call call, Exception e) {
                HomeHotEvent event = new HomeHotEvent();
                event.setWhat(HomeHotEvent.GET_ITEM_FAIL);
                postEvent(event);
            }
        };
        mNetworkInterface.getItemExam(callback, page_no, page_size);
    }

    @Override
    protected NetworkInterfaces getNetworkInterface() {
        return new NetworkInterfaces();
    }
}
