package com.dengzi.moduletest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;
import com.dengzi.lib.util.NetworkUtils;

/**
 * @Title:Network工具类Demo
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class NetworkActivity extends BaseBackActivity {

    private TextView tvAboutNetwork;

    public static void start(Context context) {
        Intent starter = new Intent(context, NetworkActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_network;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle(getString(R.string.demo_network));

        tvAboutNetwork = (TextView) findViewById(R.id.tv_about_network);
        findViewById(R.id.btn_open_wireless_settings).setOnClickListener(this);
        findViewById(R.id.btn_set_wifi_enabled).setOnClickListener(this);
        setAboutNetwork();
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_wireless_settings:
                NetworkUtils.openWirelessSettings();
                break;
            case R.id.btn_set_data_enabled:
                NetworkUtils.setDataEnabled(!NetworkUtils.getDataEnabled());
                break;
            case R.id.btn_set_wifi_enabled:
                NetworkUtils.setWifiEnabled(!NetworkUtils.getWifiEnabled());
                break;
        }
        setAboutNetwork();
    }

    private void setAboutNetwork() {
        tvAboutNetwork.setText("isConnected: " + NetworkUtils.isConnected()
                + "\ngetDataEnabled: " + NetworkUtils.getDataEnabled()
                + "\nis4G: " + NetworkUtils.is4G()
                + "\ngetWifiEnabled: " + NetworkUtils.getWifiEnabled()
                + "\ngetNetworkOperatorName: " + NetworkUtils.getNetworkOperatorName()
                + "\ngetNetworkTypeName: " + NetworkUtils.getNetworkType()
        );
    }
}
