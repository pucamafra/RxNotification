package com.marlonmafra.rxnotification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class RemoveNotificationInstanceOnSubscribe implements Observable.OnSubscribe<Void> {

    private static String TAG = "RxNotification";

    private Context context;

    public RemoveNotificationInstanceOnSubscribe(@NonNull Context mContext) {
        this.context = mContext;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {
        Scheduler.Worker inner = Schedulers.io().createWorker();
        subscriber.add(inner);
        inner.schedule(new Action0() {
            @Override
            public void call() {
                try {
                    InstanceID.getInstance(context).deleteInstanceID();
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                    Log.d(TAG,"Token has been deleted successfully");
                } catch (IOException e) {
                    Log.d(TAG,e.getMessage());
                    subscriber.onError(e);
                }
            }
        });
    }
}
