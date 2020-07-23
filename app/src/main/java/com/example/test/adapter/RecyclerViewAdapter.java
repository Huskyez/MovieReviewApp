package com.example.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.model.Image;
import com.example.test.model.Movie;
import com.example.test.model.TrendingMovie;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.views.MovieDetailActivity;

import java.util.List;
import java.util.Map;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ImageRepository imageRepository;
    private MovieRepository movieRepository;

    private MovieViewModel movieViewModel;

    private List<Movie> movies;
    private Map<Integer, Image> imageMap;

    private Context context;

    public RecyclerViewAdapter(Context context, ImageRepository imageRepository, MovieRepository movieRepository) {

        this.imageRepository = imageRepository;
        this.movieRepository = movieRepository;
        this.context = context;
    }


    public RecyclerViewAdapter(Context context, MovieViewModel movieViewModel) {
        this.context = context;
        this.movieViewModel = movieViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<TrendingMovie> movies = movieViewModel.getTrendingMovies().getValue();

        Movie movie = movies.get(position).getMovie();
        Integer tmdb_id = movie.getIds().getTmdb();

        String imageURI;
        Image image = movieViewModel.getImage(tmdb_id);

        if (image != null) {
            imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
        } else {
            movieViewModel.searchImage(tmdb_id, "movie");
            imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
        }

        Glide.with(context).asBitmap().load(imageURI).into(holder.imageView);
        holder.textView.setText(movie.getTitle());
        holder.slug_id = movie.getIds().getSlug();
        holder.tmdb_id = movie.getIds().getTmdb();
    }

    @Override
    public int getItemCount() {
        return movieViewModel.getTrendingMovies().getValue() == null ? 0 : movieViewModel.getTrendingMovies().getValue().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private String slug_id;
        private Integer tmdb_id;
//        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster);
            textView = itemView.findViewById(R.id.title);

            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);
                intent.putExtra("slug_id", slug_id);
                intent.putExtra("tmdb_id", tmdb_id);
                view.getContext().startActivity(intent);
            });

//            layout = itemView.findViewById(R.id.list_item);
        }
    }
}
