package am.monamie.shop.view.webservice;

import am.monamie.shop.model.get.ProductCategoriesResponse;
import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.get.UserRegistrationResponse;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.model.post.UserRegistration;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("last_url")
    Call<ProductCategoriesResponse> getMonAmieProducts();

    @POST("auth/login")
    Call<UserLoginResponse> loginUser(@Body UserLogin userLogin);

    @POST("auth/register")
    Call<UserRegistrationResponse> registerUser(@Body UserRegistration userRegistration);
}
