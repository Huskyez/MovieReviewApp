package com.example.test.viewmodel;

import android.content.Context;
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
import com.example.test.model.TrendingMovie;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    //TODO: Depencency ImageRepo
    private ImageRepository imageRepository;
    private MovieRepository movieRepository;

    private List<String> imageURIs;
    private List<String> titles;
    private Context context;

    public RecyclerViewAdapter(Context context, ImageRepository imageRepository, MovieRepository movieRepository) {
        imageURIs = new ArrayList<>();
        titles = new ArrayList<>();
        this.imageRepository = imageRepository;
        this.movieRepository = movieRepository;
        this.context = context;
    }

    public RecyclerViewAdapter(Context context, List<String> imageURIs, List<String> titles) {
        this.imageURIs = imageURIs;
        this.titles = titles;
        this.context = context;
    }

    public List<String> getImageURIs() {
        return imageURIs;
    }

    public void setImageURIs(List<String> imageURIs) {
        this.imageURIs = imageURIs;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        List<TrendingMovie> movies = movieRepository.getTrendingMovies().getValue();
        Integer tmdb_id = movies.get(position).getMovie().getIds().getTmdb();

        String imageURI;
        Image image = imageRepository.getImage(tmdb_id);


        if (image != null) {
            imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
        } else {
            imageRepository.searchImage(tmdb_id, "movie");
            imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
        }

        Glide.with(context).asBitmap().load(imageURI).into(holder.imageView);
        holder.textView.setText(movies.get(position).getMovie().getTitle());
    }

    @Override
    public int getItemCount() {
        return movieRepository.getTrendingMovies().getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
//        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster);
            textView = itemView.findViewById(R.id.title);
//            layout = itemView.findViewById(R.id.list_item);
        }
    }
}