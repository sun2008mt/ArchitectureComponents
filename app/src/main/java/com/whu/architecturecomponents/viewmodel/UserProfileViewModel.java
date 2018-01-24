package com.whu.architecturecomponents.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.whu.architecturecomponents.model.entity.User;
import com.whu.architecturecomponents.model.UserRepository;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/24.
 */

public class UserProfileViewModel extends ViewModel {
    private LiveData<User> user;
    private UserRepository userRepo;

    @Inject
    public UserProfileViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId) {
        if (this.user != null) {
            return;
        }

        this.user = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }
}
