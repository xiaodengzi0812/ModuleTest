package com.dengzi.moduletest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;
import com.dengzi.lib.util.SDCardUtils;

/**
 * @Title:SDCard工具类Demo
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class SDCardActivity extends BaseBackActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SDCardActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_sdcard;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle(getString(R.string.demo_sdcard));

        TextView tvAboutSdcard = (TextView) findViewById(R.id.tv_about_sdcard);
        tvAboutSdcard.setText("isSDCardEnable: " + SDCardUtils.isSDCardEnable()
                + "\ngetDataPath: " + SDCardUtils.getDataPath()
                + "\ngetSDCardPath: " + SDCardUtils.getSDCardPath()
                + "\ngetFreeSpace: " + SDCardUtils.getFreeSpace()
                + "\ngetSDCardInfo: " + SDCardUtils.getSDCardInfo()
        );
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
