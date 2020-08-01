package com.example.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.model.AbstractTitledMediaObject;
import com.example.test.model.Image;
import com.example.test.repo.ImageRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsRecyclerViewAdapter extends RecyclerView.Adapter<CollectionsRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private String type;
    private List<AbstractTitledMediaObject> list;
//    private Function<Object, AbstractTitledMediaObject> getFunction;
    private Class detailClass;

    public CollectionsRecyclerViewAdapter(Context context, String type, Class detailClass) {
        this.context = context;
        this.type = type;
        this.detailClass = detailClass;
    }

    public void setList(List<AbstractTitledMediaObject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view, detailClass);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AbstractTitledMediaObject mediaObject = list.get(position);

        Integer tmdb_id = mediaObject.getIds().getTmdb();
        String imageURI;

        ImageRepository imageRepository = ImageRepository.getInstance();
        Image image = imageRepository.getImage(tmdb_id);

        if (image != null) {
            imageURI = "https://image.tmdb.org/t/p/original" + image.getPath();
        } else {
            imageRepository.searchImage(tmdb_id, type);
            imageURI = "https://i.pinimg.com/originals/10/b2/f6/10b2f6d95195994fca386842dae53bb2.png";
        }

        Glide.with(context).asBitmap().load(imageURI).into(holder.imageView);
        holder.textView.setText(mediaObject.getTitle());
        holder.slug_id = mediaObject.getIds().getSlug();
        holder.tmdb_id = mediaObject.getIds().getTmdb();

     }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private String  slug_id;
        private Integer tmdb_id;
        private View layout;

        public ViewHolder(@NonNull View itemView, Class detailClass) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.poster);
            layout = itemView.findViewById(R.id.list_item);

            layout.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), detailClass);
                intent.putExtra("slug_id", slug_id);
                intent.putExtra("tmdb_id", tmdb_id);
                view.getContext().startActivity(intent);
            });
        }
    }
}
