package com.dengzi.lib.ioc;

import android.app.Activity;
import android.view.View;

/**
 * @Title:绑定辅助类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class BindHelper {
    private Activity mAct;
    private View mView;

    public BindHelper(Activity act) {
        this.mAct = act;
    }

    public BindHelper(View view) {
        this.mView = view;
    }

    public View findViewById(int viewID) {
        return mAct != null ? mAct.findViewById(viewID) : mView.findViewById(viewID);
    }
}
