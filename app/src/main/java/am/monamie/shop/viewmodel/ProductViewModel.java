package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.Objects;

import am.monamie.shop.AppApplication;
import am.monamie.shop.model.get.ProductCategoriesResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends AndroidViewModel {
    private MutableLiveData<ProductCategoriesResponse> mutableLiveData = new MutableLiveData<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        getJsonData();
    }

    private void getJsonData() {
        AppApplication
                .appApplication
                .getNetworkService()
                .getMonamieProducts()
                .enqueue(new Callback<ProductCategoriesResponse>() {
                    @Override
                    public void onResponse(Call<ProductCategoriesResponse> call, Response<ProductCategoriesResponse> response) {
                        Observable.just(Objects.requireNonNull(response.body()))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(subscribeData());
                    }

                    @Override
                    public void onFailure(Call<ProductCategoriesResponse> call, Throwable t) {

                    }
                });
    }

    private Observer<ProductCategoriesResponse> subscribeData() {
        return new Observer<ProductCategoriesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductCategoriesResponse productCategoriesResponse) {
                mutableLiveData.setValue(productCategoriesResponse);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public MutableLiveData<ProductCategoriesResponse> getMutableLiveData() {
        return mutableLiveData;
    }

}
