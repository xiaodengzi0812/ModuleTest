package com.dengzi.dzframework.skin.attr;

import android.view.View;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public enum SkinType {

    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String resourceName) {

        }
    }, BACKGROUNT("background") {
        @Override
        public void skin(View view, String resourceName) {

        }
    }, SRC("src") {
        @Override
        public void skin(View view, String resourceName) {

        }
    };

    private String mResourceName;

    SkinType(String resourceName) {
        this.mResourceName = resourceName;
    }

    public abstract void skin(View view, String resourceName);

    public String getResName() {
        return mResourceName;
    }
}
