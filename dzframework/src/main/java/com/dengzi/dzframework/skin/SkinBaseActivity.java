package com.dengzi.dzframework.skin;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import com.dengzi.dzframework.skin.attr.SkinAttr;
import com.dengzi.dzframework.skin.attr.SkinAttrSupport;
import com.dengzi.dzframework.skin.attr.SkinView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

/**
 * @author Djk
 * @Title: 换肤基类
 * @Time: 2017/10/13.
 * @Version:1.0.0
 */
public class SkinBaseActivity extends AppCompatActivity implements LayoutInflaterFactory {
    private SkinViewInflater mSkinViewInflater;
    private final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < 21;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // view的绘制拦截
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        if (layoutInflater.getFactory() == null) {
            LayoutInflaterCompat.setFactory(layoutInflater, this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 1.创建view,拷贝系统的创建view方法（AppCompatActivity）
        View view = createView(parent, name, context, attrs);

        if (view != null) {
            // 2.解析属性 textColor background src
            List<SkinAttr> skinAttrList = SkinAttrSupport.getSkinAttr(context, attrs);
            // 将解析好的属性添加到SkinView中
            SkinView skinView = new SkinView(view, skinAttrList);
            // 3.交给SkinManager去管理
            managerSkinView(skinView);
        }
        return view;
    }

    /**
     * 统一管理skinview
     */
    private void managerSkinView(SkinView skinView) {

    }

    /**
     * ------------------复制系统创建view功能Start----------------
     */
    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        if (mSkinViewInflater == null) {
            mSkinViewInflater = new SkinViewInflater();
        }
        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    : shouldInheritContext((ViewParent) parent);
        }
        return mSkinViewInflater.createView(parent, name, context, attrs, inheritContext,
                IS_PRE_LOLLIPOP, /* Only read android:theme pre-L (L+ handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            return false;
        }
        final View windowDecor = getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                return false;
            }
            parent = parent.getParent();
        }
    }
    /**------------------复制系统创建view功能End----------------*/
}
