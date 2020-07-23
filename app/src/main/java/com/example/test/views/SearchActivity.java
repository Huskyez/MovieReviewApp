package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.SearchResultRepository;
import com.example.test.adapter.SearchRecyclerViewAdapter;
import com.example.test.viewmodel.SearchViewModel;
import com.example.test.viewmodel.ViewModelFactory;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private SearchViewModel searchViewModel;


    @Override
    public int getMaxNumPictureInPictureActions() {
        return super.getMaxNumPictureInPictureActions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.search_recycler_view);

        searchViewModel = new ViewModelFactory().create(SearchViewModel.class);


        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(this, searchViewModel);
        recyclerView.setAdapter(adapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchViewModel.search("movie", s);
                return true;
            }

        });

        searchViewModel.getSearchResults().observe(this, searchResults -> adapter.notifyDataSetChanged());
        searchViewModel.getImageCache().observe(this, map -> adapter.notifyDataSetChanged());
    }


}