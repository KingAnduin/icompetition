package com.example.thinkpad.icompetition.model.impl;

import android.os.Handler;
import android.util.Log;

import com.example.thinkpad.icompetition.model.entity.collection.CollectionRoot;
import com.example.thinkpad.icompetition.model.entity.collection.IsCollectionRoot;
import com.example.thinkpad.icompetition.model.event.CompetitionInfoEvent;
import com.example.thinkpad.icompetition.model.i.IBaseModel;
import com.example.thinkpad.icompetition.model.i.ICompetitionModel;
import com.example.thinkpad.icompetition.network.CallbackIntercept;
import com.example.thinkpad.icompetition.network.NetworkInterfaces;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;

public class CompetitionModel extends BaseModel implements IBaseModel,ICompetitionModel {
    public CompetitionModel(Handler handler) {
        super(handler);
    }

    @Override
    protected NetworkInterfaces getNetworkInterface() {
        return new NetworkInterfaces();
    }

    @Override
    public void getIsCollection(String com_id) {
        Callback callback = new CallbackIntercept() {
            @Override
            public void onSuccess(Call call, String jsonBody) {
                Gson gson = new Gson();
                IsCollectionRoot root = gson.fromJson(jsonBody, IsCollectionRoot.class);
                if(root != null ){
                    CompetitionInfoEvent event = new CompetitionInfoEvent();
                    event.setIsCollectionRoot(root);
                    event.setWhat(CompetitionInfoEvent.IS_OK);
                    postEvent(event);
                }
            }

            @Override
            public void onFail(Call call, Exception e) {
                CompetitionInfoEvent event = new CompetitionInfoEvent();
                event.setWhat(CompetitionInfoEvent.IS_FAIL);
                postEvent(event);
            }
        };
        mNetworkInterface.getIsCollection(callback, com_id);
    }

    @Override
    public void addCollection(String user_num, String com_id) {
        Callback callback = new CallbackIntercept() {
            @Override
            public void onSuccess(Call call, String jsonBody) {
                Log.d("hjg", "onSuccess: "+jsonBody);
                Gson gson = new Gson();
                CollectionRoot root = gson.fromJson(jsonBody, CollectionRoot.class);
                if(root != null ){
                    CompetitionInfoEvent event = new CompetitionInfoEvent();
                    event.setRoot(root);
                    event.setWhat(CompetitionInfoEvent.ADD_OK);
                    postEvent(event);
                }
            }

            @Override
            public void onFail(Call call, Exception e) {
                CompetitionInfoEvent event = new CompetitionInfoEvent();
                event.setWhat(CompetitionInfoEvent.ADD_FAIL);
                postEvent(event);
            }
        };

        mNetworkInterface.addCollection(callback, user_num, com_id);

    }

    @Override
    public void cancelCollection(String com_id) {
        Callback callback = new CallbackIntercept() {
            @Override
            public void onSuccess(Call call, String jsonBody) {
                Gson gson = new Gson();
                CollectionRoot root = gson.fromJson(jsonBody, CollectionRoot.class);
                if(root != null ){
                    CompetitionInfoEvent event = new CompetitionInfoEvent();
                    event.setRoot(root);
                    event.setWhat(CompetitionInfoEvent.CANCEL_OK);
                    postEvent(event);
                }
            }

            @Override
            public void onFail(Call call, Exception e) {
                CompetitionInfoEvent event = new CompetitionInfoEvent();
                event.setWhat(CompetitionInfoEvent.CANCEL_FAIL);
                postEvent(event);
            }
        };

        mNetworkInterface.cancleCollection(callback, com_id);
    }
}
