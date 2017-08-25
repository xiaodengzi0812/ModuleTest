package com.dengzi.moduletest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dengzi.lib.util.DeviceUtils;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;

/**
 * @Title:Device工具类Demo
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class DeviceActivity extends BaseBackActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, DeviceActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_device;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle(getString(R.string.demo_device));
        TextView tvAboutDevice = (TextView) findViewById(R.id.tv_about_device);
        tvAboutDevice.setText("isRoot: " + DeviceUtils.isDeviceRooted()
                + "\ngetSDKVersion: " + DeviceUtils.getSDKVersion()
                + "\ngetAndroidID: " + DeviceUtils.getAndroidID()
                + "\ngetMacAddress: " + DeviceUtils.getMacAddress()
                + "\ngetManufacturer: " + DeviceUtils.getManufacturer()
                + "\ngetModel: " + DeviceUtils.getModel()
        );
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
    }
}