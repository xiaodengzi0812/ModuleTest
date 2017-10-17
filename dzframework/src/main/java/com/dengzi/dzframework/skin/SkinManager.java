package com.dengzi.dzframework.skin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.dengzi.dzframework.skin.attr.SkinView;
import com.dengzi.dzframework.skin.callback.ISkinChangeListener;
import com.dengzi.dzframework.skin.config.SkinConfig;
import com.dengzi.dzframework.skin.config.SkinSPUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Djk
 * @Title: 换肤框架管理类
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class SkinManager {
    private static SkinManager mSkinManager;
    private static Context mContext;
    // 获取外部资源
    private SkinResource mSkinResource;
    // 换肤view的集合
    public Map<ISkinChangeListener, List<SkinView>> mSkinViews = new ArrayMap<>();

    static {
        mSkinManager = new SkinManager();
    }

    public static SkinManager getInstance() {
        return mSkinManager;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        // 初始化资源加载
        initSkinResource(SkinSPUtil.getInstance(mContext).getSkinPath());
        if (mSkinResource == null) {
            initDefaultResource();
        }
    }

    /**
     * 初始化皮肤资源
     *
     * @param skinPath
     * @return
     */
    public SkinResource initSkinResource(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            return null;
        }
        // 判断皮肤文件是否存在
        File skinFile = new File(skinPath);
        if (!skinFile.exists()) {
            return null;
        }
        // 判断皮肤文件是否可以获取到包名
        if (TextUtils.isEmpty(SkinResource.getPackageNameByPath(mContext, skinPath))) {
            return null;
        }
        mSkinResource = new SkinResource(mContext.getApplicationContext(), skinPath);
        return mSkinResource;
    }

    /**
     * 初始化默认资源
     */
    public void initDefaultResource() {
        String defaultPath = mContext.getPackageResourcePath();
        // 判断皮肤文件是否存在
        File skinFile = new File(defaultPath);
        if (!skinFile.exists()) {
            return;
        }
        // 判断皮肤文件是否可以获取到包名
        if (TextUtils.isEmpty(SkinResource.getPackageNameByPath(mContext, defaultPath))) {
            return;
        }
        mSkinResource = new SkinResource(mContext.getApplicationContext(), defaultPath);
    }

    /**
     * 加载皮肤
     *
     * @param skinPath
     * @return
     */
    public int loadSkin(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            throw new NullPointerException("SkinPath is not be null !");
        }
        // 不重复加载相同的皮肤
        String currentPath = SkinSPUtil.getInstance(mContext).getSkinPath();
        if (skinPath.equals(currentPath)) {
            return SkinConfig.SKIN_LOAD_EXIST;
        }

        // 校验皮肤文件
        if (initSkinResource(skinPath) == null) {
            return SkinConfig.SKIN_FILE_ERROR;
        }
        changeSkinView();
        // 保存皮肤路径
        SkinSPUtil.getInstance(mContext).saveSkinPath(skinPath);
        return SkinConfig.SKIN_LOAD_SUCCESS;
    }


    /**
     * 恢复默认
     *
     * @return
     */
    public int recovery() {
        // 不重复加载相同的皮肤
        String currentPath = SkinSPUtil.getInstance(mContext).getSkinPath();
        if (TextUtils.isEmpty(currentPath)) {
            return SkinConfig.SKIN_LOAD_EXIST;
        }
        initDefaultResource();
        changeSkinView();
        // 清除皮肤路径
        SkinSPUtil.getInstance(mContext).clearSkinInfo();
        return SkinConfig.SKIN_LOAD_SUCCESS;
    }

    /**
     * 改变皮肤view
     */
    private void changeSkinView() {
        Set<ISkinChangeListener> keySet = mSkinViews.keySet();
        for (ISkinChangeListener listener : keySet) {
            List<SkinView> skinViews = mSkinViews.get(listener);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }
            listener.onSkinChanged(mSkinResource);
        }
    }

    /**
     * 获取SkinView集合
     *
     * @param listener
     */
    public List<SkinView> getSkinViews(ISkinChangeListener listener) {
        return mSkinViews.get(listener);
    }

    /**
     * 设置SkinView集合
     */
    public void registerSkinViews(ISkinChangeListener listener, List<SkinView> skinViews) {
        mSkinViews.put(listener, skinViews);
    }

    /**
     * 在activity退出时删除集合中的view(防止内存泄露)
     *
     * @param listener
     */
    public void unregisterSkinViews(ISkinChangeListener listener) {
        try {
            mSkinViews.remove(listener);
        } catch (Exception e) {
        }
    }

    /**
     * 获取SkinResource
     *
     * @return
     */
    public SkinResource getSkinResource() {
        return mSkinResource;
    }

}
