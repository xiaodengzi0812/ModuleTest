package com.dengzi.moduletest.base;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.dengzi.moduletest.R;


/**
 * @Title:DrawerActivity基类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public abstract class BaseBackActivity extends BaseActivity {

    protected CoordinatorLayout rootLayout;
    protected Toolbar           mToolbar;

    @Override
    protected void setBaseView() {
//        Slidr.attach(this);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_back, null);
        setContentView(contentView);
        rootLayout = (CoordinatorLayout) findViewById(R.id.root_layout);
        FrameLayout flActivityContainer = (FrameLayout) findViewById(R.id.activity_container);
        flActivityContainer.addView(LayoutInflater.from(this).inflate(bindLayout(), flActivityContainer, false));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getToolBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected ActionBar getToolBar() {
        return getSupportActionBar();
    }
}
