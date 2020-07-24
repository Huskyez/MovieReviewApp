package com.example.test.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.test.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviePageFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2; private List<Fragment> fragmentList;


    public MoviePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MoviePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviePageFragment newInstance() {
        MoviePageFragment fragment = new MoviePageFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
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
        TrendingMoviesFragment trendingMoviesFragment = TrendingMoviesFragment.newInstance();
        PopularMoviesFragment popularMoviesFragment = PopularMoviesFragment.newInstance();

        transaction.add(R.id.fragment_container, popularMoviesFragment);
        transaction.add(R.id.fragment_container, trendingMoviesFragment);
        transaction.commit();
    }

}