package com.dengzi.moduletest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dengzi.lib.util.AppUtils;
import com.dengzi.lib.util.SpanUtils;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;


/**
 * @Title: App工具类Demo
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class AppActivity extends BaseBackActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AppActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_app;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle(getString(R.string.demo_app));
        TextView tvAboutApp = (TextView) findViewById(R.id.tv_about_app);
        tvAboutApp.setText(
                new SpanUtils()
                        .appendLine("app icon: ").appendImage(AppUtils.getAppIcon(), SpanUtils.ALIGN_CENTER)
                        .appendLine(AppUtils.getAppInfo().toString())
                        .appendLine("isAppRoot: " + AppUtils.isAppRoot())
                        .appendLine("isAppDebug: " + AppUtils.isAppDebug())
                        .appendLine("AppSignatureSHA1: " + AppUtils.getAppSignatureSHA1())
                        .append("isAppForeground: " + AppUtils.isAppForeground())
                        .create());
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

}
