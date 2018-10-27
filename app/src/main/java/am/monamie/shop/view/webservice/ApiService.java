package am.monamie.shop.view.webservice;

import am.monamie.shop.model.get.CreateDeviceResponse;
import am.monamie.shop.model.get.ProductCategoriesResponse;
import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.get.UserRegistrationResponse;
import am.monamie.shop.model.post.CreateDevice;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.model.post.UserRegistration;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("product-categories?page=1")
    Call<ProductCategoriesResponse> getMonAmieProducts();

    @POST("auth/login")
    Call<UserLoginResponse> loginUser(@Body UserLogin userLogin);

    @POST("auth/register")
    Call<UserRegistrationResponse> registerUser(@Body UserRegistration userRegistration);

    @POST("devices")
    Call<CreateDeviceResponse> createDevice(@Body CreateDevice createDevice);
}
