package com.example.test.views;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.btnLogin);

//        OAuth oAuth = new OAuth();

        loginButton.setOnClickListener(view -> {
            WebView webView = findViewById(R.id.web_view);
            webView.clearCache(true);
            webView.clearHistory();
            webView.setWebChromeClient(new WebChromeClient());
            webView.loadUrl("https://trakt.tv/oauth/authorize?response_type=code&client_id=ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086&redirect_uri=app://moviereviewapp");
            loginButton.setEnabled(false);
        });

    }
}
