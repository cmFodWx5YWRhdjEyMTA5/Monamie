package am.monamie.shop.viewmodel.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import am.monamie.shop.AppApplication;
import am.monamie.shop.model.get.UserRegistrationResponse;
import am.monamie.shop.model.post.UserRegistration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserRegistrationRepository {
    private static final String TAG = UserRegistrationRepository.class.getName();

    public LiveData<UserRegistrationResponse> initRequest(UserRegistration userRegistration) {
        final MutableLiveData<UserRegistrationResponse> data = new MutableLiveData<>();
        AppApplication
                .appApplication
                .getNetworkService()
                .registerUser(userRegistration)
                .enqueue(new Callback<UserRegistrationResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserRegistrationResponse> call, @NonNull Response<UserRegistrationResponse> response) {
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserRegistrationResponse> call, @NonNull Throwable t) {
                        Log.i(TAG, "onFailure: Response Null" + t.getMessage());
                    }
                });
        return data;
    }
}
