package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.views.movie.MoviePageFragment;
import com.example.test.views.movie.PopularMoviesFragment;
import com.example.test.views.show.ShowPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_movie:
                    fragment = MoviePageFragment.newInstance();
                    break;
                case R.id.nav_shows:
                    fragment = ShowPageFragment.newInstance();
                    break;
                default:
                    fragment = new PopularMoviesFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
            return true;
        });

        ActionMenuItemView searchItem = findViewById(R.id.search_button);
        searchItem.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });
    }
}