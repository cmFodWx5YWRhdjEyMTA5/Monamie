package am.monamie.shop.view.webservice;

import am.monamie.shop.model.ProductCategories;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("last_url")
    Call<ProductCategories> getMonamieProducts();
}
