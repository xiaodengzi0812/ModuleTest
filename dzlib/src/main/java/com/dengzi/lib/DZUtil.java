package com.dengzi.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dengzi.lib.hotfix.HotFixManager;
import com.dengzi.lib.util.CrashUtils;

/**
 * @Title:Utils初始化相关
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public final class DZUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private static volatile DZUtil mDZUtil;

    private DZUtil(@NonNull final Context context) {
        DZUtil.context = context.getApplicationContext();
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static DZUtil init(@NonNull final Context context) {
        if (mDZUtil == null) {
            synchronized (DZUtil.class) {
                if (mDZUtil == null) {
                    mDZUtil = new DZUtil(context);
                }
            }
        }
        return mDZUtil;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("you should init first");
    }

    /**
     * 初始化异常捕获器
     */
    public void initCrash() {
        CrashUtils.init();
    }

    /**
     * 初始化热修复文件
     */
    public void initHotFix() {
        try {
            HotFixManager hotFixManager = new HotFixManager(context);
            hotFixManager.loadDex();
        } catch (Exception e) {
        }
    }
}
