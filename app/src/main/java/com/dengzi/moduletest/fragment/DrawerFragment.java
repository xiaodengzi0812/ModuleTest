package com.dengzi.moduletest.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dengzi.lib.hookstartactivity.HookStartActivityUtil;
import com.dengzi.lib.ioc.BindClick;
import com.dengzi.lib.ioc.BindUtil;
import com.dengzi.moduletest.R;
import com.dengzi.moduletest.activity.HotFixActivity;
import com.dengzi.moduletest.activity.IocActivity;
import com.dengzi.moduletest.activity.SkinActivity;
import com.dengzi.moduletest.activity.NetTestActivity;
import com.dengzi.moduletest.base.BaseDrawerActivity;
import com.dengzi.moduletest.base.BaseFragment;
import com.dengzi.moduletest.ipc.IpcActivity;
import com.dengzi.moduletest.plugin.NoConfigActivity;
import com.dengzi.moduletest.plugin.ProxyActivity;

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

    @BindClick({R.id.ioc_tv, R.id.hot_fix_tv, R.id.net_test_tv, R.id.load_image_tv, R.id.guard_service_tv, R.id.no_config_tv})
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
            case R.id.load_image_tv:
                mActivity.startActivity(new Intent(getActivity(), SkinActivity.class));
                break;
            case R.id.guard_service_tv:
                mActivity.startActivity(new Intent(getActivity(), IpcActivity.class));
                break;
            case R.id.no_config_tv:
                toNoConfigActivity();
                break;
        }
        ((BaseDrawerActivity) getActivity()).onDrawerItemClick(view.getId());
    }

    /**
     * 跳转到没有在manifests中配置的activity
     */
    private void toNoConfigActivity() {
        // 在MyApp中initHook()
        mActivity.startActivity(new Intent(getActivity(), NoConfigActivity.class));
    }
}
