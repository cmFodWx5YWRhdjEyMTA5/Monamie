package am.monamie.shop.view.broadcastreceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class NetworkStateReceiver extends BroadcastReceiver {

    private List<NetworkStateListener> listeners;
    private boolean hadConnection;

    public NetworkStateReceiver() {
        listeners = new ArrayList<>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = null;
        if (connectivityManager != null) {
            activeNetInfo = connectivityManager.getActiveNetworkInfo();
        }
        boolean isNetworkAvailable = activeNetInfo != null && activeNetInfo.isConnected();
        if (isNetworkAvailable == hadConnection) {
            return;
        }

        List<NetworkStateListener> copyList;
        synchronized (this) {
            copyList = new ArrayList<>(listeners);
        }
        if (isNetworkAvailable) {
            for (NetworkStateListener listener : copyList) {
                listener.onNetworkAvailable(this);
            }
        } else {
            for (NetworkStateListener listener : copyList) {
                listener.onNetworkUnavailable(this);
            }
        }
        hadConnection = isNetworkAvailable;
    }

    public synchronized void addListener(NetworkStateListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public synchronized void removeListener(NetworkStateListener listener) {
        listeners.remove(listener);
    }

    public synchronized void removeAllListeners() {
        if (!listeners.isEmpty()) {
            listeners.clear();
        }
    }

    public interface NetworkStateListener {
        void onNetworkAvailable(NetworkStateReceiver receiver);

        void onNetworkUnavailable(NetworkStateReceiver receiver);
    }
}
