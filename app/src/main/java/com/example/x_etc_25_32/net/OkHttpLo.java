package com.example.x_etc_25_32.net;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 20:32
 */
public interface OkHttpLo {
    void onResponse(JSONObject jsonObject);

    void onFailure(IOException obj);
}
