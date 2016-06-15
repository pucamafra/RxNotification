package com.marlonmafra.rxnotification;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.concurrent.Callable;

import rx.Observable;

import static com.marlonmafra.rxnotification.util.Preconditions.checkNotNull;

public class RxNotification {

    private static final String FIREBASE_INSTANCE_ID_SCOPE = "FCM";

    /**
     * Create a token on Google Cloud Message
     *
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<String> getToken() {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return FirebaseInstanceId.getInstance().getToken();
            }
        });
    }

    /**
     * Create a token on Google Cloud Message
     *
     * @param gcmRegId Google Cloud Message Registration ID
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<String> getToken(@NonNull final String gcmRegId) {
        checkNotNull(gcmRegId, "gcmRegId == null");
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return FirebaseInstanceId.getInstance().getToken(gcmRegId, FIREBASE_INSTANCE_ID_SCOPE);
            }
        });
    }

    /**
     * Remove a token
     *
     * @param gcmRegId Google Cloud Message Registration ID
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> removeToken(@NonNull final String gcmRegId) {
        checkNotNull(gcmRegId, "gcmRegId == null");
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                FirebaseInstanceId.getInstance().deleteToken(gcmRegId, FIREBASE_INSTANCE_ID_SCOPE);
                return null;
            }
        });
    }

    /**
     * * Remove the Instance ID itself, including all associated tokens
     *
     * @return an Observable that emits the result.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> removeInstance() {
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                FirebaseInstanceId.getInstance().deleteInstanceId();
                return null;
            }
        });
    }
}
