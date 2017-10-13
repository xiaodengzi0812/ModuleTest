package com.dengzi.dzframework.skin.attr;

import android.view.View;

import java.util.List;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinView {

    private View mView;
    private List<SkinAttr> mAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mAttrs = skinAttrs;
    }

    public void skin() {
        for (SkinAttr attr : mAttrs) {
            attr.skin(mView);
        }
    }


}
