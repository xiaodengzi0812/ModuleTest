package com.dengzi.moduletest.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dengzi.lib.ioc.BindClick;
import com.dengzi.lib.ioc.BindUtil;
import com.dengzi.lib.ioc.BindView;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;

/**
 * @Title:测试ioc框架
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class IocActivity extends BaseBackActivity {
    @BindView(R.id.tv1)
    private TextView tv1;
    @BindView(R.id.tv2)
    private TextView tv2;

    @BindClick({R.id.tv1, R.id.tv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                Toast.makeText(IocActivity.this, "click hello1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv2:
                Toast.makeText(IocActivity.this, "click hello2", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_ioc;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle("IOC 测试");
        BindUtil.inject(this);
        tv1.setText("hello1");
        tv2.setText("hello2");
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
