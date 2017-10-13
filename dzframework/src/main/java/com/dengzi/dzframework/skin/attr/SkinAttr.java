package com.dengzi.dzframework.skin.attr;

import android.view.View;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinAttr {

    private String mResourceName;
    private SkinType mSkinType;

    public SkinAttr(String resourceName, SkinType skinType) {
        this.mResourceName = resourceName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view, mResourceName);
    }
}
