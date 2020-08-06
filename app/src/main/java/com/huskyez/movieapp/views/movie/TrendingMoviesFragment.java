package com.huskyez.movieapp.views.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huskyez.movieapp.R;
import com.huskyez.movieapp.adapter.MoviesRecyclerViewAdapter;
import com.huskyez.movieapp.viewmodel.MovieViewModel;
import com.huskyez.movieapp.viewmodel.ViewModelFactory;

import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrendingMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingMoviesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;

    public TrendingMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TrendingMoviesFragment.
     */
    public static TrendingMoviesFragment newInstance() {
        TrendingMoviesFragment fragment = new TrendingMoviesFragment();
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
        return inflater.inflate(R.layout.layout_horizontal_scroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView textView = view.findViewById(R.id.category_title);

        MovieViewModel movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
        MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(this.getContext(), movieViewModel.getTrendingMovies().getValue().stream().map(x -> x.getMovie()).collect(Collectors.toList()));

        textView.setText(getString(R.string.trending));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        movieViewModel.updateTrendingMovies();
        movieViewModel.getTrendingMovies().observe(this.getViewLifecycleOwner(), movies -> {
            adapter.setMediaObjects(movieViewModel.getTrendingMovies().getValue().stream().map(x -> x.getMovie()).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());
    }
}