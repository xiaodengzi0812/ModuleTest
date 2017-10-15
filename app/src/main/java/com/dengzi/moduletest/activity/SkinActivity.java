package com.dengzi.moduletest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.dengzi.dzframework.skin.SkinBaseActivity;
import com.dengzi.dzframework.skin.SkinManager;
import com.dengzi.moduletest.R;

import java.io.File;

/**
 * @Title: 插件换肤
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class SkinActivity extends SkinBaseActivity {

    ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);
        iv = (ImageView) findViewById(R.id.iv);
        SkinManager.init(this);
    }

    /**
     * 换肤
     */
    public void skin(View view) {
        String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dengzi.skin";
        int result = SkinManager.getInstance().loadSkin(skinPath);
    }

    /**
     * 恢复
     */
    public void recovery(View view) {
        int result = SkinManager.getInstance().recovery();
    }

    /**
     * 恢复
     */
    public void toOtherPage(View view) {
        Intent intent = new Intent(SkinActivity.this, SkinActivity.class);
        startActivity(intent);
    }

}
