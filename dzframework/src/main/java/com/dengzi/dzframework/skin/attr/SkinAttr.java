package com.dengzi.dzframework.skin.attr;

import android.view.View;

/**
 * @author Djk
 * @Title: 属性bean
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinAttr {
    // 皮肤资源名称
    private String mResourceName;
    // 皮肤资源属性Type
    private SkinType mSkinType;

    public SkinAttr(String resourceName, SkinType skinType) {
        this.mResourceName = resourceName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view, mResourceName);
    }
}
