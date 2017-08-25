package com.dengzi.moduletest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseDrawerActivity;

/**
 * @Title: MainActivity
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class MainActivity extends BaseDrawerActivity {

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.root_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    public void crashClick(View view) {
        throw new NullPointerException();
    }

    public void deviceClick(View view) {
        DeviceActivity.start(this);
    }

    public void appClick(View view) {
        AppActivity.start(this);
    }

    public void imageClick(View view) {
        ImageActivity.start(this);
    }

    public void networkClick(View view) {
        NetworkActivity.start(this);
    }

    public void phoneClick(View view) {
        PhoneActivity.start(this);
    }

    public void pinyinClick(View view) {
        PinyinActivity.start(this);
    }

    public void processClick(View view) {
        ProcessActivity.start(this);
    }

    public void sdcardClick(View view) {
        SDCardActivity.start(this);
    }

    public void spannableClick(View view) {
        SpanActivity.start(this);
    }

    public void toastClick(View view) {
        ToastActivity.start(this);
    }

    @Override
    public void onDrawerItemClick(int viewId) {
        // 这里就是侧滑框的点击回调事件
    }
}
