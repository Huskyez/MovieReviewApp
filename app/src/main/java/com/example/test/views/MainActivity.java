package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.adapter.RecyclerViewAdapter;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ViewModelFactory;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private MovieViewModel movieViewModel;

//    private MovieRepository movieRepository;
//    private ImageRepository imageRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Hope this works
        movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
//        movieRepository = new MovieRepository();
//        imageRepository = new ImageRepository();

        adapter = new RecyclerViewAdapter(this, movieViewModel);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        movieViewModel.updateTrendingMovies();

        movieViewModel.getTrendingMovies().observe(this, trendingMovies -> {
//            adapter.setMovies(trendingMovies.stream().map(TrendingMovie::getMovie).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this, map -> {
//            adapter.setImageMap(map);
            adapter.notifyDataSetChanged();
        });

        movieViewModel.updateTrendingMovies();

        SwipeRefreshLayout layout = findViewById(R.id.trending_swipe_layout);
        layout.setOnRefreshListener(() -> {
            movieViewModel.updateTrendingMovies();
            layout.setRefreshing(false);
        });


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