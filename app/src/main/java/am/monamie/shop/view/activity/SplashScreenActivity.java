package am.monamie.shop.view.activity;


import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import am.monamie.shop.R;
import am.monamie.shop.model.get.CreateDeviceResponse;
import am.monamie.shop.model.post.CreateDevice;
import am.monamie.shop.view.constants.MonAmieEnum;
import am.monamie.shop.view.helper.SharedPreferencesHelper;
import am.monamie.shop.view.util.DeviceUtils;
import am.monamie.shop.viewmodel.CreateDeviceViewModel;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = SplashScreenActivity.class.getName();
    // Views
    private AlertDialog alertDialog;
    // Object
    private Activity activity = SplashScreenActivity.this;
    private Context context = SplashScreenActivity.this;
    private Handler handler;
    private CreateDevice createDevice;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && window != null) {
            window.setStatusBarColor(getResources().getColor(R.color.window));
            window.setNavigationBarColor(getResources().getColor(R.color.navigation_bar));
        }
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void checkNetworkConnection() {
        if (!DeviceUtils.isNetworkConnectionAvailable(context)) {
            showNetworkDialog();
        } else {
            // Created View Model for Device
            String deviceId = DeviceUtils.deviceId(this);
            String deviceToken = SharedPreferencesHelper.getKey(this, MonAmieEnum.TOKEN_FCM.getValue());
            createDevice = new CreateDevice(deviceId, deviceToken, MonAmieEnum.DEVICE_TYPE.getValue());
            CreateDeviceViewModel viewModel = ViewModelProviders.of(this).get(CreateDeviceViewModel.class);
            viewModel.createDevice(createDevice);
            final Observer<CreateDeviceResponse> nameObserve = createDeviceResponse -> {
                if (createDeviceResponse != null) {
                    // FIXME: need check :if create device equals true -> goGeneralScreen from handler
                    Log.i(TAG, "checkNetworkConnection: ");
                }
            };
            viewModel.getLiveData().observe(this, nameObserve);


            handler.postDelayed(() -> {
                Log.i(TAG, "checkNetworkConnection: ");
                goGeneralScreen(activity);
            }, 3000);

        }
    }

    private void goGeneralScreen(Activity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        activity.finish();
    }

    private void showNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.connect_to_network))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.connect_to_wifi), (dialog, id) -> startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton(getString(R.string.quit), (dialog, id) -> finish());
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

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
