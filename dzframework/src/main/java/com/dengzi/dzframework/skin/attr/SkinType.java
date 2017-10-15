package com.dengzi.dzframework.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dengzi.dzframework.skin.SkinManager;

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
            TextView tv = (TextView) view;
            ColorStateList color = SkinManager.getInstance().getSkinResource().getColorByName(resourceName);
            if (color != null) {
                tv.setTextColor(color);
            }
        }
    }, BACKGROUNT("background") {
        @Override
        public void skin(View view, String resourceName) {
            ColorStateList color = SkinManager.getInstance().getSkinResource().getColorByName(resourceName);
            if (color != null) {
                view.setBackgroundColor(color.getDefaultColor());
            }

            Drawable drawable = SkinManager.getInstance().getSkinResource().getDrawableByName(resourceName);
            if (drawable != null) {
                view.setBackgroundDrawable(drawable);
            }
        }
    }, SRC("src") {
        @Override
        public void skin(View view, String resourceName) {
            ImageView iv = (ImageView) view;
            Drawable drawable = SkinManager.getInstance().getSkinResource().getDrawableByName(resourceName);
            if (drawable != null) {
                iv.setImageDrawable(drawable);
            }
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
