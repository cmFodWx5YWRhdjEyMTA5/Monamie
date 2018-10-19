package am.monamie.shop.activity.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import am.monamie.shop.R;
import am.monamie.shop.activity.view.utils.DeviceUtils;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = SplashScreenActivity.class.getName();
    private Activity activity = SplashScreenActivity.this;
    private Context context = SplashScreenActivity.this;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        DeviceUtils.deviceId(context);
        checkNetworkConnection();
        DeviceUtils.deviceLanguage();

    }

    private void checkNetworkConnection() {
        if (!DeviceUtils.isNetworkConnectionAvailable(context)) {
            showNetworkDialog();
        }
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
        if(alertDialog == null){
            alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
