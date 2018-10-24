package am.monamie.shop.view.webservice;

import am.monamie.shop.model.get.ProductCategoriesResponse;
import am.monamie.shop.model.get.UserRegistrationResponse;
import am.monamie.shop.model.post.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("last_url")
    Call<ProductCategoriesResponse> getMonamieProducts();

    @POST("auth/login")
    Call<UserRegistrationResponse> loginUser(@Body User user);
}
