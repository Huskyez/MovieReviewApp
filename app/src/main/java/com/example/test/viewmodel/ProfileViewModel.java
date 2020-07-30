package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.model.stats.UserStats;
import com.example.test.model.user.UserSettings;
import com.example.test.repo.UserRepository;

public class ProfileViewModel extends ViewModel {

    private UserRepository userRepository;

    public ProfileViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUserSettings(String access_token) {
        userRepository.searchUserSettings(access_token);
    }

    public LiveData<UserSettings> getUserSettings() {
        return userRepository.getUserSettings();
    }

    public void updateUserStats(String id) {
        userRepository.searchUserStats(id);
    }

    public LiveData<UserStats> getUserStats() {
        return userRepository.getUserStats();
    }

}