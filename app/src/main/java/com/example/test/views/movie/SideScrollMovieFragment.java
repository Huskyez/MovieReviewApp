package com.example.test.views.movie;

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

import com.example.test.R;
import com.example.test.adapter.MoviesRecyclerViewAdapter;
import com.example.test.model.movie.AnticipatedMovie;
import com.example.test.model.movie.RecommendedMovie;
import com.example.test.model.movie.Movie;
import com.example.test.model.movie.TrendingMovie;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ViewModelFactory;
import com.example.test.views.movie.SideScrollMovieFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SideScrollMovieFragment extends Fragment {

    private String categoryTitle;

    public SideScrollMovieFragment() {

    }

    public static SideScrollMovieFragment newInstance(String title) {
        SideScrollMovieFragment sideScrollMovieFragment = new SideScrollMovieFragment();
        sideScrollMovieFragment.categoryTitle = title;
        return sideScrollMovieFragment;
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

    private List<Movie> getList(String category, MovieViewModel movieViewModel) {
        List<Movie> movieList = new ArrayList<>();

        if (category.equals("Recommended")) {
            movieViewModel.updateRecommendedMovies("weekly");
            movieList = movieViewModel.getRecommendedMovies().getValue().stream().map(RecommendedMovie::getMovie).collect(Collectors.toList());
            return movieList;
        }

        if (category.equals("Popular")) {
            movieViewModel.updatePopularMovies();
            movieList = movieViewModel.getPopularMovies().getValue();
            return movieList;
        }
        if (category.equals("Trending")) {
            movieViewModel.updateTrendingMovies();
            movieList = movieViewModel.getTrendingMovies().getValue().stream().map(TrendingMovie::getMovie).collect(Collectors.toList());
            return movieList;
        }
        if (category.equals("Anticipated")) {
            movieViewModel.updateAnticipatedMovies();
            movieList = movieViewModel.getAnticipatedMovies().getValue().stream().map(AnticipatedMovie::getMovie).collect(Collectors.toList());
            return movieList;
        }

        return movieList;
    }

    private void setListener(String category, MovieViewModel movieViewModel, MoviesRecyclerViewAdapter adapter) {

        if (category.equals("Recommended")) {
            movieViewModel.getRecommendedMovies().observe(this.getViewLifecycleOwner(), movies -> {
                adapter.setMediaObjects(movies.stream().map(RecommendedMovie::getMovie).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            });
            return;
        }

        if (category.equals("Popular")) {
            movieViewModel.getPopularMovies().observe(this.getViewLifecycleOwner(), movies -> {
                adapter.setMediaObjects(movies);
                adapter.notifyDataSetChanged();
            });
            return;
        }
        if (category.equals("Trending")) {
            movieViewModel.getTrendingMovies().observe(this.getViewLifecycleOwner(), movies -> {
                adapter.setMediaObjects(movies.stream().map(TrendingMovie::getMovie).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            });
            return;
        }
        if (category.equals("Anticipated")) {
            movieViewModel.getAnticipatedMovies().observe(this.getViewLifecycleOwner(), movies -> {
                adapter.setMediaObjects(movies.stream().map(AnticipatedMovie::getMovie).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            });
            return;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView textView = view.findViewById(R.id.category_title);
        textView.setText(categoryTitle);

        MovieViewModel movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
        MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(this.getContext(), getList(categoryTitle, movieViewModel));
        setListener(categoryTitle, movieViewModel, adapter);
        movieViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }

}
