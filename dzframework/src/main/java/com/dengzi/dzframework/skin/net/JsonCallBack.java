package com.dengzi.dzframework.skin.net;

import android.os.Handler;
import android.util.Log;

import com.dengzi.lib.net.HttpCallBack;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author Djk
 * @Title: json解析业务并返回业务bean
 * @Time: 2017/8/22.
 * @Version:1.0.0
 */
public abstract class JsonCallBack<T extends BaseBean> implements HttpCallBack {
    public final Class<T> mClazz;
    public Handler deliver;
    private JsonClient<T> mJsonClient;

    public JsonCallBack() {
        this(null);
    }

    public JsonCallBack(JsonClient jsonClient) {
        deliver = new Handler();
        mClazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (jsonClient != null) {
            mJsonClient = jsonClient;
        } else {
            mJsonClient = new FastJsonClient<>();
        }
    }

    @Override
    public boolean onPre(Map<String, Object> mParams) {
        // 这个地方可以添加公共参数
        return false;
    }

    @Override
    public final void onSuccess(String result) {
        try {
            // 解析业务成bean
            Log.e("dengzi", "result = " + result);
            final T base = mJsonClient.jsonToBean(result, mClazz);
            if (base != null) {
                // 判断code
                // TODO 这个地方待定
                if (base.isSuccess()) {
                    sendSuccess(base);
                } else {
                    sendError(base.getStatus(), base.message);
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
