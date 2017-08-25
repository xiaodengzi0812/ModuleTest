package com.dengzi.moduletest.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dengzi.lib.ioc.BindClick;
import com.dengzi.lib.ioc.BindUtil;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.activity.DaoActivity;
import com.dengzi.moduletest.activity.HotFixActivity;
import com.dengzi.moduletest.activity.IocActivity;
import com.dengzi.moduletest.activity.NetTestActivity;
import com.dengzi.moduletest.base.BaseDrawerActivity;
import com.dengzi.moduletest.base.BaseFragment;

/**
 * @Title: 侧滑view
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class DrawerFragment extends BaseFragment {

    public static DrawerFragment newInstance() {
        Bundle args = new Bundle();
        DrawerFragment fragment = new DrawerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_drawer;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        BindUtil.inject(view, this);
    }

    @Override
    public void doBusiness(Context context) {
    }

    @Override
    public void onWidgetClick(View view) {
    }

    @BindClick({R.id.ioc_tv, R.id.hot_fix_tv, R.id.net_test_tv, R.id.dao_test_tv})
    public void onClick(View view) {
        ((BaseDrawerActivity) getActivity()).closeDrawerView();
        switch (view.getId()) {
            case R.id.ioc_tv:
                mActivity.startActivity(new Intent(getActivity(), IocActivity.class));
                break;
            case R.id.hot_fix_tv:
                mActivity.startActivity(new Intent(getActivity(), HotFixActivity.class));
                break;
            case R.id.net_test_tv:
                mActivity.startActivity(new Intent(getActivity(), NetTestActivity.class));
                break;
            case R.id.dao_test_tv:
                mActivity.startActivity(new Intent(getActivity(), DaoActivity.class));
                break;
        }
        ((BaseDrawerActivity) getActivity()).onDrawerItemClick(view.getId());
    }

}
