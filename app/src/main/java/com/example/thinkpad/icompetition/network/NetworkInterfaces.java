package com.example.thinkpad.icompetition.network;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import okhttp3.Callback;

/**
 * Created by Hjg
 * on 2018/11/13.
 */

public class NetworkInterfaces {
    private static final String SERVER_HOST = "http://www.pipicat.top:5000";
    //系统参数，接口名字的字段名,为了减少参数传递,在调用网络接口的方法时将这个字段加入到map中,而不是在处理系统
    // 参数的时候加入
    //private static final String METHOD = "method";

    //以下是请求地址
    private static final String REGISTER = "/api/users/register"; //用户注册
    private static final String LOG_IN = "/api/users/login"; //登陆系统
    private static final String PAGING_QUERY_EXAM = "/api/competitions/bypage"; //分页查询竞赛信息
    private static final String PAGING_QUERY_HOT = "/api/competitions/hot"; //分页查询热门竞赛


    /**
     * 用户注册
     * @param callback 。
     * @param name 用户名
     * @param pwd 密码
     */
    public void userRegister(Callback callback, String name, String pwd){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("user_num", name);
        param.put("user_pwd", pwd);
        new NetworkRequest(param, SERVER_HOST + REGISTER, callback).sendRequest();

    }


    /**
     * 用户登陆
     * @param callback .
     * @param user_num 用户名
     * @param user_pwd 密码
     */
    public void userLogIn(Callback callback, String user_num, String user_pwd){
        //Log.d("hjg", "userLogIn: "+user_num+ "  " +user_pwd);
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("user_num", user_num);
        param.put("user_pwd", user_pwd);
        new NetworkRequest(param, SERVER_HOST + LOG_IN, callback).sendRequest();
    }



    /**
     * 分页查询竞赛信息
     * @param callback .
     * @param page_no 页码
     * @param page_size 每页数量
     */
    public void getItemExam(Callback callback, int page_no, int page_size){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("page", String.valueOf(page_no));
        param.put("pageSize", String.valueOf(page_size));
        new NetworkRequest(param, SERVER_HOST + PAGING_QUERY_EXAM, callback).sendRequest();
    }

    /**
     * 分页查询热门竞赛信息
     * @param callback .
     * @param page_no 页码
     * @param page_size 每页数量
     */
    public void getHotsExam(Callback callback, int page_no, int page_size){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("page", String.valueOf(page_no));
        param.put("pageSize", String.valueOf(page_size));
        new NetworkRequest(param, SERVER_HOST + PAGING_QUERY_HOT, callback).sendRequest();

    }



}
