package com.dengzi.moduletest.net;

import android.util.Log;
import android.widget.Toast;

import com.dengzi.dzframework.skin.net.BaseBean;
import com.dengzi.dzframework.skin.net.JsonCallBack;
import com.dengzi.lib.util.NetworkUtils;
import com.dengzi.moduletest.MyApp;

import java.util.Map;

/**
 * @author Djk
 * @Title:
 * @Time: 2018/1/17.
 * @Version:1.0.0
 */
public abstract class RequestCallBack<T extends BaseBean> extends JsonCallBack {

    @Override
    public boolean onPre(Map mParams) {
        // 拦截无网络连接
        boolean isConected = NetworkUtils.isConnected();
        if (!isConected) {
            Toast.makeText(MyApp.getInstance(), "网络连接失败", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onPre(mParams);
    }
}
