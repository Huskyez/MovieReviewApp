package com.huskyez.movieapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;



public class SectionsPageAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments;

    public SectionsPageAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        if (position == 0) {
//            return SearchMovieFragment.newInstance();
//        }
//        if (position == 1) {
//            return SearchShowFragment.newInstance();
//        }
//
//        return SearchMovieFragment.newInstance();
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
