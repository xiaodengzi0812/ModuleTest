package com.dengzi.dzframework.skin.attr;

import android.view.View;

import java.util.List;

/**
 * @author Djk
 * @Title: 换肤view，包含具体的要换肤的view和该view下对应的属性集合
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinView {
    // 具体的换肤view
    private View mView;
    // 该view下对应的属性集合
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
