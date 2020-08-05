package com.huskyez.test.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.huskyez.test.R;
import com.huskyez.test.viewmodel.ProfileViewModel;
import com.huskyez.test.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        BarChart barChart = view.findViewById(R.id.bar_chart);

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
                watchTime.setText("Total Watchtime: " + totalWatchTime + " minutes");
//                ratings.setText(stats.getRatings().getDistribution().get("10") + "");

                Map<String, Integer> distribution = stats.getRatings().getDistribution();
                List<BarEntry> barEntries = new ArrayList<>();
                for (String key : distribution.keySet().stream().sorted().collect(Collectors.toList())) {
                    barEntries.add(new BarEntry(Integer.parseInt(key), distribution.get(key)));
                }
                BarDataSet barDataSet = new BarDataSet(barEntries, "Rating Distribution");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);

                BarData barData = new BarData(barDataSet);
                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.animateY(2000);
            }
        });


    }
}