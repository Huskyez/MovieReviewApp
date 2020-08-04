package com.huskyez.test.views.show;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huskyez.test.R;
import com.huskyez.test.model.common.WatchlistItem;
import com.huskyez.test.model.image.Image;

import com.huskyez.test.model.show.CollectionShow;
import com.huskyez.test.model.show.Show;
import com.huskyez.test.model.show.ShowDetails;
import com.huskyez.test.model.sync.CollectionPostBody;
import com.huskyez.test.model.sync.PostMediaObject;
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
import android.widget.TextView;

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

    private FloatingActionButton addButton;
    private FloatingActionButton collectionButton;
    private FloatingActionButton watchlistButton;

    private TextView tvCollection;
    private TextView tvWatchlist;

    private Animation fabClose;
    private Animation fabOpen;

    private boolean isOpen;

    private UserListsRepository userListsRepository;
    private List<CollectionShow> collectionShows;
    private List<WatchlistItem> watchlist;
    private int collectionPosition;
    private int watchlistPosition;

    private View.OnClickListener addToCollection;
    private View.OnClickListener removeFromCollection;
    private View.OnClickListener addToWatchlist;
    private View.OnClickListener removeFromWatchlist;


    TimeZone timeZone = TimeZone.getDefault();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

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


        tvCollection = findViewById(R.id.tv_collection);
        tvWatchlist = findViewById(R.id.tv_watchlist);


        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);

        collectionButton.setVisibility(View.INVISIBLE);
        watchlistButton.setVisibility(View.INVISIBLE);

        tvCollection.setVisibility(View.INVISIBLE);
        tvWatchlist.setVisibility(View.INVISIBLE);


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        tmdb_id = getIntent().getIntExtra("tmdb_id", 0);
        slug_id = getIntent().getStringExtra("slug_id");

        setFloatingActionButtons();
        setDetailPage();


        dateFormat.setTimeZone(timeZone);

        userListsRepository = UserListsRepository.getInstance(getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null));

        collectionShows = userListsRepository.getShowCollection().getValue();
        watchlist = userListsRepository.getWatchlist().getValue().stream().filter(x -> x.getType().equals("show")).collect(Collectors.toList());

        collectionPosition = collectionShows.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        watchlistPosition = watchlist.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);

        userListsRepository.getShowCollection().observe(this, collection -> {
            collectionShows = collection;
            collectionPosition = collectionShows.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
        });

        userListsRepository.getWatchlist().observe(this, watchlist -> {
            this.watchlist = watchlist.stream().filter(x -> x.getType().equals("show")).collect(Collectors.toList());
            watchlistPosition = this.watchlist.stream().map(x -> x.getShow().getIds().getSlug()).collect(Collectors.toList()).indexOf(slug_id);
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