package com.marlonmafra.rxnotification.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.List;

public abstract class RxGcmService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        onMessageReceived(from, data, isAppRunning());
    }

    public abstract void onMessageReceived(String from, Bundle data, boolean isAppRunning);

    private boolean isAppRunning() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.processName.equals(getApplication().getPackageName())) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return processInfo.pkgList.length > 0;
                }
            }
        }
        return false;
    }
}
