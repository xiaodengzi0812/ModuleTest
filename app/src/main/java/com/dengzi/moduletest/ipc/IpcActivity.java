package com.dengzi.moduletest.ipc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dengzi.moduletest.R;
import com.dengzi.moduletest.ipc.service.GuardService;
import com.dengzi.moduletest.ipc.service.JobWakeUpService;
import com.dengzi.moduletest.ipc.service.UserService;

/**
 * @author Djk
 * @Title: ipc进程间通信
 * @Time: 2017/10/19.
 * @Version:1.0.0
 */
public class IpcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
    }


    public void bindService(View view) {
        initService();
    }

    /**
     * 开启服务
     */
    private void initService() {
        // 开启自己的服务
        Intent userServiceIntent = new Intent(this, UserService.class);
        startService(userServiceIntent);

        // 开启守护的服务
        Intent guardServiceIntent = new Intent(this, GuardService.class);
        startService(guardServiceIntent);

        // 大于5.0则启动jobservice
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent jobServiceIntent = new Intent(this, JobWakeUpService.class);
            startService(jobServiceIntent);
        }
    }

}
