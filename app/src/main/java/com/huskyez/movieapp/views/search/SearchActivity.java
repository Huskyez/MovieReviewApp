package com.huskyez.movieapp.views.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.huskyez.movieapp.R;
import com.huskyez.movieapp.adapter.SectionsPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.huskyez.movieapp.repo.ImageRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // open the search view
        searchView = findViewById(R.id.search_bar);
        searchView.setIconified(false);

        viewPager2 = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SearchMovieFragment.newInstance());
        fragments.add(SearchShowFragment.newInstance());



        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(this,  fragments);
        viewPager2.setAdapter(sectionsPageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((SearchFragment)fragments.get(tab.getPosition())).search(searchView.getQuery().toString());
                Log.d("TABLAYOUT", "On tab selected called" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("TABLAYOUT", "On tab reselected called");
            }
        });

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            if (position == 0) {
                tab.setText("MOVIES");
            }
            if (position == 1) {
                tab.setText("TV SHOWS");
            }
        })).attach();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                if (tabLayout.getSelectedTabPosition() == 0) {
//                    fragments.get(0)
//                }
                ((SearchFragment)fragments.get(tabLayout.getSelectedTabPosition())).search(searchView.getQuery().toString());
                return true;
            }

        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            ImageRepository.getInstance().clearImageCache();
            finish();
            return true;
        }
        return false;
    }

}