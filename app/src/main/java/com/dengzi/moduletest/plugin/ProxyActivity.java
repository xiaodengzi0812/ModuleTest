package com.dengzi.moduletest.plugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dengzi.moduletest.R;

/**
 * @Title: 用来绕过系统manifests检查的代理activity
 * @Author: djk
 * @Time: 2017/10/27
 * @Version:1.0.0
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

