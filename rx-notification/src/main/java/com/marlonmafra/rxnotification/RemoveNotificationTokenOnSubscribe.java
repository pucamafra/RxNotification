package com.marlonmafra.rxnotification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RemoveNotificationTokenOnSubscribe implements Observable.OnSubscribe<Void> {

    private static String TAG = "RxNotification";

    private String gcmRegId;
    private Context context;

    public RemoveNotificationTokenOnSubscribe(@NonNull Context mContext, @NonNull String mGcmRegId) {
        this.context = mContext;
        this.gcmRegId = mGcmRegId;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {
        Scheduler.Worker inner = Schedulers.io().createWorker();
        subscriber.add(inner);
        inner.schedule(() -> {
            try {
                InstanceID.getInstance(context).deleteToken(this.gcmRegId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
                subscriber.onNext(null);
                subscriber.onCompleted();
                Log.d(TAG,"Token has been deleted successfully");
            } catch (IOException e) {
                Log.d(TAG,e.getMessage());
                subscriber.onError(e);
            }
        });
    }
}
