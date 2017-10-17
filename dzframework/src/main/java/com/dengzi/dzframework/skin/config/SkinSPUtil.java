package com.dengzi.dzframework.skin.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * @author Djk
 * @Title: SharedPreferences工具类
 * @Time: 2017/10/16.
 * @Version:1.0.0
 */
public class SkinSPUtil {
    private static Context mContext;
    private static SharedPreferences sharedPreferences;
    private static boolean hasChangedSkin = false;

    private static class SkinHolder {
        static final SkinSPUtil INSTANCE = new SkinSPUtil();
    }

    public static SkinSPUtil getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences(SkinConfig.SKIN_CONFIG_NAME, Context.MODE_PRIVATE);
        }
        hasChangedSkin = !TextUtils.isEmpty(sharedPreferences.getString(SkinConfig.SKIN_PATH_NAME, ""));
        return SkinHolder.INSTANCE;
    }

    private SkinSPUtil() {
    }

    /**
     * 获取皮肤路径
     *
     * @return
     */
    public String getSkinPath() {
        return sharedPreferences.getString(SkinConfig.SKIN_PATH_NAME, "");
    }

    /**
     * 保存皮肤路径
     */
    public void saveSkinPath(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            hasChangedSkin = false;
        } else {
            hasChangedSkin = true;
        }
        sharedPreferences.edit().putString(SkinConfig.SKIN_PATH_NAME, skinPath).commit();
    }

    /**
     * 保存皮肤路径
     */
    public void clearSkinInfo() {
        hasChangedSkin = false;
        sharedPreferences.edit().putString(SkinConfig.SKIN_PATH_NAME, "").commit();
    }

    /**
     * 是否已经替换皮肤
     *
     * @return
     */
    public boolean hasChangedSkin() {
        return hasChangedSkin;
    }

}
