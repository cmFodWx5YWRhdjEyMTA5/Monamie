package am.monamie.shop.activity.view.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Locale;

public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getName();

    public static String deviceId(Context context){
        @SuppressLint("HardwareIds") String device_UDID = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        Log.i(TAG, "deviceId: "+device_UDID);
        return device_UDID;
    }
    public static boolean isNetworkConnectionAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }
    public static String deviceLanguage(){
        String deviceLanguage = Locale.getDefault().getDisplayLanguage();
        Log.i(TAG, "deviceLanguage: "+deviceLanguage);
        return deviceLanguage;
    }

}
