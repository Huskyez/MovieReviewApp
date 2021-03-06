package com.huskyez.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huskyez.movieapp.model.stats.UserStats;
import com.huskyez.movieapp.model.user.UserSettings;
import com.huskyez.movieapp.repo.UserRepository;

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
