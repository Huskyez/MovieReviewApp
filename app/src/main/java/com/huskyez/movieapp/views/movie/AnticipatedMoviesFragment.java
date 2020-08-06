package com.huskyez.movieapp.views.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huskyez.movieapp.R;
import com.huskyez.movieapp.adapter.MoviesRecyclerViewAdapter;
import com.huskyez.movieapp.model.movie.AnticipatedMovie;
import com.huskyez.movieapp.viewmodel.MovieViewModel;
import com.huskyez.movieapp.viewmodel.ViewModelFactory;

import java.util.stream.Collectors;

public class AnticipatedMoviesFragment extends Fragment {

    public AnticipatedMoviesFragment() {
        // Required empty public constructor
    }

    public static AnticipatedMoviesFragment newInstance() {
        AnticipatedMoviesFragment fragment = new AnticipatedMoviesFragment();
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

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView textView = view.findViewById(R.id.category_title);

        MovieViewModel movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
        MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(this.getContext(), movieViewModel.getAnticipatedMovies().getValue().stream().map(AnticipatedMovie::getMovie).collect(Collectors.toList()));

        textView.setText("Anticipated");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        movieViewModel.updateAnticipatedMovies();
        movieViewModel.getAnticipatedMovies().observe(this.getViewLifecycleOwner(), movies -> {
            adapter.setMediaObjects(movieViewModel.getAnticipatedMovies().getValue().stream().map(AnticipatedMovie::getMovie).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());
    }
}
