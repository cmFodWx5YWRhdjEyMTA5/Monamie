package am.monamie.shop.viewmodel.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import am.monamie.shop.AppApplication;
import am.monamie.shop.model.network.get.CreateDeviceResponse;
import am.monamie.shop.model.network.post.CreateDevice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDeviceRepository {
    private static final String TAG = CreateDeviceRepository.class.getName();

    public LiveData<CreateDeviceResponse> initRequest(CreateDevice createDevice) {
        final MutableLiveData<CreateDeviceResponse> data = new MutableLiveData<>();
        AppApplication
                .appApplication
                .getNetworkService()
                .createDevice(createDevice)
                .enqueue(new Callback<CreateDeviceResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CreateDeviceResponse> call, @NonNull Response<CreateDeviceResponse> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<CreateDeviceResponse> call, @NonNull Throwable t) {
                        Log.i(TAG, "onFailure: " + t.getMessage());
                    }
                });
        return data;
    }
}
