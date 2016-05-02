package com.marlonmafra.rxnotification.sample;

import android.os.Bundle;

import com.marlonmafra.rxnotification.service.RxGcmService;

public class GCMService extends RxGcmService {

    @Override
    public void onMessageReceived(String from, Bundle data, boolean isAppRunning) {
        // Do whatever you want here
    }
}
