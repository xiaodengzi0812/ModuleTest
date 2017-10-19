package com.dengzi.moduletest.ipc.service;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * @author Djk
 * @Title: JobService 开户定时轮寻，看UserService有没有被杀死，如果被杀死了就启动它
 * @Time: 2017/10/19.
 * @Version:1.0.0
 */
public class JobWakeUpService extends JobService {
    private static final int jobId = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JobInfo.Builder jobBuilder = new JobInfo.Builder(jobId,
                new ComponentName(this, JobWakeUpService.class));
        // 设置轮寻时间
        jobBuilder.setPeriodic(5000);
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobBuilder.build());

        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        // 开户定时轮寻，看UserService有没有被杀死，如果被杀死了就启动它
        boolean userServiceRunning = isServiceRunning(UserService.class.getName());
        if (!userServiceRunning) {
            Intent userServiceIntent = new Intent(JobWakeUpService.this, UserService.class);
            startService(userServiceIntent);
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    /**
     * 判断进程服务是否运行
     *
     * @return
     */
    private boolean isServiceRunning(String proessName) {
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : lists) {
            if (info.processName.equals(proessName)) {
                isRunning = true;
            }
        }
        return isRunning;
    }

}
