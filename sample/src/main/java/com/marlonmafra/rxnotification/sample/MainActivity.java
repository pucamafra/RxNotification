package com.marlonmafra.rxnotification.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.marlonmafra.rxnotification.RxNotification;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "TestNotification";

    private String gcmRegId = "744983548091";


    @Bind(R.id.btnRegister)
    Button btnRegister;

    @Bind(R.id.btnRemove)
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "OnCreate");
    }

    @OnClick(R.id.btnRegister)
    public void register() {

        RxNotification.getToken(getApplicationContext(), this.gcmRegId)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                   //     Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Register - onError - " + e.getMessage());
                    }

                    @Override
                    public void onNext(String token) {
                    //    Log.d(TAG, token);
                    }
                });
    }

    @OnClick(R.id.btnRemove)
    public void removeToken() {
        RxNotification.removeToken(this, this.gcmRegId)
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Remove - onError - " + e.getMessage());
                    }

                    @Override
                    public void onNext(Void avoid) {
                        Log.d(TAG, "onNext");
                    }
                });
    }


}
