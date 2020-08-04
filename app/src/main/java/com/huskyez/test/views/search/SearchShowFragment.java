package com.huskyez.test.views.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huskyez.test.R;
import com.huskyez.test.adapter.SearchRecyclerViewAdapter;
import com.huskyez.test.viewmodel.SearchViewModel;
import com.huskyez.test.viewmodel.ViewModelFactory;
import com.huskyez.test.views.show.ShowDetailActivity;

public class SearchShowFragment extends Fragment implements SearchFragment {

    private SearchViewModel searchViewModel;

    public SearchShowFragment() {

    }

    public static SearchShowFragment newInstance() {
        SearchShowFragment fragment = new SearchShowFragment();
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

        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getContext(), searchViewModel, "show", ShowDetailActivity.class);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchViewModel.getShowSearchResults().observe(this, searchResults -> adapter.notifyDataSetChanged());
        searchViewModel.getImageCache().observe(this, map -> adapter.notifyDataSetChanged());
    }

    @Override
    public void search(String query) {
        if (searchViewModel != null)
            searchViewModel.search("show", query);
    }
}
