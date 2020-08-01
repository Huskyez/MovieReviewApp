package com.example.test.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.model.stats.UserStats;
import com.example.test.model.user.UserSettings;
import com.example.test.viewmodel.ProfileViewModel;
import com.example.test.viewmodel.ViewModelFactory;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;


    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profileViewModel = new ViewModelFactory().create(ProfileViewModel.class);

        String access_token = getActivity().getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null);
        Log.d("Token", access_token);
        profileViewModel.updateUserSettings(access_token);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView avatar = view.findViewById(R.id.avatar);
        TextView username = view.findViewById(R.id.tv_username);
        TextView watchTime = view.findViewById(R.id.tv_watch_time);
        TextView ratings = view.findViewById(R.id.tv_ratings);

        profileViewModel.getUserSettings().observe(getViewLifecycleOwner(), settings -> {
            if (settings != null) {
                Log.d("SLUG", settings.getUser().getIds().getSlug());
                profileViewModel.updateUserStats(settings.getUser().getIds().getSlug());
                username.setText(settings.getUser().getUsername());
                Glide.with(view).asBitmap().load(settings.getUser().getImages().getAvatar().getFull()).into(avatar);

            }
        });

        profileViewModel.getUserStats().observe(getViewLifecycleOwner(), stats -> {
            if (stats != null) {
                int totalWatchTime = stats.getMovies().getMinutes() + stats.getEpisodes().getMinutes();
                watchTime.setText(totalWatchTime + "");
                ratings.setText(stats.getRatings().getDistribution().get("10") + "");
            }
        });


    }
}