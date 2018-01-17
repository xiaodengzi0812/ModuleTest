package com.dengzi.moduletest;

import android.os.Environment;

import com.dengzi.dzframework.skin.SkinManager;
import com.dengzi.dzframework.skin.net.OkHttpEngine;
import com.dengzi.lib.DZUtil;
import com.dengzi.lib.hookstartactivity.HookStartActivityUtil;
import com.dengzi.lib.net.HttpManager;
import com.dengzi.lib.util.CrashUtils;
import com.dengzi.moduletest.base.BaseApplication;
import com.dengzi.moduletest.plugin.ProxyActivity;

/**
 * @Title: 自定义application
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initUtil();
        iniHttp();
        initSkin();
        initHook();
    }

    private void initHook() {
        new HookStartActivityUtil(this, ProxyActivity.class).execute();
    }

    /**
     * 初始化插件换肤框架
     */
    private void initSkin() {
        SkinManager.getInstance().init(this);
    }

    /**
     * 初始化http网络框架
     */
    private void iniHttp() {
        HttpManager.initEngine(OkHttpEngine.getInstance());
    }

    /**
     * 初始化工具类
     */
    private void initUtil() {
        // 初始化工具类
        DZUtil dzUtil = DZUtil.init(this);
        // 初始化异常捕获器
        dzUtil.initCrash();
        String crashDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dengzi_error";
        CrashUtils.setCrashDir(crashDir);
        // 初始化热修复
        dzUtil.initHotFix();
    }

}
