package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.Objects;

import am.monamie.shop.AppApplication;
import am.monamie.shop.model.get.ProductCategoriesResponse;
import am.monamie.shop.view.repository.ProductCategoriesRepository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoriesViewModel extends AndroidViewModel {
    private static final String TAG = ProductCategoriesViewModel.class.getName();
    private ProductCategoriesRepository repository;
    private LiveData<ProductCategoriesResponse> liveData;

    public ProductCategoriesViewModel(@NonNull Application application) {
        super(application);
        productCategories();
    }

    private void productCategories() {
        repository = new ProductCategoriesRepository();
        liveData = repository.initRequest();
    }

    public LiveData<ProductCategoriesResponse> getLiveData() {
        return liveData;
    }
}
