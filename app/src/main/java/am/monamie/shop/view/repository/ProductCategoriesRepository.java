package am.monamie.shop.view.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import am.monamie.shop.AppApplication;
import am.monamie.shop.model.get.ProductCategoriesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoriesRepository {
    private static final String TAG = ProductCategoriesRepository.class.getName();

    public LiveData<ProductCategoriesResponse> initRequest() {
        final MutableLiveData<ProductCategoriesResponse> data = new MutableLiveData<>();
        AppApplication
                .appApplication
                .getNetworkServiceWithToken()
                .getMonAmieProducts()
                .enqueue(new Callback<ProductCategoriesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductCategoriesResponse> call, @NonNull Response<ProductCategoriesResponse> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductCategoriesResponse> call, @NonNull Throwable t) {
                        Log.i(TAG, "onFailure: " + t.getMessage());
                    }
                });
        return data;
    }
}
