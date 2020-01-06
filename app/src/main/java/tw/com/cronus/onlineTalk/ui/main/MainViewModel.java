package tw.com.cronus.onlineTalk.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

class MainViewModel extends ViewModel {
    private final MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();

    public LiveData<FirebaseUser> getUser() {
        return userLiveData;
    }

//    public void setUser(FirebaseUser user) {
//        userLiveData = user;
//    }

}
