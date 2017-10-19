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
 * @Title: 用户自己的服务
 * @Author: djk
 * @Time: 2017/10/19
 * @Version:1.0.0
 */
public class UserService extends Service {
    public UserService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("dengzi", "UserService 连接成功");
            // 这里可以获取一个别的进程的aidl，然后aidl里有什么方法可以在这里调用
            // IServiceAidl aidl = IServiceAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("dengzi", "UserService 连接失败");
            Intent guardServiceIntent = new Intent(UserService.this, GuardService.class);
            startService(guardServiceIntent);
            bindService(guardServiceIntent, mServiceConn, Context.BIND_IMPORTANT);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            // 绑定用户进程服务
            Intent guardServiceIntent = new Intent(this, GuardService.class);
            bindService(guardServiceIntent, mServiceConn, Context.BIND_IMPORTANT);
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
