package com.dengzi.dzframework.skin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.ArrayMap;

import com.dengzi.dzframework.skin.attr.SkinView;

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

    private SkinResource mSkinResource;

    public Map<Activity, List<SkinView>> mSkinViews = new ArrayMap<>();

    static {
        mSkinManager = new SkinManager();
    }

    public static SkinManager getInstance() {
        return mSkinManager;
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 加载皮肤
     *
     * @param skinPath
     * @return
     */
    public int loadSkin(String skinPath) {
        mSkinResource = new SkinResource(mContext.getApplicationContext(), skinPath);
        Set<Activity> keySet = mSkinViews.keySet();
        for (Activity activity : keySet) {
            List<SkinView> skinViews = mSkinViews.get(activity);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }
        }
        return 0;
    }

    /**
     * 恢复默认
     *
     * @return
     */
    public int recovery() {
        return 0;
    }

    /**
     * 获取SkinView集合
     *
     * @param activity
     */
    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    /**
     * 获取SkinView集合
     *
     * @param activity
     */
    public void setSkinViews(Activity activity, List<SkinView> skinViews) {
        mSkinViews.put(activity, skinViews);
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
