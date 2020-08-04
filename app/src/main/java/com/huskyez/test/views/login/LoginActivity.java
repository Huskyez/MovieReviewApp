package com.huskyez.test.views.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huskyez.test.R;
import com.huskyez.test.api.TraktApiConfiguration;
import com.huskyez.test.repo.OAuth;
import com.huskyez.test.views.MainActivity;

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

        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(view -> {
            WebView webView = findViewById(R.id.web_view);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (request.getUrl().getScheme().equals("app")) {
                        Intent intent = new Intent(getApplicationContext(), HandleLoginActivity.class);
                        intent.setData(request.getUrl());
                        startActivity(intent);
                        return true;
                    }

                    return false;
                }
            });
            webView.loadUrl("https://trakt.tv/oauth/authorize?response_type=code&client_id=" + TraktApiConfiguration.CLIENT_ID + "&redirect_uri=" + TraktApiConfiguration.REDIRECT_URI);
            loginButton.setEnabled(false);
        });

    }
}
