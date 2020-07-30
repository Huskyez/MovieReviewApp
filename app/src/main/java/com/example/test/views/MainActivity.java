package com.example.test.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.repo.OAuth;
import com.example.test.views.login.LoginActivity;
import com.example.test.views.movie.MoviePageFragment;
import com.example.test.views.movie.PopularMoviesFragment;
import com.example.test.views.search.SearchActivity;
import com.example.test.views.show.ShowPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private boolean dialogResponse;

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
                case R.id.nav_profile:
                    fragment = ProfileFragment.newInstance();
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

        ActionMenuItemView logoutItem = findViewById(R.id.logout_button);

        logoutItem.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sign Out");
            builder.setMessage("Do you really want to sign out?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                OAuth oAuth = new OAuth(getApplicationContext());
                String access_token = getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null);
                oAuth.revokeToken(access_token);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(intent);
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {
                dialogResponse = false;
                dialogInterface.cancel();
            });
            builder.show();

        });

    }

    private void showAlertDialog() {

    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.logout_button) {
//            OAuth oAuth = new OAuth(getApplicationContext());
//            String access_token = getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE).getString("access_token", null);
//            oAuth.revokeToken(access_token);
//            Intent intent = new Intent(this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}