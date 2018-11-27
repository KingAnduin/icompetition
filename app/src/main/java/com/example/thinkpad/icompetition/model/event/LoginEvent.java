package com.example.thinkpad.icompetition.model.event;

import com.example.thinkpad.icompetition.model.entity.user.LoginRoot;

/**
 * Created by Hjg on 2018/11/27.
 */
public class LoginEvent extends BaseEvent {
    public static final int LOGIN_OK = 100;
    public static final int LOGIN_FAIL = 0;
    private String message = "";
    private LoginRoot root;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginRoot getRoot() {
        return root;
    }

    public void setRoot(LoginRoot root) {
        this.root = root;
    }
}
