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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularMoviesFragment extends Fragment {


    public PopularMoviesFragment() {
        // Required empty public constructor
    }

    public static PopularMoviesFragment newInstance() {
        PopularMoviesFragment fragment = new PopularMoviesFragment();
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
        return inflater.inflate(R.layout.layout_horizontal_scroll, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView textView = view.findViewById(R.id.category_title);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        MovieViewModel movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
        MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(this.getContext(), movieViewModel.getPopularMovies().getValue());

        textView.setText(getString(R.string.popular));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        movieViewModel.updatePopularMovies();
        movieViewModel.getPopularMovies().observe(this.getViewLifecycleOwner(), movies -> {
            adapter.setMediaObjects(movieViewModel.getPopularMovies().getValue());
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());
    }
}