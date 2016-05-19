package com.marlonmafra.rxnotification;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.util.concurrent.Callable;

import rx.Observable;

import static com.marlonmafra.rxnotification.util.Preconditions.checkNotNull;

public class RxNotification {

    /**
     * Create a token on Google Cloud Message
     *
     * @param context  Android context
     * @param gcmRegId Google Cloud Message Registration ID
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<String> getToken(@NonNull Context context, int gcmRegId) {
        checkNotNull(context, "context == null");
        checkNotNull(gcmRegId, "gcmRegId == id not found");
        return getToken(context, context.getString(gcmRegId));
    }

    /**
     * Create a token on Google Cloud Message
     *
     * @param context  Android context
     * @param gcmRegId Google Cloud Message Registration ID
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<String> getToken(@NonNull final Context context, @NonNull final String gcmRegId) {
        checkNotNull(context, "context == null");
        checkNotNull(gcmRegId, "gcmRegId == null");
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return InstanceID.getInstance(context).getToken(gcmRegId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            }
        });
    }

    /**
     * Remove a token
     *
     * @param context  Android context
     * @param gcmRegId Google Cloud Message Registration ID
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> removeToken(@NonNull Context context, int gcmRegId) {
        checkNotNull(context, "context == null");
        checkNotNull(gcmRegId, "gcmRegId ==  id not found");
        return removeToken(context, context.getString(gcmRegId));
    }

    /**
     * Remove a token
     *
     * @param context  Android context
     * @param gcmRegId Google Cloud Message Registration ID
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> removeToken(@NonNull final Context context, @NonNull final String gcmRegId) {
        checkNotNull(context, "context == null");
        checkNotNull(gcmRegId, "gcmRegId == null");
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                InstanceID.getInstance(context).deleteToken(gcmRegId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
                return null;
            }
        });
    }

    /**
     * * Remove the Instance ID itself, including all associated tokens
     *
     * @param context Android context
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> removeInstance(@NonNull final Context context) {
        checkNotNull(context, "context == null");
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                InstanceID.getInstance(context).deleteInstanceID();
                return null;
            }
        });
    }
}
