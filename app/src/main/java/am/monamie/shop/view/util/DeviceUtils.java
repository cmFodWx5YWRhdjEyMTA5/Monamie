package am.monamie.shop.view.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Locale;

import am.monamie.shop.view.constants.MonAmieEnum;
import am.monamie.shop.view.helper.SharedPreferencesHelper;

public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getName();
    private static String token;

    public static String deviceId(Context context) {
        @SuppressLint("HardwareIds") String device_UDID = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        Log.i(TAG, "deviceId: " + device_UDID);
        return device_UDID;
    }

    public static boolean isNetworkConnectionAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();

            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    public static String deviceLanguage() {
        String deviceLanguage = Locale.getDefault().getDisplayLanguage();
        Log.i(TAG, "deviceLanguage: " + deviceLanguage);
        return deviceLanguage;
    }

    public static void closeKeyboard(Activity activity) {
        if (activity != null) {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }

    public static String deviceToken(Context context) {
        FirebaseInstanceId
                .getInstance()
                .getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        token = null;
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }
                    // Get new Instance ID token
                    if (task.getResult() != null) {
                        token = task.getResult().getToken();
                        SharedPreferencesHelper.putKey(context, MonAmieEnum.TOKEN_FCM.getValue(),token);
                        Log.i(TAG, "deviceToken: Successfully " + token);
                    } else {
                        token = null;
                        Log.i(TAG, "deviceToken: FireBase task result equals NULL");
                    }
                });
        return token;
    }

}
