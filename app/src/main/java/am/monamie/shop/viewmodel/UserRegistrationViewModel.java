package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import am.monamie.shop.model.network.get.UserRegistrationResponse;
import am.monamie.shop.model.network.post.UserRegistration;
import am.monamie.shop.viewmodel.repository.UserRegistrationRepository;

public class UserRegistrationViewModel extends AndroidViewModel {
    private static final String TAG = UserRegistrationViewModel.class.getName();
    private UserRegistrationRepository repository;
    private LiveData<UserRegistrationResponse> liveData;

    public UserRegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public void registerUser(UserRegistration userRegistration) {
        repository = new UserRegistrationRepository();
        liveData = repository.initRequest(userRegistration);
    }

    public LiveData<UserRegistrationResponse> getLiveData() {
        return liveData;
    }
}
