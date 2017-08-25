package com.dengzi.moduletest.base;

import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.dengzi.moduletest.R;
import com.dengzi.moduletest.fragment.DrawerFragment;


/**
 * @Title:侧滑view基类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public abstract class BaseDrawerActivity extends BaseActivity {
    protected DrawerLayout rootLayout;
    protected DrawerFragment drawerFragment;

    @Override
    protected void setBaseView() {
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_drawer, null);
        setContentView(contentView);
        rootLayout = (DrawerLayout) findViewById(R.id.root_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_container);
        frameLayout.addView(LayoutInflater.from(this).inflate(bindLayout(), frameLayout, false));

        drawerFragment = DrawerFragment.newInstance();
        mActivity.getFragmentManager().beginTransaction().replace(R.id.frameLayout_drawer, drawerFragment).commit();
    }

    /**
     * 侧滑框里的点击事件
     *
     * @param viewId
     */
    public void onDrawerItemClick(int viewId) {

    }

    /**
     * 关闭侧滑框
     */
    public void closeDrawerView() {
        if (rootLayout != null && rootLayout.isShown()) {
            rootLayout.closeDrawers();
        }
    }
}
