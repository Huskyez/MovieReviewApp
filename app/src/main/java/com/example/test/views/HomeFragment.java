package com.example.test.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;
import com.example.test.model.WatchlistItem;
import com.example.test.model.movie.CollectionMovie;
import com.example.test.model.movie.RecommendedMovie;
import com.example.test.model.movie.WatchedMovie;
import com.example.test.model.show.CollectionShow;
import com.example.test.model.show.WatchedShow;
import com.example.test.repo.UserListsRepository;

import java.util.Collection;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


     public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        String access_token = getActivity().getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null);
        UserListsRepository userListsRepository = UserListsRepository.getInstance(access_token);

        CollectionsFragment<CollectionMovie> movieCollectionsFragment = CollectionsFragment.newInstance("Movie Collection", "movie", userListsRepository.getMovieCollection(), userListsRepository::searchMovieCollection, CollectionMovie::getMovie);

        CollectionsFragment<CollectionShow> showCollectionsFragment = CollectionsFragment.newInstance("Show Collection", "tv", userListsRepository.getShowCollection(), userListsRepository::searchShowCollection, CollectionShow::getShow);

        CollectionsFragment<WatchlistItem> movieWatchlistFragment = CollectionsFragment.newInstance("Movie Watchlist", "movie", userListsRepository.getWatchlist(), userListsRepository::searchWatchlist, WatchlistItem::getMovie);

        CollectionsFragment<WatchlistItem> showWatchlistFragment = CollectionsFragment.newInstance("Show Watchlist", "tv", userListsRepository.getWatchlist(), userListsRepository::searchWatchlist, WatchlistItem::getShow);

        CollectionsFragment<WatchlistItem> recommendedMovieFragment = CollectionsFragment.newInstance("Recommended Movies", "movie", userListsRepository.getRecommendations(), userListsRepository::searchRecommendations, WatchlistItem::getMovie);

        CollectionsFragment<WatchlistItem> recommendedShowsFragment = CollectionsFragment.newInstance("Recommended Shows", "tv", userListsRepository.getRecommendations(), userListsRepository::searchRecommendations, WatchlistItem::getShow);

        CollectionsFragment<WatchedMovie> watchedMovieFragment = CollectionsFragment.newInstance("Watched Movies", "movie", userListsRepository.getWatchedMovies(), userListsRepository::searchWatchedMovies, WatchedMovie::getMovie);
        CollectionsFragment<WatchedShow> watchedShowFragment = CollectionsFragment.newInstance("Watched Shows", "tv", userListsRepository.getWatchedShows(), userListsRepository::searchWatchedShows, WatchedShow::getShow);

        transaction.add(R.id.container, movieCollectionsFragment);
        transaction.add(R.id.container, showCollectionsFragment);
        transaction.add(R.id.container, movieWatchlistFragment);
        transaction.add(R.id.container, showWatchlistFragment);
        transaction.add(R.id.container, recommendedMovieFragment);
        transaction.add(R.id.container, recommendedShowsFragment);
        transaction.add(R.id.container, watchedMovieFragment);
        transaction.add(R.id.container, watchedShowFragment);

        transaction.commit();
    }
}