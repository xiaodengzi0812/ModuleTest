package com.dengzi.dzframework.skin;

import android.app.Activity;
import android.content.Context;
import android.util.ArrayMap;

import com.dengzi.dzframework.skin.attr.SkinView;

import java.util.Map;
import java.util.Set;

/**
 * @author Djk
 * @Title: 换肤框架管理类
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinManager {
    private static SkinManager mSkinManager;
    private static Context mContext;

    private Map<Activity, SkinView> mSkinViews = new ArrayMap<>();

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
        SkinResource skinResource = new SkinResource(mContext.getApplicationContext(), skinPath);
        Set<Activity> keySet = mSkinViews.keySet();
        for (Activity activity : keySet) {
            SkinView skinView = mSkinViews.get(activity);
            skinView.skin();
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
}
