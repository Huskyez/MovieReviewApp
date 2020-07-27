package com.example.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.test.viewmodel.SearchViewModel;
import com.example.test.views.movie.MovieDetailActivity;

import java.util.List;


public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>{


    private SearchViewModel searchViewModel;
    private String type;
    private Class detailClass;
    private Context context;

    public SearchRecyclerViewAdapter(Context context, SearchViewModel searchViewModel, String type, Class detailClass) {
        this.searchViewModel = searchViewModel;
        this.context = context;
        this.type = type;
        this.detailClass = detailClass;
    }

    @NonNull
    @Override
    public SearchRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchitem, parent, false);
        return new SearchRecyclerViewAdapter.ViewHolder(view, detailClass);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewAdapter.ViewHolder holder, int position) {
        List<SearchResult> searchResults = searchViewModel.getSearchResults().getValue();
        SearchResult searchResult = searchResults.get(position);
        Integer tmdb_id;

        String searchResultType = searchResult.getType();

        if (searchResultType.equals("movie")) {
            tmdb_id = searchResult.getMovie().getIds().getTmdb();
        } else {
            tmdb_id = searchResult.getShow().getIds().getTmdb();
        }

//        if (searchResults == null) {
//            return;
//        }

        String imageURI;
        Image image = searchViewModel.getImage(tmdb_id);

        if (image != null) {
            imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
        } else {
            searchViewModel.searchImage(tmdb_id, type);
            imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
        }

        Glide.with(context).asBitmap().load(imageURI).into(holder.ivPoster);

        if (searchResultType.equals("movie")) {
            holder.tvMovieTitle.setText(searchResult.getMovie().getTitle());
        } else {
            holder.tvMovieTitle.setText(searchResult.getShow().getTitle());
        }


        try {
            if (searchResultType.equals("movie")) {
                holder.tvReleaseYear.setText(searchResult.getMovie().getYear().toString());
            } else {
                holder.tvReleaseYear.setText(searchResult.getShow().getYear().toString());
            }
        } catch (NullPointerException e) {
            holder.tvReleaseYear.setText("UNKNOWN");
            Log.e("Error","Movie:" + searchResults.get(position).getMovie());
        }

        if (searchResultType.equals("movie")) {
            holder.slug_id = searchResult.getMovie().getIds().getSlug();
            holder.tmdb_id = searchResult.getMovie().getIds().getTmdb();
        } else {
            holder.slug_id = searchResult.getShow().getIds().getSlug();
            holder.tmdb_id = searchResult.getShow().getIds().getTmdb();
        }
    }

    @Override
    public int getItemCount() {
        return searchViewModel.getSearchResults().getValue() == null ? 0 : searchViewModel.getSearchResults().getValue().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvMovieTitle;
        private TextView tvReleaseYear;

        private String slug_id;
        private Integer tmdb_id;

        private View layout;

        public ViewHolder(@NonNull View itemView, Class detailClass) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.image);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvReleaseYear = itemView.findViewById(R.id.tv_release_year);
            layout = itemView.findViewById(R.id.search_item);

            layout.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), detailClass);
                intent.putExtra("slug_id", slug_id);
                intent.putExtra("tmdb_id", tmdb_id);
                view.getContext().startActivity(intent);
            });
        }
    }
}
