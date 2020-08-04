package com.huskyez.test.views.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huskyez.test.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviePageFragment extends Fragment {


    private List<Fragment> fragmentList;

    public MoviePageFragment() {
        // Required empty public constructor
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }


    public static MoviePageFragment newInstance() {
        MoviePageFragment fragment = new MoviePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        SideScrollMovieFragment recommendedFragment = SideScrollMovieFragment.newInstance("Recommended");
        SideScrollMovieFragment popularFragment = SideScrollMovieFragment.newInstance("Popular");
        SideScrollMovieFragment trendingFragment = SideScrollMovieFragment.newInstance("Trending");
        SideScrollMovieFragment anticipatedFragment = SideScrollMovieFragment.newInstance("Anticipated");


        transaction.add(R.id.fragment_container, recommendedFragment);
        transaction.add(R.id.fragment_container, popularFragment);
        transaction.add(R.id.fragment_container, trendingFragment);
        transaction.add(R.id.fragment_container, anticipatedFragment);
        transaction.commit();
    }

}