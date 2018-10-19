package am.monamie.shop.view.activity;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import am.monamie.shop.R;
import am.monamie.shop.view.utils.DeviceUtils;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = SplashScreenActivity.class.getName();
    private Activity activity = SplashScreenActivity.this;
    private Context context = SplashScreenActivity.this;
    private AlertDialog alertDialog;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        Window window = getWindow();
        ActionBar actionBar = getSupportActionBar();
        configurationScreenWindow(window, actionBar);

        DeviceUtils.deviceId(context);
        checkNetworkConnection();
        DeviceUtils.deviceLanguage();

    }

    private void configurationScreenWindow(Window window, ActionBar actionBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && window != null && actionBar != null) {
            window.setStatusBarColor(getResources().getColor(R.color.window));
            window.setNavigationBarColor(getResources().getColor(R.color.navigation_bar));
            actionBar.hide();
        }
    }

    private void checkNetworkConnection() {
        if (!DeviceUtils.isNetworkConnectionAvailable(context)) {
            showNetworkDialog();
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goGeneralScreen(activity);
                }
            }, 3000);
        }
    }

    private void goGeneralScreen(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        activity.finish();
    }

    private void showNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.connect_to_network))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.connect_to_wifi), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton(getString(R.string.quit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        if (alertDialog == null) {
            alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkNetworkConnection();
    }
}
