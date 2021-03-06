package com.huskyez.movieapp.views.search;

import android.os.Bundle;
import android.util.Log;
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
import com.huskyez.movieapp.views.show.ShowDetailActivity;

public class SearchShowFragment extends Fragment implements SearchFragment {

    private SearchViewModel searchViewModel;

    private String pastQuery;

    public SearchShowFragment() {

    }

    public static SearchShowFragment newInstance() {
        SearchShowFragment fragment = new SearchShowFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FRAGMENT", "On Create");
        searchViewModel = new ViewModelFactory().create(SearchViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("FRAGMENT", "On Create View");
        return inflater.inflate(R.layout.layout_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.search_recycler_view);

        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getContext(), searchViewModel, "show", ShowDetailActivity.class);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchViewModel.getShowSearchResults().observe(this, searchResults -> adapter.notifyDataSetChanged());
        searchViewModel.getImageCache().observe(this, map -> adapter.notifyDataSetChanged());

        if (pastQuery != null) {
            searchViewModel.search("show", pastQuery);
            pastQuery = null;
        }
    }

    @Override
    public void search(String query) {

        if (searchViewModel != null) {
            searchViewModel.search("show", query);
        } else {
            pastQuery = query;
        }
    }
}
