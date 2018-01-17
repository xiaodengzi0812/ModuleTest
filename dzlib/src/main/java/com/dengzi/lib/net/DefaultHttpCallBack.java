package com.dengzi.lib.net;

import java.util.Map;

/**
 * @author Djk
 * @Title:
 * @Time: 2018/1/17.
 * @Version:1.0.0
 */
public class DefaultHttpCallBack implements HttpCallBack {
    @Override
    public boolean onPre(Map<String, Object> mParams) {
        return false;
    }

    @Override
    public void onError(int code, String msg) {

    }

    @Override
    public void onSuccess(String result) {

    }
}
