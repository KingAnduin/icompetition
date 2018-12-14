package com.example.thinkpad.icompetition.model.event;

import com.example.thinkpad.icompetition.model.entity.collection.CollectionRoot;
import com.example.thinkpad.icompetition.model.entity.collection.IsCollectionRoot;

/**
 * Created By hjg on 2018/12/13
 */
public class CompetitionInfoEvent extends BaseEvent{
    public static final int ADD_OK = 101;
    public static final int ADD_FAIL = 102;
    public static final int CANCEL_OK = 201;
    public static final int CANCEL_FAIL = 202;
    public static final int IS_OK = 301;
    public static final int IS_FAIL = 302;
    private String message = "";
    private IsCollectionRoot isCollectionRoot;
    private CollectionRoot root;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IsCollectionRoot getIsCollectionRoot() {
        return isCollectionRoot;
    }

    public void setIsCollectionRoot(IsCollectionRoot isCollectionRoot) {
        this.isCollectionRoot = isCollectionRoot;
    }

    public CollectionRoot getRoot() {
        return root;
    }

    public void setRoot(CollectionRoot root) {
        this.root = root;
    }



}
