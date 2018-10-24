package am.monamie.shop.view.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import am.monamie.shop.AppApplication;
import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.view.webservice.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserLoginRepository {
    private static final String TAG = UserLoginRepository.class.getName();
    private ApiService apiService;

    public LiveData<UserLoginResponse> initRequest(UserLogin userLogin) {
        final MutableLiveData<UserLoginResponse> data = new MutableLiveData<>();
        AppApplication.appApplication.getNetworkService().loginUser(userLogin).enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserLoginResponse> call, @NonNull Response<UserLoginResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UserLoginResponse> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
