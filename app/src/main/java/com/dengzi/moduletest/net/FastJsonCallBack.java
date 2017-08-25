package com.dengzi.moduletest.net;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.dengzi.lib.net.HttpCallBack;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author Djk
 * @Title: fastjson解析业务并返回业务bean
 * @Time: 2017/8/22.
 * @Version:1.0.0
 */
public abstract class FastJsonCallBack<T extends BaseBean> implements HttpCallBack {
    public final Class<T> mClazz;
    public Handler deliver;

    public FastJsonCallBack() {
        deliver = new Handler();
        mClazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public final HttpCallBack getDefaultCallBack() {
        return new HttpCallBack() {
            @Override
            public void onPre(Map<String, Object> mParams) {
                FastJsonCallBack.this.onPre(mParams);
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public HttpCallBack getDefaultCallBack() {
                return null;
            }
        };
    }

    @Override
    public void onPre(Map<String, Object> mParams) {
        // 这个地方可以添加公共参数
        mParams.put("os", "android");
        mParams.put("version", "1.0");
    }

    @Override
    public final void onSuccess(String result) {
        try {
            final T base = JSON.parseObject(result, mClazz);
            if (base != null) {
                if (base.isSuccess()) {
                    sendSuccess(base);
                } else {
                    sendError(base.getStatus(), base.getMessage());
                }
            } else {
                sendError(ERROR_CODE, EMPTY_RESULT);
            }
        } catch (final Exception e) {
            sendError(ERROR_CODE, e.getMessage().toString());
        }
    }

    @Override
    public final void onError(final int code, final String message) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                onFailed(code, message);
            }
        });
    }

    /*发送成功的回调*/
    private void sendSuccess(final T bean) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(bean);
            }
        });
    }

    /*发送失败的回调*/
    private void sendError(final int code, final String message) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                onFailed(code, message);
            }
        });
    }

    /*我们自己的成功回调，直接回调业务bean*/
    public abstract void onSuccess(T t);

    /*我们自己的失败回调*/
    public void onFailed(int code, String message) {
    }

}
