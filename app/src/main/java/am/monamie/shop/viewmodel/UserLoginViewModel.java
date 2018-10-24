package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import am.monamie.shop.model.get.UserRegistrationResponse;
import am.monamie.shop.model.post.User;
import am.monamie.shop.view.repository.UserLoginRepository;

public class UserLoginViewModel extends AndroidViewModel {
    private LiveData<UserRegistrationResponse> liveData;
    private UserLoginRepository repository;

    public UserLoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginUser(User user) {
        repository = new UserLoginRepository();
        liveData = repository.init(user);
    }

    public LiveData<UserRegistrationResponse> getLiveData() {
        return liveData;
    }
}
