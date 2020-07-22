package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Pair;

import com.example.test.R;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;
import com.example.test.viewmodel.RecyclerViewAdapter;
import com.example.test.model.Image;
import com.example.test.model.TrendingMovie;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ViewModelFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private MovieViewModel movieViewModel;

    private MovieRepository movieRepository;
    private ImageRepository imageRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Hope this works
        movieViewModel = new ViewModelFactory().create(MovieViewModel.class);

        movieRepository = new MovieRepository();
        imageRepository = new ImageRepository();

        adapter = new RecyclerViewAdapter(this, imageRepository, movieRepository);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        movieViewModel.updateTrendingMovies();

        movieRepository.getTrendingMovies().observe(this, trendingMovies -> {
//            adapter.setMovies(trendingMovies.stream().map(TrendingMovie::getMovie).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        });
        imageRepository.getImageCache().observe(this, map -> {
//            adapter.setImageMap(map);
            adapter.notifyDataSetChanged();
        });

        movieRepository.searchTrendingMovies();

        SwipeRefreshLayout layout = findViewById(R.id.trending_swipe_layout);
        layout.setOnRefreshListener(() -> {
            movieRepository.searchTrendingMovies();
            layout.setRefreshing(false);
        });



//        movieViewModel.getTrendingMoviesData().observe(this, pairs -> {
////            fillAdapter(pairs);
//            adapter.notifyDataSetChanged();
//        });



    }

//    private void fillAdapter(List<Pair<TrendingMovie, Image>> movies) {
//
//        List<String> titles = new ArrayList<>();
//        List<String> imageUris = new ArrayList<>();
//        movies.forEach(p -> {
//            titles.add(p.first.getMovie().getTitle());
//            imageUris.add("https://image.tmdb.org/t/p/w500" + p.second.getPath());
//        });
//        adapter.setTitles(titles);
//        adapter.setImageURIs(imageUris);
//    }
}