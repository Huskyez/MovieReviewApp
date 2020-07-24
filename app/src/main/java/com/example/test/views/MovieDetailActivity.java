package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.model.Image;
import com.example.test.viewmodel.MovieDetailsViewModel;
import com.example.test.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MovieDetailActivity extends AppCompatActivity {


    //TODO: make a nicer layout

    private MovieDetailsViewModel movieDetailsViewModel;

    private ImageView imageView;

    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvTagline;
    private TextView tvSummary;
    private TextView tvRuntime;
    private TextView tvRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        movieDetailsViewModel = new ViewModelFactory().create(MovieDetailsViewModel.class);

        Integer tmdb_id = getIntent().getIntExtra("tmdb_id", 0);
        String slug_id = getIntent().getStringExtra("slug_id");

        imageView = findViewById(R.id.image);
        tvTitle = findViewById(R.id.tv_movie_title);
        tvYear = findViewById(R.id.tv_year);
        tvTagline = findViewById(R.id.tv_tagline);
        tvSummary = findViewById(R.id.tv_summary);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvRating = findViewById(R.id.tv_rating);

        movieDetailsViewModel.searchMovieDetails(slug_id);

        movieDetailsViewModel.getMovieDetails().observe(this, movieDetails -> {
            if (movieDetails == null) {
                return;
            }
//            String dateFormat = "yyyy - mm - dd";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

            tvTitle.setText(movieDetails.getTitle());
            tvYear.setText(movieDetails.getReleased());
            tvTagline.setText(movieDetails.getTagline());
            tvSummary.setText(movieDetails.getOverview());
            tvRuntime.setText(movieDetails.getRuntime().toString());
            tvRating.setText(movieDetails.getRating().toString());
        });

        movieDetailsViewModel.getImageCache().observe(this, map -> {

            String imageURI;
            Image image = movieDetailsViewModel.getImage(tmdb_id);
            if (image != null) {
                imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
            } else {
                movieDetailsViewModel.searchImage(tmdb_id, "movie");
                imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
            }

            Glide.with(getBaseContext()).asBitmap().load(imageURI).into(imageView);
        });

    }
}