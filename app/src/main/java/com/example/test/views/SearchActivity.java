package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.SearchResultRepository;
import com.example.test.viewmodel.SearchRecyclerViewAdapter;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
//    private SearchViewModel searchViewModel;

    private SearchResultRepository searchResultRepository;
    private ImageRepository imageRepository;

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

//        searchViewModel = new ViewModelFactory().create(SearchViewModel.class);

        searchResultRepository = new SearchResultRepository();
        imageRepository = new ImageRepository();

        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(this, imageRepository, searchResultRepository);
        recyclerView.setAdapter(adapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchResultRepository.search("movie", s);
                return true;
            }
        });

        searchResultRepository.getSearchResults().observe(this, searchResults -> {
            adapter.notifyDataSetChanged();
        });
        imageRepository.getImageCache().observe(this, map -> adapter.notifyDataSetChanged());
    }


}