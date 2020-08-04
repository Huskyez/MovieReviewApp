package com.huskyez.test.views.movie;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huskyez.test.R;
import com.huskyez.test.model.common.WatchlistItem;
import com.huskyez.test.model.image.Image;
import com.huskyez.test.model.movie.CollectionMovie;
import com.huskyez.test.model.movie.Movie;
import com.huskyez.test.model.movie.MovieDetails;
import com.huskyez.test.model.sync.CollectionPostBody;
import com.huskyez.test.model.sync.PostMediaObject;
import com.huskyez.test.model.sync.WatchlistPostBody;
import com.huskyez.test.repo.UserListsRepository;
import com.huskyez.test.viewmodel.MovieDetailsViewModel;
import com.huskyez.test.viewmodel.ViewModelFactory;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class MovieDetailActivity extends AppCompatActivity {


    private MovieDetailsViewModel movieDetailsViewModel;

    private String slug_id;
    private Integer tmdb_id;

    private MovieDetails movieDetails;

    private ImageView imageView;

    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvTagline;
    private TextView tvSummary;
    private TextView tvRuntime;
    private TextView tvRating;

    private FloatingActionButton addButton;
    private FloatingActionButton collectionButton;
    private FloatingActionButton watchlistButton;
    private FloatingActionButton watchedButton;

    private TextView tvCollection;
    private TextView tvWatchlist;
    private TextView tvWatched;

    private Animation fabClose;
    private Animation fabOpen;

    private boolean isOpen;

    TimeZone timeZone = TimeZone.getDefault();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

    private UserListsRepository userListsRepository;
    private List<CollectionMovie> collectionMovies;
    private List<WatchlistItem> watchlist;
    private int collectionPosition;
    private int watchlistPosition;

    private View.OnClickListener addToCollection;
    private View.OnClickListener removeFromCollection;
    private View.OnClickListener addToWatchlist;
    private View.OnClickListener removeFromWatchlist;

    private void closeFabs() {
        collectionButton.startAnimation(fabClose);
        watchlistButton.startAnimation(fabClose);
//                watchedButton.startAnimation(fabClose);
        tvCollection.setVisibility(View.INVISIBLE);
        tvWatchlist.setVisibility(View.INVISIBLE);
        isOpen = false;
//                tvWatched.setVisibility(View.INVISIBLE);
    }

    private void openFabs() {
        collectionButton.startAnimation(fabOpen);
        watchlistButton.startAnimation(fabOpen);
//                watchedButton.startAnimation(fabOpen);

        tvCollection.setVisibility(View.VISIBLE);
        tvWatchlist.setVisibility(View.VISIBLE);
        isOpen = true;
//                tvWatched.setVisibility(View.VISIBLE);
    }

    private void setFloatingActionButtons() {

        addButton = findViewById(R.id.add_button);
        collectionButton = findViewById(R.id.collection_button);
        watchlistButton = findViewById(R.id.watchlist_button);
        watchedButton = findViewById(R.id.watched_button);

        tvCollection = findViewById(R.id.tv_collection);
        tvWatchlist = findViewById(R.id.tv_watchlist);
        tvWatched = findViewById(R.id.tv_watched);

        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);

        collectionButton.setVisibility(View.INVISIBLE);
        watchlistButton.setVisibility(View.INVISIBLE);
        watchedButton.setVisibility(View.INVISIBLE);
        tvCollection.setVisibility(View.INVISIBLE);
        tvWatchlist.setVisibility(View.INVISIBLE);
        tvWatched.setVisibility(View.INVISIBLE);

        addButton.setOnClickListener(view -> {
            if (isOpen) {
                closeFabs();

            } else {
                openFabs();
            }
        });
    }

    private void setDetailPage() {

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
            this.movieDetails = movieDetails;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieDetailsViewModel = new ViewModelFactory().create(MovieDetailsViewModel.class);

        tmdb_id = getIntent().getIntExtra("tmdb_id", 0);
        slug_id = getIntent().getStringExtra("slug_id");

        setFloatingActionButtons();
        setDetailPage();

        dateFormat.setTimeZone(timeZone);

        //TODO: change listener according to situation
        userListsRepository = UserListsRepository.getInstance(getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null));

        collectionMovies = userListsRepository.getMovieCollection().getValue();
        watchlist = userListsRepository.getWatchlist().getValue().stream().filter(x -> x.getType().equals("movie")).collect(Collectors.toList());

        userListsRepository.getMovieCollection().observe(this, collection -> {
            collectionMovies = collection;
            collectionPosition = collectionMovies.stream().map(x -> x.getMovie().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        });
        userListsRepository.getWatchlist().observe(this, watchlist -> {
            this.watchlist = watchlist.stream().filter(x -> x.getType().equals("movie")).collect(Collectors.toList());
            watchlistPosition = this.watchlist.stream().map(x -> x.getMovie().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        });

        collectionPosition = collectionMovies.stream().map(x -> x.getMovie().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        watchlistPosition = watchlist.stream().map(x -> x.getMovie().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);

        addToCollection = view -> {
            String now = dateFormat.format(new Date());
            PostMediaObject toAdd = new PostMediaObject(now, movieDetails.getTitle(), movieDetails.getYear(), movieDetails.getIds());
            userListsRepository.addToCollection(new CollectionPostBody(new ArrayList<>(Collections.singletonList(toAdd)), null));
            collectionMovies.add(new CollectionMovie(now, now, new Movie(toAdd.title, toAdd.year, toAdd.ids)));
            tvCollection.setText("Remove from Collection");
            collectionButton.setOnClickListener(removeFromCollection);
            closeFabs();
        };

        removeFromCollection = view -> {
            CollectionMovie collectionMovie = collectionMovies.get(collectionPosition);
            PostMediaObject toAdd = new PostMediaObject(null, collectionMovie.getMovie().getTitle(), collectionMovie.getMovie().getYear(), collectionMovie.getMovie().getIds());
            userListsRepository.removeFromCollection(new CollectionPostBody(new ArrayList<>(Collections.singletonList(toAdd)), null));
            collectionMovies.remove(collectionPosition);
            tvCollection.setText("Add to Collection");
            collectionButton.setOnClickListener(addToCollection);
            closeFabs();
        };

        removeFromWatchlist = view -> {
            userListsRepository.removeFromWatchlist(new WatchlistPostBody(new ArrayList<>(Collections.singletonList(watchlist.get(watchlistPosition).getMovie())), null));
            watchlist.remove(watchlistPosition);
            tvWatchlist.setText("Add to Watchlist");
            watchlistButton.setOnClickListener(removeFromWatchlist);
            closeFabs();
        };

        addToWatchlist = view -> {
            Movie toAdd = new Movie(movieDetails.getIds(), movieDetails.getTitle());
            userListsRepository.addToWatchlist(new WatchlistPostBody(new ArrayList<>(Collections.singletonList(toAdd)), null));
            watchlist.add(new WatchlistItem(null, null, "movie", toAdd, null));
            tvWatchlist.setText("Remove from Watchlist");
            watchlistButton.setOnClickListener(addToWatchlist);
            closeFabs();
        };

//        UserListsRepository userListsRepository = UserListsRepository.getInstance(getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null));
//
//        List<CollectionMovie> collectionMovies = userListsRepository.getMovieCollection().getValue();
//        List<WatchlistItem>

        if (collectionPosition != -1) {
            tvCollection.setText("Remove from Collection");
            collectionButton.setOnClickListener(removeFromCollection);
        } else {
            tvCollection.setText("Add to Collection");
            collectionButton.setOnClickListener(addToCollection);
        }


        if (watchlistPosition != -1) {
            tvWatchlist.setText("Remove from Watchlist");
            watchlistButton.setOnClickListener(removeFromWatchlist);
        } else {
            tvWatchlist.setText("Add to Watchlist");
            watchlistButton.setOnClickListener(addToWatchlist);
        }
    }
}