package com.dengzi.moduletest.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dengzi.dzframework.skin.net.BaseBean;
import com.dengzi.lib.net.HttpManager;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;
import com.dengzi.moduletest.net.RequestCallBack;

/**
 * @Title: 网络框架测试类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class NetTestActivity extends BaseBackActivity {

    TextView tv;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_net;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle("网络框架");
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    public void netTest(View view) {
        HttpManager.getInstence(this).url("https://api.ffan.com/gmps_usercenter/v1/appversion").post().execute(new RequestCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                tv.setText("success:" + bean.getMessage());
            }

            @Override
            public void onFailed(int code, String message) {
                tv.setText("error:" + message);
            }
        });
    }

}
