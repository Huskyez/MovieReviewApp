package com.huskyez.movieapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huskyez.movieapp.api.ApiService;
import com.huskyez.movieapp.api.ApiServiceFactory;
import com.huskyez.movieapp.model.stats.UserStats;
import com.huskyez.movieapp.model.user.UserSettings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService apiService;

    private static UserRepository instance = null;

    private UserSettings userSettings;
    private MutableLiveData<UserSettings> userSettingsMutableLiveData = new MutableLiveData<>();

    private UserStats userStats;
    private MutableLiveData<UserStats> userStatsMutableLiveData = new MutableLiveData<>();

    private UserRepository() {
        apiService = ApiServiceFactory.getService();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
            return instance;
        }

        return  instance;
    }

    public void searchUserSettings(String access_token) {


        String toSend = "Bearer " + access_token;
        Call<UserSettings> call = apiService.getUserSettings(toSend);

        call.enqueue(new Callback<UserSettings>() {
            @Override
            public void onResponse(Call<UserSettings> call, Response<UserSettings> response) {
                if (response.isSuccessful()) {
                    userSettings = response.body();
                    userSettingsMutableLiveData.setValue(userSettings);
                } else {
                    UserSettings settings = new UserSettings();
                    settings.setUser(null);
                    userSettingsMutableLiveData.setValue(settings);
                }
            }

            @Override
            public void onFailure(Call<UserSettings> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<UserSettings> getUserSettings() {
        return userSettingsMutableLiveData;
    }

    public void searchUserStats(String id) {

        Call<UserStats> call = apiService.getUserStats(id);

        call.enqueue(new Callback<UserStats>() {
            @Override
            public void onResponse(Call<UserStats> call, Response<UserStats> response) {
                if (response.isSuccessful()) {
                    userStats = response.body();
                    userStatsMutableLiveData.setValue(userStats);
                }
            }

            @Override
            public void onFailure(Call<UserStats> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<UserStats> getUserStats() {
        return userStatsMutableLiveData;
    }

    public void clearData() {
        userSettings = null;
        userStats = null;
        userSettingsMutableLiveData.setValue(null);
        userStatsMutableLiveData.setValue(null);
    }

}
