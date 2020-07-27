package com.example.test.views.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.adapter.TrendingMoviesRecyclerViewAdapter;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ViewModelFactory;


public class TrendingMoviesActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TrendingMoviesRecyclerViewAdapter adapter;

    private MovieViewModel movieViewModel;

//    private MovieRepository movieRepository;
//    private ImageRepository imageRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        recyclerView = findViewById(R.id.recyclerView);

        // Hope this works
        movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
//        movieRepository = new MovieRepository();
//        imageRepository = new ImageRepository();

        adapter = new TrendingMoviesRecyclerViewAdapter(this, movieViewModel);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        SwipeRefreshLayout layout = findViewById(R.id.trending_swipe_layout);
        layout.setOnRefreshListener(() -> movieViewModel.updateTrendingMovies());



        movieViewModel.getTrendingMovies().observe(this, trendingMovies -> {
            layout.setRefreshing(false);
//            adapter.setMovies(trendingMovies.stream().map(TrendingMovie::getMovie).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this, map -> {
//            adapter.setImageMap(map);
            adapter.notifyDataSetChanged();
        });

        movieViewModel.updateTrendingMovies();

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