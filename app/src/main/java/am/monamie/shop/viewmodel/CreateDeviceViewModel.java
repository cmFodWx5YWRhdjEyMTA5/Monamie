package am.monamie.shop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import am.monamie.shop.model.network.get.CreateDeviceResponse;
import am.monamie.shop.model.network.post.CreateDevice;
import am.monamie.shop.viewmodel.repository.CreateDeviceRepository;

public class CreateDeviceViewModel extends AndroidViewModel {
    private static final String TAG = CreateDeviceViewModel.class.getName();
    private LiveData<CreateDeviceResponse> liveData;
    private CreateDeviceRepository repositoty;

    public CreateDeviceViewModel(@NonNull Application application) {
        super(application);
    }

    public void createDevice(CreateDevice createDevice) {
        repositoty = new CreateDeviceRepository();
        liveData = repositoty.initRequest(createDevice);
    }

    public LiveData<CreateDeviceResponse> getLiveData() {
        return liveData;
    }
}
