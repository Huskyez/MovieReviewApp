package com.example.test.views.show;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.model.Image;
import com.example.test.viewmodel.ShowDetailsViewModel;
import com.example.test.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetailActivity extends AppCompatActivity {

    private ShowDetailsViewModel showDetailsViewModel;

    private ImageView imageView;

    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvSummary;
    private TextView tvStatus;
    private TextView tvEpisodes;
    private TextView tvRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        Integer tmdb_id = getIntent().getIntExtra("tmdb_id", 0);
        String slug_id = getIntent().getStringExtra("slug_id");

        imageView = findViewById(R.id.poster);
        tvTitle = findViewById(R.id.tv_title);
        tvYear = findViewById(R.id.tv_year);
        tvSummary = findViewById(R.id.tv_summary);
        tvStatus = findViewById(R.id.tv_status);
        tvEpisodes = findViewById(R.id.tv_nr_episodes);
        tvRating = findViewById(R.id.tv_rating);

        showDetailsViewModel = new ViewModelFactory().create(ShowDetailsViewModel.class);

        showDetailsViewModel.searchShowDetails(slug_id);

        showDetailsViewModel.getShowDetails().observe(this, showDetails -> {
            if (showDetails == null) {
                return;
            }
//            String dateFormat = "yyyy - mm - dd";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

            tvTitle.setText(showDetails.getTitle());
            tvYear.setText(showDetails.getFirstAired());
            tvSummary.setText(showDetails.getOverview());
            tvStatus.setText(showDetails.getStatus());
            tvEpisodes.setText(showDetails.getAired_episodes().toString());
            tvRating.setText(showDetails.getRating().toString());
        });

        showDetailsViewModel.getImageCache().observe(this, map -> {

            String imageURI;
            Image image = showDetailsViewModel.getImage(tmdb_id);
            if (image != null) {
                imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
            } else {
                showDetailsViewModel.searchImage(tmdb_id, "show");
                imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
            }

            Glide.with(getBaseContext()).asBitmap().load(imageURI).into(imageView);
        });
    }
}