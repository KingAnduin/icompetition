package com.example.thinkpad.icompetition.network;

import android.support.v4.util.ArrayMap;
import android.telephony.SignalStrength;
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
    private static final String USER_INFOR = "/api/users/message"; //用户信息数据
    private static final String PAGING_QUERY_EXAM = "/api/competitions/bypage"; //分页查询竞赛信息
    private static final String PAGING_QUERY_HOT = "/api/competitions/hot"; //分页查询热门竞赛
    private static final String SUBMIT_USERINFOR = "/api/users/update"; //提交用户信息
    private static final String CHANGE_PASSWORD = "/api/users/updatepwd"; //修改密码
    private static final String PAGING_QUERY_INTEREST = "api/competitions/type";//分页查询分类的竞赛
    private static final String COLLECTION_BY_ID = "/api/collections/byid"; //获取是否收藏（单个）
    private static final String COLLECTION_ADD = "/api/collections/add"; //添加收藏
    private static final String COLLECTION_CANCLE = "/api/collections/delete "; //删除收藏


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
     * 用户密码修改
     * @param callback .
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public void changePassword(Callback callback,String oldPassword,String newPassword ){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("user_pwd", oldPassword);
        param.put("new_pwd",newPassword);
        new NetworkRequest(param,SERVER_HOST + CHANGE_PASSWORD,callback).sendRequest();
    }

    /**
     * 用户信息
     * @param callback 。
     * @param num 用户名
     */
    public void userInfor(Callback callback, String num) {
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("user_num", num);
        new NetworkRequest(param, SERVER_HOST + USER_INFOR, callback).sendRequest();
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

    /**
     * 带头像提交个人用户信息
     * @param callback .
     * @param userName 用户姓名
     * @param userSex 用户性别
     * @param userBirthday 用户生日
     * @param interest 用户兴趣
     * @param headImage 用户头像
     */
    public void submitUserInfor(Callback callback,String userName,String userSex,String userBirthday,String interest,String headImage){
        ArrayMap<String,String> param = new ArrayMap<>();
        param.put("user_name",userName);
        param.put("user_sex",userSex);
        param.put("user_birthday",userBirthday);
        param.put("user_interest",interest);
        param.put("user_headimage",headImage);
        new NetworkRequest(param,SERVER_HOST + SUBMIT_USERINFOR,callback).sendRequest();
    }

    /**
     * 应后台要求添加
     * 不带头像提交个人用户信息
     * @param callback .
     * @param userName 用户姓名
     * @param userSex 用户性别
     * @param userBirthday 用户生日
     * @param interest 用户兴趣
     */
    public void submitUserInforWithoutHeadImage(Callback callback,String userName,String userSex,String userBirthday,String interest){
        ArrayMap<String,String> param = new ArrayMap<>();
        param.put("user_name",userName);
        param.put("user_sex",userSex);
        param.put("user_birthday",userBirthday);
        param.put("user_interest",interest);
        new NetworkRequest(param,SERVER_HOST + SUBMIT_USERINFOR,callback).sendRequest();
    }

    /**
     * 分页查询兴趣的竞赛信息
     * @param callback .
     * @param page_no 页码
     * @param page_size 每页数量
     * @param type 类型
     */
    public void getTypeExam(Callback callback, int page_no, int page_size, String type){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("page", String.valueOf(page_no));
        param.put("pageSize", String.valueOf(page_size));
        param.put("type", type);
        new NetworkRequest(param, SERVER_HOST + PAGING_QUERY_INTEREST, callback).sendRequest();
    }

    /**
     * 删除收藏
     * @param callback .
     * @param comid 比赛id
     */
    public void cancleCollection(Callback callback, String comid){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("comid", comid);
        new NetworkRequest(param, SERVER_HOST + COLLECTION_CANCLE, callback).sendRequest();
    }


    /**
     * 添加收藏
     * @param callback .
     * @param usernum 用户账号
     * @param comid 比赛id
     */
    public void addCollection(Callback callback, String usernum, String comid){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("usernum", usernum);
        param.put("comid", comid);
        new NetworkRequest(param, SERVER_HOST + COLLECTION_ADD, callback).sendRequest();
    }


    /**
     * 获取是否收藏（单个）
     * @param callback .
     * @param comid 比赛id
     */
    public void getIsCollection(Callback callback,String comid){
        ArrayMap<String, String> param = new ArrayMap<>();
        param.put("comid", comid);
        new NetworkRequest(param, SERVER_HOST + COLLECTION_BY_ID, callback).sendRequest();
    }


}
