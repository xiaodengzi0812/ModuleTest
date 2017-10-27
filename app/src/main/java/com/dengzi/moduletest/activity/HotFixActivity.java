package com.dengzi.moduletest.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dengzi.lib.hotfix.HotFixManager;
import com.dengzi.lib.ioc.BindClick;
import com.dengzi.lib.ioc.BindUtil;
import com.dengzi.lib.ioc.BindView;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.base.BaseBackActivity;

import java.io.File;

/**
 * @Title:测试热修复
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class HotFixActivity extends BaseBackActivity {
    @BindView(R.id.test_tv)
    private TextView testTv;

    @Override
    public int bindLayout() {
        return R.layout.activity_hot_fix;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        BindUtil.inject(this);
        getToolBar().setTitle("热修复测试");
    }

    @BindClick({R.id.fix_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fix_btn:
                fixDex();
                break;
        }
    }

    /**
     * 加载热修复文件
     */
    private void fixDex() {
        HotFixManager hotFixManager = new HotFixManager(this);
        File dexFile = new File(Environment.getExternalStorageDirectory(), "hotFix.apk");
        if (dexFile.exists()) {
            try {
                hotFixManager.addDex(dexFile.getAbsolutePath());
                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "修复失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "修复包不存在", Toast.LENGTH_SHORT).show();
        }
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
