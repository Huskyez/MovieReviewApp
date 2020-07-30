package com.example.test.views.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.api.TraktApiConfiguration;
import com.example.test.repo.OAuth;
import com.example.test.views.MainActivity;

import java.time.Instant;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Checks for any tokens
        // if there exists an access token we can procede, if not the user needs to authenticate
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getApplicationContext().getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        if (sharedPreferences.contains("access_token")) {

            long now = Instant.now().getEpochSecond();
            long expires = sharedPreferences.getLong("expires_at", now);

            // if it's less than 3 days until token expires refresh token
            if (expires < 259200) {
                String refreshToken = sharedPreferences.getString("refresh_token", null);
                OAuth oAuth = new OAuth(this.getApplicationContext());
                oAuth.exchangeRefreshTokenForAccessToken(refreshToken);
            }

            // set up the intent so you can not turn back to this activity
            Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(intent);
        }

//        OAuth oAuth = new OAuth();
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(view -> {
            WebView webView = findViewById(R.id.web_view);
            webView.setWebChromeClient(new WebChromeClient());
            webView.clearCache(true);
            webView.clearHistory();
            webView.loadUrl("https://trakt.tv/oauth/authorize?response_type=code&client_id=" + TraktApiConfiguration.CLIENT_ID + "&redirect_uri=" + TraktApiConfiguration.REDIRECT_URI);
            loginButton.setEnabled(false);
        });

    }
}
