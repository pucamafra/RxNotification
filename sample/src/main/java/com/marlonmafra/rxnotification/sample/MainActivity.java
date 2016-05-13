package com.marlonmafra.rxnotification.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.marlonmafra.rxnotification.RxNotification;
import com.marlonmafra.rxnotification.exceptions.DeviceUnsupportedException;
import com.marlonmafra.rxnotification.exceptions.GooglePlayServicesNotInstalledException;
import com.marlonmafra.rxnotification.exceptions.GooglePlayServicesOutDatedException;
import com.marlonmafra.rxnotification.exceptions.UnknownErrorException;
import com.marlonmafra.rxnotification.util.RxNotificationUtil;

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
        try {
            RxNotificationUtil.verifyGooglePlayService(this);
            RxNotification.getToken(getApplicationContext(), this.gcmRegId)
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "Register - onError - " + e.getMessage());
                        }

                        @Override
                        public void onNext(String token) {
                            Log.d(TAG, token);
                        }
                    });
        } catch (DeviceUnsupportedException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (GooglePlayServicesOutDatedException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (GooglePlayServicesNotInstalledException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (UnknownErrorException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
