package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.Objects;

import am.monamie.shop.AppApplication;
import am.monamie.shop.model.ProductCategories;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends AndroidViewModel {
    private MutableLiveData<ProductCategories> mutableLiveData = new MutableLiveData<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        getJsonData();
    }

    private void getJsonData() {
        AppApplication
                .appApplication
                .getNetworkService()
                .getMonamieProducts()
                .enqueue(new Callback<ProductCategories>() {
                    @Override
                    public void onResponse(Call<ProductCategories> call, Response<ProductCategories> response) {
                        Observable.just(Objects.requireNonNull(response.body()))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(subscribeData());
                    }

                    @Override
                    public void onFailure(Call<ProductCategories> call, Throwable t) {

                    }
                });
    }

    private Observer<ProductCategories> subscribeData() {
        return new Observer<ProductCategories>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductCategories productCategories) {
                mutableLiveData.setValue(productCategories);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public MutableLiveData<ProductCategories> getMutableLiveData() {
        return mutableLiveData;
    }

}
