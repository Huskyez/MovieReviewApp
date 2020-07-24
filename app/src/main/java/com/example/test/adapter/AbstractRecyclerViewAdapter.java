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
import com.example.test.model.AbstractMediaObject;
import com.example.test.model.AbstractTitledMediaObject;
import com.example.test.model.Image;
import com.example.test.model.movie.Movie;
import com.example.test.model.show.Show;
import com.example.test.repo.ImageRepository;
import com.example.test.views.MovieDetailActivity;

import java.util.List;
import java.util.Map;

public abstract class AbstractRecyclerViewAdapter<T extends AbstractTitledMediaObject> extends RecyclerView.Adapter<AbstractRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<T> mediaObjects;

    public AbstractRecyclerViewAdapter(Context context, List<T> mediaObjects){
        this.context = context;
        this.mediaObjects = mediaObjects;
    }

    public void setMediaObjects(List<T> mediaObjects) {
        this.mediaObjects = mediaObjects;
    }

    @NonNull
    @Override
    public AbstractRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new AbstractRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractRecyclerViewAdapter.ViewHolder holder, int position) {

        T mediaObject = mediaObjects.get(position);
        Integer tmdb_id = mediaObject.getIds().getTmdb();
        String imageURI;

        ImageRepository imageRepository = ImageRepository.getInstance();
        Image image = imageRepository.getImage(tmdb_id);

        if (image != null) {
            imageURI = "https://image.tmdb.org/t/p/w500" + image.getPath();
        } else {
            imageRepository.searchImage(tmdb_id, "movie");
            imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
        }

        Glide.with(context).asBitmap().load(imageURI).into(holder.imageView);
        holder.textView.setText(mediaObject.getTitle());
        holder.slug_id = mediaObject.getIds().getSlug();
        holder.tmdb_id = mediaObject.getIds().getTmdb();
    }

    @Override
    public int getItemCount() {
        return mediaObjects == null ? 0 : mediaObjects.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private String  slug_id;
        private Integer tmdb_id;
        private View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.poster);
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
