package am.monamie.shop.view.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import am.monamie.shop.AppApplication;
import am.monamie.shop.model.get.UserRegistrationResponse;
import am.monamie.shop.model.post.User;
import am.monamie.shop.view.webservice.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginRepository {
    private static final String TAG = UserLoginRepository.class.getName();
    private ApiService apiService;

    public LiveData<UserRegistrationResponse> init(User user) {
        final MutableLiveData<UserRegistrationResponse> data = new MutableLiveData<>();
        AppApplication.appApplication.getNetworkService().loginUser(user).enqueue(new Callback<UserRegistrationResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserRegistrationResponse> call, @NonNull Response<UserRegistrationResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UserRegistrationResponse> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
