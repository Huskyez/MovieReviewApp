package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.test.R;
import com.example.test.viewmodel.RecyclerViewAdapter;
import com.example.test.viewmodel.SearchViewModel;
import com.example.test.viewmodel.ViewModelFactory;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.search_recycler_view);

        searchViewModel = new ViewModelFactory().create(SearchViewModel.class);

//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
//        recyclerView.setAdapter(adapter);
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

        searchViewModel.getSearchResults().observe(this, searchResults -> {

        });
    }


}