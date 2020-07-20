package com.example.test;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> imageURIs;
    private List<String> titles;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        imageURIs = new ArrayList<>();
        titles = new ArrayList<>();
        this.context = context;
    }

    public List<String> getImageURIs() {
        return imageURIs;
    }

    public void setImageURIs(List<String> imageURIs) {
        this.imageURIs.clear();
        this.imageURIs = imageURIs;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles.clear();
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
        Glide.with(context).asBitmap().load(imageURIs.get(position)).into(holder.imageView);
        holder.textView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return imageURIs.size();
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
