package com.marlonmafra.rxnotification.sample;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class GCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message) {
        // Do whatever you want here
        Log.d("TAG", "dfgdfg");
    }

}
