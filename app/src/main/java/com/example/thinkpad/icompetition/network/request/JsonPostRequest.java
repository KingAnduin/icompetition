package com.example.thinkpad.icompetition.network.request;

import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

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
     * 可以给请求添加tag,便于取消含有该tag的请求
     *
     * @return request
     */
    public Request getRequest() {
        //将map转换为json字串
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(mRequestParam));
        return new Request.Builder().url(mUrl).post(body).tag("tag").build();
    }

}
