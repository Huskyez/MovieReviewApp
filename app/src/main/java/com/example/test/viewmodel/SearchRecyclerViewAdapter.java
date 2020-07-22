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

import com.example.test.model.SearchResult;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.SearchResultRepository;

import java.util.List;


public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>{

    private ImageRepository imageRepository;
    private SearchResultRepository searchResultRepository;
    private Context context;

    public SearchRecyclerViewAdapter(Context context, ImageRepository imageRepository, SearchResultRepository searchResultRepository) {

        this.imageRepository = imageRepository;
        this.searchResultRepository = searchResultRepository;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchitem, parent, false);
        return new SearchRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewAdapter.ViewHolder holder, int position) {
        List<SearchResult> searchResults = searchResultRepository.getSearchResults().getValue();
        Integer tmdb_id = searchResults.get(position).getMovie().getIds().getTmdb();

        String imageURI;
        Image image = imageRepository.getImage(tmdb_id);

        if (image != null) {
            imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
        } else {
            imageRepository.searchImage(tmdb_id, "movie");
            imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
        }

        Glide.with(context).asBitmap().load(imageURI).into(holder.ivPoster);
        holder.tvMovieTitle.setText(searchResults.get(position).getMovie().getTitle());
        holder.tvReleaseYear.setText(searchResults.get(position).getMovie().getYear().toString());
    }

    @Override
    public int getItemCount() {
        return searchResultRepository.getSearchResults().getValue().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvMovieTitle;
        private TextView tvReleaseYear;
//        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.image);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvReleaseYear = itemView.findViewById(R.id.tv_release_year);
//            layout = itemView.findViewById(R.id.list_item);
        }
    }
}
