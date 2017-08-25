package com.dengzi.moduletest.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;


/**
 * @Title:Activity基类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView, View.OnClickListener {

    /**
     * 当前Activity渲染的视图View
     */
    protected View contentView;
    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView();
        initView(savedInstanceState, contentView);
        doBusiness(this);
    }

    protected void setBaseView() {
        setContentView(contentView = LayoutInflater.from(this).inflate(bindLayout(), null));
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()) onWidgetClick(view);
    }
}
