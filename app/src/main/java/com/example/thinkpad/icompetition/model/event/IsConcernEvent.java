package com.example.thinkpad.icompetition.model.event;

import com.example.thinkpad.icompetition.model.entity.search.IsConcernRoot;

public class IsConcernEvent extends BaseEvent{
    public static final int GET_ISCONCERN_OK = 105;
    public static final int GET_ISCONCERN_FAIL = -105;
    public static final int ADD_CONCERN_OK = 106;
    public static final int ADD_CONCERN_FAIL = -106;
    public static final int DELETE_CONCERN_OK = 107;
    public static final int DELETE_CONCERN_FAIL = -107;
    private IsConcernRoot root;

    public IsConcernRoot getRoot() {
        return root;
    }

    public void setRoot(IsConcernRoot root) {
        this.root = root;
    }

}
