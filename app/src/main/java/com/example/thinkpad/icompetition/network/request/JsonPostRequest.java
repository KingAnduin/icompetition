package com.example.thinkpad.icompetition.network.request;

import android.support.v4.util.ArrayMap;
import android.util.Base64;

import com.example.thinkpad.icompetition.IcompetitionApplication;
import com.example.thinkpad.icompetition.view.activity.ActivityManager;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.util.Base64.encodeToString;

/**
 * post方式的json请求
 *
 * @see #getRequest() 返回一个Request实例,用于OkHttpClient发送
 * *
 * Created by z
 * on 2017/3/11 0011.
 */

public class JsonPostRequest {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String mUrl;                                //请求地址
    private ArrayMap<String, String> mRequestParam;     //请求参数

    public JsonPostRequest(String url, ArrayMap<String, String> requestParam) {
        this.mUrl = url;
        this.mRequestParam = requestParam;
    }

    /**
     * 创建一个请求
     * 在这里添加Base64加密后的token
     * 可以给请求添加tag,便于取消含有该tag的请求
     *
     * @return request
     */
    public Request getRequest() {
        //将map转换为json字串
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(mRequestParam));
        return new Request.Builder().url(mUrl).addHeader("Authorization","Basic "+getBase64Token(((IcompetitionApplication) ActivityManager.getActivityManager().peekActivity().getApplication()).getToken())).post(body).tag("tag").build();
       // return new Request.Builder().url(mUrl).addHeader("Authorization","Basic ZXlKaGJHY2lPaUpJVXpJMU5pSXNJbWxoZENJNk1UVTBNelEzTmprMk15d2laWGh3SWpveE5UUXpORGczTURRemZRLmV5SjFjMlZ5WDI1MWJTSTZNVFUyT0RFNU5UTXpNakY5LlVqT3ZwMHNMOEtlc252dXpyS2d0NzZ3eWJWUy1uSV9MdFVwQ2lnSnl2c006").post(body).tag("tag").build();
    }

    /**
     * 使用Base64加密token
     * @return request
     */
    private String getBase64Token(String token){
        return encodeToString(token.getBytes(), Base64.NO_WRAP);
    }
}
