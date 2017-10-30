package com.dengzi.moduletest.plugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dengzi.moduletest.R;

/**
 * @Title: 没有在manifests中配置的Activity
 * @Author: djk
 * @Time: 2017/10/27
 * @Version:1.0.0
 */
public class NoConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_config);
    }
}

