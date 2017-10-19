package com.dengzi.moduletest.ipc.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.dengzi.moduletest.IServiceAidl;

/**
 * @Title: 守护的服务
 * @Author: djk
 * @Time: 2017/10/19
 * @Version:1.0.0
 */
public class GuardService extends Service {
    public GuardService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("dengzi", "GuardService 连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("dengzi", "GuardService 连接失败");
            // 连接失败后重启用户服务
            Intent userServiceIntent = new Intent(GuardService.this, UserService.class);
            startService(userServiceIntent);
            bindService(userServiceIntent, mServiceConn, Context.BIND_IMPORTANT);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            // 绑定守护进程服务
            Intent userServiceIntent = new Intent(this, UserService.class);
            bindService(userServiceIntent, mServiceConn, Context.BIND_IMPORTANT);
        } catch (Exception e) {
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IServiceAidl.Stub mBinder = new IServiceAidl.Stub() {

    };

    @Override
    public void onDestroy() {
        unbindService(mServiceConn);
        super.onDestroy();
    }
}
