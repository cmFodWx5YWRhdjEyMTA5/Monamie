package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import am.monamie.shop.model.network.get.ProductCategoriesResponse;
import am.monamie.shop.viewmodel.repository.ProductCategoriesRepository;

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
