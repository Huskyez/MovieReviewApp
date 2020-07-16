package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

//    static final String apiKey = "6fec35152112a76320cff5806cd8e72c36f70dfb8ea4e9347d8cc0db4774a50b";

    private TextView text;

    ApiService apiService = ApiServiceFactory.getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callButton = findViewById(R.id.callButton);
        text = findViewById(R.id.textView);

        callButton.setOnClickListener(view -> getPopular());

    }

    public void getPopular() {
        Call<List<Movie>> call = apiService.getPopular();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (!response.isSuccessful()) {
                    text.setText(response.message());
                    return;
                }

                assert response.body() != null;
                StringBuilder movies = new StringBuilder();
                for (Movie movie : response.body()) {
                    movies.append(movie).append("\n");
                }
                text.setText(movies.toString());

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                text.setText(t.getMessage());
            }
        });

    }

    public void getTrending() {
        Call<String> call = apiService.getTrending();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    text.setText(response.message());
                    return;
                }

                assert response.body() != null;
                text.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                text.setText(t.getMessage());
            }
        });
    }
}