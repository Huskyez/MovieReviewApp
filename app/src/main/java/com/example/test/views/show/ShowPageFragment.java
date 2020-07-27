package com.example.test.views.show;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.test.R;

import java.util.List;

public class ShowPageFragment extends Fragment {

    public ShowPageFragment() {
        // Required empty public constructor
    }


    public static ShowPageFragment newInstance() {
        ShowPageFragment fragment = new ShowPageFragment();
//        fragment.setFragmentList(fragments);
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
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

        //TODO: pass a list of fragments instead of having them hardcoded
        //TODO: (another one) these fragments should have the same layout
        // -> refactor it (same adapter)

        SideScrollShowFragment popularFragment = SideScrollShowFragment.newInstance("Popular");
        SideScrollShowFragment trendingFragment = SideScrollShowFragment.newInstance("Trending");
        SideScrollShowFragment anticipatedFragment = SideScrollShowFragment.newInstance("Anticipated");

        transaction.add(R.id.fragment_container, popularFragment);
        transaction.add(R.id.fragment_container, trendingFragment);
        transaction.add(R.id.fragment_container, anticipatedFragment);
        transaction.commit();
    }

}
