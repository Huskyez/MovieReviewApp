package com.huskyez.movieapp.views.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huskyez.movieapp.R;
import com.huskyez.movieapp.adapter.SearchRecyclerViewAdapter;
import com.huskyez.movieapp.viewmodel.SearchViewModel;
import com.huskyez.movieapp.viewmodel.ViewModelFactory;
import com.huskyez.movieapp.views.movie.MovieDetailActivity;

public class SearchMovieFragment extends Fragment implements SearchFragment {

    private SearchViewModel searchViewModel;

    public SearchMovieFragment() {

    }

    public static SearchMovieFragment newInstance() {
        SearchMovieFragment fragment = new SearchMovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchViewModel = new ViewModelFactory().create(SearchViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recycler_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.search_recycler_view);

        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getContext(), searchViewModel, "movie", MovieDetailActivity.class);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchViewModel.getMovieSearchResults().observe(this, searchResults -> adapter.notifyDataSetChanged());
        searchViewModel.getImageCache().observe(this, map -> adapter.notifyDataSetChanged());
    }

    @Override
    public void search(String query) {
        if (searchViewModel != null)
            searchViewModel.search("movie", query);
    }
}
