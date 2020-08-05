package com.huskyez.test.views.show;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huskyez.test.R;
import com.huskyez.test.model.common.WatchlistItem;
import com.huskyez.test.model.image.Image;

import com.huskyez.test.model.movie.HistoryMovie;
import com.huskyez.test.model.movie.Movie;
import com.huskyez.test.model.movie.RatedMovie;
import com.huskyez.test.model.movie.WatchedMovie;
import com.huskyez.test.model.show.CollectionShow;
import com.huskyez.test.model.show.RatedShow;
import com.huskyez.test.model.show.Show;
import com.huskyez.test.model.show.ShowDetails;
import com.huskyez.test.model.show.WatchedShow;
import com.huskyez.test.model.sync.CollectionPostBody;
import com.huskyez.test.model.sync.HistoryPostBody;
import com.huskyez.test.model.sync.PostMediaObject;
import com.huskyez.test.model.sync.RatingPostBody;
import com.huskyez.test.model.sync.WatchlistPostBody;
import com.huskyez.test.repo.UserListsRepository;
import com.huskyez.test.viewmodel.ShowDetailsViewModel;
import com.huskyez.test.viewmodel.ViewModelFactory;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class ShowDetailActivity extends AppCompatActivity {

    private ShowDetailsViewModel showDetailsViewModel;
    private ShowDetails showDetails;

    private String slug_id;
    private Integer tmdb_id;

    private ImageView imageView;

    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvSummary;
    private TextView tvStatus;
    private TextView tvEpisodes;
    private TextView tvRating;

    private RatingBar ratingBar;

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

    private UserListsRepository userListsRepository;
    private List<CollectionShow> collectionShows;
    private List<WatchlistItem> watchlist;
    private List<WatchedShow> history;
    private int collectionPosition;
    private int watchlistPosition;
    private int historyPosition;

    private View.OnClickListener addToCollection;
    private View.OnClickListener removeFromCollection;
    private View.OnClickListener addToWatchlist;
    private View.OnClickListener removeFromWatchlist;
    private View.OnClickListener addToHistory;
    private View.OnClickListener removeFromHistory;


    TimeZone timeZone = TimeZone.getDefault();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

    private void closeFabs() {
        collectionButton.startAnimation(fabClose);
        watchlistButton.startAnimation(fabClose);
        watchedButton.startAnimation(fabClose);
        tvCollection.setVisibility(View.INVISIBLE);
        tvWatchlist.setVisibility(View.INVISIBLE);
        tvWatched.setVisibility(View.INVISIBLE);
        isOpen = false;
    }

    private void openFabs() {
        collectionButton.startAnimation(fabOpen);
        watchlistButton.startAnimation(fabOpen);
        watchedButton.startAnimation(fabOpen);

        tvCollection.setVisibility(View.VISIBLE);
        tvWatchlist.setVisibility(View.VISIBLE);
        tvWatched.setVisibility(View.VISIBLE);
        isOpen = true;
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
        imageView = findViewById(R.id.poster);
        tvTitle = findViewById(R.id.tv_title);
        tvYear = findViewById(R.id.tv_year);
        tvSummary = findViewById(R.id.tv_summary);
        tvStatus = findViewById(R.id.tv_status);
        tvEpisodes = findViewById(R.id.tv_nr_episodes);
        tvRating = findViewById(R.id.tv_rating);

        LinearLayout genreLayout = findViewById(R.id.genre_container);


        showDetailsViewModel = new ViewModelFactory().create(ShowDetailsViewModel.class);

        showDetailsViewModel.searchShowDetails(slug_id);
        showDetailsViewModel.getShowDetails().observe(this, showDetails -> {
            if (showDetails == null) {
                return;
            }
            this.showDetails = showDetails;
//            String dateFormat = "yyyy - mm - dd";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

            tvTitle.setText(showDetails.getTitle());
            tvYear.setText(showDetails.getFirstAired().substring(0, 10));
            tvSummary.setText(showDetails.getOverview());
            tvStatus.setText("Status: " + showDetails.getStatus());
            tvEpisodes.setText("Number of episodes: " + showDetails.getAired_episodes().toString());
            tvRating.setText("Rating: " + showDetails.getRating().toString());

            for (String genre : showDetails.getGenres()) {
                TextView tvGenre = new TextView(this);
                tvGenre.setBackground(getDrawable(R.drawable.text_genre_bg));
                tvGenre.setTextColor(getColor(R.color.lightGray));
                tvGenre.setText(genre);
                genreLayout.addView(tvGenre);
            }

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


    private void setRatingBar() {

        ratingBar = findViewById(R.id.rating_bar);

        userListsRepository.searchRatings();

        userListsRepository.getRatings().observe(this, ratings -> {
            Integer rating = 0;
            int position = ratings.stream().filter(x -> x.getType().equals("show")).map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
            if (position != -1) {
                rating = ratings.get(position).getRating();
            }
            ratingBar.setRating(rating / 2.0f);
        });

        ratingBar.setOnRatingBarChangeListener((ratinpgBar, v, b) -> {
            // check if user changed the rating bar
            if (!b) {
                return;
            }

            int rating = (int) (2.0f * v);

            String toShow = "Your rating: ";
            switch (rating) {
                case 0:
                    toShow += "No rating!";
                    break;
                case 1:
                    toShow += "Weak Sauce :(";
                    break;
                case 2:
                    toShow += "Terrible";
                    break;
                case 3:
                    toShow += "Bad";
                    break;
                case 4:
                    toShow += "Poor";
                    break;
                case 5:
                    toShow += "Meh";
                    break;
                case 6:
                    toShow += "Fair";
                    break;
                case 7:
                    toShow += "Good";
                    break;
                case 8:
                    toShow += "Great";
                    break;
                case 9:
                    toShow += "Superb";
                    break;
                case 10:
                    toShow += "Totally Ninja!";
                    break;
            }
            Toast.makeText(this, toShow, Toast.LENGTH_SHORT).show();

            RatedShow ratedShow = new RatedShow(showDetails.getIds(), showDetails.getTitle(), showDetails.getYear(), rating);
            if (rating == 0) {
                userListsRepository.removeRating(new RatingPostBody(null, new ArrayList<>(Collections.singletonList(ratedShow))));
            } else {
                userListsRepository.addRating(new RatingPostBody(null, new ArrayList<>(Collections.singletonList(ratedShow))));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        tmdb_id = getIntent().getIntExtra("tmdb_id", 0);
        slug_id = getIntent().getStringExtra("slug_id");

        userListsRepository = UserListsRepository.getInstance(getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null));
        dateFormat.setTimeZone(timeZone);

        setFloatingActionButtons();
        setDetailPage();
        setRatingBar();

        collectionShows = userListsRepository.getShowCollection().getValue();
        watchlist = userListsRepository.getWatchlist().getValue().stream().filter(x -> x.getType().equals("show")).collect(Collectors.toList());
        history = userListsRepository.getWatchedShows().getValue();

        collectionPosition = collectionShows.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        watchlistPosition = watchlist.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        historyPosition = history.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);

        userListsRepository.getResponse().observe(this, response -> {
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        });

        userListsRepository.getShowCollection().observe(this, collection -> {
            collectionShows = collection;
            collectionPosition = collectionShows.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        });

        userListsRepository.getWatchlist().observe(this, watchlist -> {
            this.watchlist = watchlist.stream().filter(x -> x.getType().equals("show")).collect(Collectors.toList());
            watchlistPosition = this.watchlist.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        });

        userListsRepository.getWatchedShows().observe(this, history -> {
            this.history = history;
            historyPosition = this.history.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        });


        addToCollection = view -> {
            String now = dateFormat.format(new Date());
            PostMediaObject toAdd = new PostMediaObject(now, showDetails.getTitle(), showDetails.getYear(), showDetails.getIds());
            userListsRepository.addToCollection(new CollectionPostBody(null, new ArrayList<>(Collections.singletonList(toAdd))));
            collectionShows.add(new CollectionShow(now, now, new Show(toAdd.ids, toAdd.title, toAdd.year), null));
            tvCollection.setText("Remove from Collection");
            collectionButton.setOnClickListener(removeFromCollection);
            closeFabs();
        };

        removeFromCollection = view -> {
            CollectionShow collectionShow = collectionShows.get(collectionPosition);
            PostMediaObject toAdd = new PostMediaObject(null, collectionShow.getShow().getTitle(), collectionShow.getShow().getYear(), collectionShow.getShow().getIds());
            userListsRepository.removeFromCollection(new CollectionPostBody(null, new ArrayList<>(Collections.singletonList(toAdd))));
            tvCollection.setText("Add to Collection");
            collectionShows.remove(collectionPosition);
            collectionButton.setOnClickListener(addToCollection);
            closeFabs();
        };

        addToWatchlist = view -> {
            Show toAdd = new Show(showDetails.getIds(), showDetails.getTitle(), showDetails.getYear());
            userListsRepository.addToWatchlist(new WatchlistPostBody(null, new ArrayList<>( Collections.singletonList(toAdd))));
            watchlist.add(new WatchlistItem(null, null, "show", null, toAdd));
            tvWatchlist.setText("Remove from Watchlist");
            watchlistButton.setOnClickListener(removeFromWatchlist);
            closeFabs();
        };

        removeFromWatchlist = view -> {
            userListsRepository.removeFromWatchlist(new WatchlistPostBody(null, new ArrayList<>(Collections.singletonList(watchlist.get(watchlistPosition).getShow()))));
            tvWatchlist.setText("Add to Watchlist");
            watchlist.remove(watchlistPosition);
            watchlistButton.setOnClickListener(addToWatchlist);
            closeFabs();
        };

        addToHistory = view -> {
            Show show = new Show(showDetails.getIds(), showDetails.getTitle(), showDetails.getYear());
            WatchedShow toAdd = new WatchedShow(null, null, null, null, show, null);
            history.add(toAdd);
            userListsRepository.addToHistory(new HistoryPostBody(null, new ArrayList<>(Collections.singletonList(show)), null, null));
            tvWatched.setText("Remove from History");
            watchedButton.setOnClickListener(removeFromHistory);
            closeFabs();
        };

        removeFromHistory = view -> {
            WatchedShow watchedShow = history.get(historyPosition);
            Show toRemove = new Show(watchedShow.getShow().getIds(), watchedShow.getShow().getTitle(), watchedShow.getShow().getYear());
            userListsRepository.removeFromHistory(new HistoryPostBody(null, new ArrayList<>(Collections.singletonList(toRemove)), null, null));
            history.remove(historyPosition);
            tvWatched.setText("Add to History");
            watchedButton.setOnClickListener(addToHistory);
            closeFabs();
        };


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

        if (historyPosition != -1) {
            tvWatched.setText("Remove from History");
            watchedButton.setOnClickListener(removeFromHistory);
        } else {
            tvWatched.setText("Add to History");
            watchedButton.setOnClickListener(addToHistory);
        }

    }
}