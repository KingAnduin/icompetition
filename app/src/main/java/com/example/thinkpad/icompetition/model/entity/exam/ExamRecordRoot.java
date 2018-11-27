package com.example.thinkpad.icompetition.model.entity.exam;

import java.util.List;

/**
 * Created by Hjg on 2018/11/26.
 */
public class ExamRecordRoot {
    private int code;
    private String message;
    private List<ExamRecordItemBean> itemBean;


    public List<ExamRecordItemBean> getItemBean() {
        return itemBean;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
