package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

//    static final String apiKey = "6fec35152112a76320cff5806cd8e72c36f70dfb8ea4e9347d8cc0db4774a50b";
    static final String baseURL = "https://api.trakt.tv/";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService apiService = retrofit.create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callButton = findViewById(R.id.callButton);
        final TextView text = findViewById(R.id.textView);

        callButton.setOnClickListener(view -> {
            Call<List<String>> call = apiService.getTrending();

            call.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if (!response.isSuccessful()) {
                        text.setText(response.message());
                        return;
                    }

                    assert response.body() != null;
                    text.setText(response.body().toString());
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    text.setText(t.getMessage());
                }
            });
        });

    }
}