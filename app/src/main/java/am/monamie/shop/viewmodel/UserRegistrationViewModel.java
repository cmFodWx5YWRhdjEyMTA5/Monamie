package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

public class UserRegistrationViewModel extends AndroidViewModel {
    private static final String TAG = UserRegistrationViewModel.class.getName();
    public UserRegistrationViewModel(@NonNull Application application) {
        super(application);
    }
}
