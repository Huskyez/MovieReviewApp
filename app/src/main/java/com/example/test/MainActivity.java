package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.model.Image;
import com.example.test.model.Movie;
import com.example.test.model.TrendingMovie;
import com.example.test.repo.MovieRepository;
import com.example.test.viewmodel.MovieViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Hope this works
        movieViewModel = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                MovieRepository movieRepository = new MovieRepository();
                return (T) new MovieViewModel(movieRepository);
            }
        }.create(MovieViewModel.class);


        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieViewModel.updateTrendingMovies();

        SwipeRefreshLayout layout = findViewById(R.id.trending_swipe_layout);
        layout.setOnRefreshListener(() -> {
            movieViewModel.updateTrendingMovies();
            layout.setRefreshing(false);
        });

        movieViewModel.getTrendingMoviesData().observe(this, pairs -> {
            fillAdapter(pairs);
            adapter.notifyDataSetChanged();
        });

    }


    private void fillAdapter(List<Pair<TrendingMovie, Image>> movies) {

        List<String> titles = new ArrayList<>();
        List<String> imageUris = new ArrayList<>();
        movies.forEach(p -> {
            titles.add(p.first.getMovie().getTitle());
            imageUris.add("https://image.tmdb.org/t/p/w500" + p.second.getPath());
        });
        adapter.setTitles(titles);
        adapter.setImageURIs(imageUris);
    }
}