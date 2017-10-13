package com.dengzi.dzframework.skin;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Method;

/**
 * @author Djk
 * @Title: 获取外部资源
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinResource {

    // 资源都是通过这个对象获取
    private Resources mSkinResource;
    // 皮肤apk对应的包名
    private String mSkinPackageName;

    public SkinResource(Context context, String skinPath) {
        try {
            //获取apk的资源 最终都要通过AssetManager 获取,  getAssets() 获取的AssetManager是获取的本身Apk的
            //获取其他Apk的资源需要实例化一个AssetManager,并把该AssetManager的加载路径修改为被 加载的Apk的路径
            // 反射获取AssetManager
            Resources supResource = context.getResources();
            AssetManager assetManager = AssetManager.class.newInstance();
            // 反射获取AssetManager中的addAssetPath方法
            Method method = AssetManager.class.getMethod("addAssetPath", String.class);
            // 反射执行addAssetPath方法
            method.invoke(assetManager, skinPath);
            // 创建一个我们自己的 Resources
            mSkinResource = new Resources(assetManager, supResource.getDisplayMetrics(), supResource.getConfiguration());
            // 获取skin包名
            getPackageNameByPath(context, skinPath);
        } catch (Exception e) {
        }
    }

    /**
     * 从一个路径中获取apk对应的包名
     *
     * @param context
     * @param skinPath
     */
    private void getPackageNameByPath(Context context, String skinPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            mSkinPackageName = appInfo.packageName;  //得到安装包名称
        }
    }

    /**
     * 通过名字来获取Drawable资源
     *
     * @param resName
     * @return
     */
    public Drawable getDrawableByName(String resName) {
        // 获取资源对应的id值
        Drawable drawable = null;
        try {
            int resourceId = mSkinResource.getIdentifier(resName, "drawable", mSkinPackageName);
            drawable = mSkinResource.getDrawable(resourceId);
        } catch (Resources.NotFoundException e) {
        }
        return drawable;
    }

    /**
     * 通过名字来获取Color资源
     *
     * @param resName
     * @return
     */
    public ColorStateList getColorByName(String resName) {
        // 获取资源对应的id值
        ColorStateList color = null;
        try {
            int resourceId = mSkinResource.getIdentifier(resName, "color", mSkinPackageName);
            color = mSkinResource.getColorStateList(resourceId);
        } catch (Resources.NotFoundException e) {
        }
        return color;
    }
}
