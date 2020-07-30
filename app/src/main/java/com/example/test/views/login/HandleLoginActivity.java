package com.example.test.views.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.test.repo.OAuth;
import com.example.test.views.MainActivity;


// This is called when a user logs in for the first time
public class HandleLoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent currentIntent = getIntent();
        Uri data = currentIntent.getData();
        assert data != null;
        String code = data.getQueryParameter("code");

        OAuth oAuth = new OAuth(this.getApplicationContext());
        oAuth.exchangeCodeForAccessToken(code);

        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(intent);
    }
}