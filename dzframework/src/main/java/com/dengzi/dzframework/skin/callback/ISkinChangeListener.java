package com.dengzi.dzframework.skin.callback;

import com.dengzi.dzframework.skin.SkinResource;

/**
 * @author Djk
 * @Title: 皮肤框架回调接口（为了解决自定义view的问题，把resource返回给当前activity，让调用者自己去针对自定义view去做一些处理）
 * @Time: 2017/10/17.
 * @Version:1.0.0
 */
public interface ISkinChangeListener {
    void onSkinChanged(SkinResource resource);
}
