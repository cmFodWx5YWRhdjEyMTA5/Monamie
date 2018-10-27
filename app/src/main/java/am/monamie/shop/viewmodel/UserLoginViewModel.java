package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.viewmodel.repository.UserLoginRepository;

public class UserLoginViewModel extends AndroidViewModel {
    private static final String TAG = UserLoginViewModel.class.getName();
    private LiveData<UserLoginResponse> liveData;
    private UserLoginRepository repository;

    public UserLoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginUser(UserLogin userLogin) {
        repository = new UserLoginRepository();
        liveData = repository.initRequest(userLogin);
    }

    public LiveData<UserLoginResponse> getLiveData() {
        return liveData;
    }
}
