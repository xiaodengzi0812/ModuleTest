package com.dengzi.moduletest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;
import com.dengzi.lib.util.ToastUtils;

/**
 * @Title:Toast工具类Demo
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class ToastActivity extends BaseBackActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ToastActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_toast;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle(getString(R.string.demo_toast));

        view.findViewById(R.id.btn_show_green_font).setOnClickListener(this);
        view.findViewById(R.id.btn_show_custom_bg).setOnClickListener(this);
        view.findViewById(R.id.btn_show_custom_view).setOnClickListener(this);
        view.findViewById(R.id.btn_show_middle).setOnClickListener(this);
        view.findViewById(R.id.btn_cancel_toast).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
        resetToast();
        switch (view.getId()) {
            case R.id.btn_show_green_font:
                ToastUtils.setMessageColor(Color.GREEN);
                ToastUtils.show(getResources().getString(R.string.toast_green_font), Toast.LENGTH_SHORT);
                break;
            case R.id.btn_show_custom_bg:
                ToastUtils.setBgResource(R.drawable.shape_round_rect);
                ToastUtils.show(getResources().getString(R.string.toast_custom_bg), Toast.LENGTH_SHORT);
                break;
            case R.id.btn_show_custom_view:
                ToastUtils.showCustom(R.layout.toast_custom, Toast.LENGTH_SHORT);
                break;
            case R.id.btn_show_middle:
                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                ToastUtils.show(getResources().getString(R.string.toast_middle), Toast.LENGTH_SHORT);
                break;
            case R.id.btn_cancel_toast:
                ToastUtils.cancel();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        resetToast();
        super.onDestroy();
    }

    private void resetToast() {
        ToastUtils.setMessageColor(0x12000000);
        ToastUtils.setBgResource(-1);
        ToastUtils.setView(null);
        ToastUtils.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, getResources().getDimensionPixelSize(R.dimen.offset_64));
    }
}
