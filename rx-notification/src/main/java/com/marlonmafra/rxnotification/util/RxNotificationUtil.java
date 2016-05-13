package com.marlonmafra.rxnotification.util;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.marlonmafra.rx.notification.R;
import com.marlonmafra.rxnotification.exceptions.DeviceUnsupportedException;
import com.marlonmafra.rxnotification.exceptions.GooglePlayServicesNotInstalledException;
import com.marlonmafra.rxnotification.exceptions.GooglePlayServicesOutDatedException;
import com.marlonmafra.rxnotification.exceptions.UnknownErrorException;

public class RxNotificationUtil {

    /**
     * Verify whether your device has Google Play Service installed / Updated
     *
     * @param context
     *          Android context
     * @throws DeviceUnsupportedException
     * @throws GooglePlayServicesOutDatedException
     * @throws GooglePlayServicesNotInstalledException
     * @throws UnknownErrorException
     */
    public static void verifyGooglePlayService(Context context) throws DeviceUnsupportedException, GooglePlayServicesOutDatedException, GooglePlayServicesNotInstalledException, UnknownErrorException {

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int status = googleAPI.isGooglePlayServicesAvailable(context);

        if (status == ConnectionResult.SUCCESS) {
            return;
        }

        if (googleAPI.isUserResolvableError(status)) {
            switch (status) {
                case ConnectionResult.SERVICE_MISSING: {
                    throw new GooglePlayServicesNotInstalledException(context.getString(R.string.google_play_service_not_installed));
                }
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED: {
                    throw new GooglePlayServicesOutDatedException(context.getString(R.string.google_play_service_out_date));
                }
                default: {
                    throw new UnknownErrorException(context.getString(R.string.google_play_service_unknown_error, status));
                }
            }
        } else {
            throw new DeviceUnsupportedException(context.getString(R.string.google_play_service_unsupported_device));
        }
    }
}
