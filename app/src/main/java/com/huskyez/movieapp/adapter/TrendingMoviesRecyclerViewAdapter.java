package com.huskyez.movieapp.adapter;

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
import com.huskyez.movieapp.R;
import com.huskyez.movieapp.model.image.Image;
import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.movie.TrendingMovie;
import com.huskyez.movieapp.viewmodel.MovieViewModel;
import com.huskyez.movieapp.views.movie.MovieDetailActivity;

import java.util.List;



public class TrendingMoviesRecyclerViewAdapter extends RecyclerView.Adapter<TrendingMoviesRecyclerViewAdapter.ViewHolder> {

    private MovieViewModel movieViewModel;
    private Context context;

    public TrendingMoviesRecyclerViewAdapter(Context context, MovieViewModel movieViewModel) {
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
        private View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster);
            textView = itemView.findViewById(R.id.title);
            layout = itemView.findViewById(R.id.list_item);

            layout.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);
                intent.putExtra("slug_id", slug_id);
                intent.putExtra("tmdb_id", tmdb_id);
                view.getContext().startActivity(intent);
            });
        }
    }
}
