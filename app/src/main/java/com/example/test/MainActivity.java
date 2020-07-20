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

import android.os.Bundle;
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

//    static final String apiKey = "6fec35152112a76320cff5806cd8e72c36f70dfb8ea4e9347d8cc0db4774a50b";

    private Button callButton;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callButton = findViewById(R.id.callButton);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Hope this works
        movieViewModel = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                MovieRepository movieRepository = new MovieRepository();
                return (T) new MovieViewModel(movieRepository);
            }
        }.create(MovieViewModel.class);

//        movieViewModel.updateTrendingMovies();

//        List<TrendingMovie> movies = movieViewModel.getTrendingMovies();

        movieViewModel.getTrendingMovies().observe(this, movies -> {
//            StringBuilder toShow = new StringBuilder();
//            for (TrendingMovie movie : movies) {
//                toShow.append(movie).append("\n");
//            }
//            text.setText(toShow);
            fillAdapter(movies);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Update!", Toast.LENGTH_SHORT).show();
        });

        callButton.setOnClickListener(view -> {
            movieViewModel.updateTrendingMovies();
//            movieViewModel.clearImages();
        });
    }


    // TODO: find a better way to update the view with images
    private void fillAdapter(List<TrendingMovie> movies) {

        List<Image> imageList = movieViewModel.getImagesTrending().getValue();

        List<String> titles = new ArrayList<>();

        movieViewModel.clearImages();

        movies.forEach(x -> {
            movieViewModel.searchImage(x.getMovie().getIds().getTmdb(), "movie");
//            imageURIs.add("https://image.tmdb.org/t/p/w500/aWhitEcqilcGwHoeJk9yLpMx45F.jpg");
            titles.add(x.getMovie().getTitle());
        });

        assert imageList != null;
        List<String> imageURIs = new ArrayList<>(imageList.stream().map(x -> "https://image.tmdb.org/t/p/w500" + x.getPath()).collect(Collectors.toList()));

        adapter.setImageURIs(imageURIs);
        adapter.setTitles(titles);
    }
}