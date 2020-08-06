package com.huskyez.movieapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huskyez.movieapp.R;
import com.huskyez.movieapp.adapter.CollectionsRecyclerViewAdapter;
import com.huskyez.movieapp.model.common.AbstractTitledMediaObject;
import com.huskyez.movieapp.repo.ImageRepository;
import com.huskyez.movieapp.views.movie.MovieDetailActivity;
import com.huskyez.movieapp.views.show.ShowDetailActivity;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionsFragment<T> extends Fragment {

    private String categoryTitle;
    private String type;
    private Runnable updateFunction;
    private Function<T, AbstractTitledMediaObject> getFunction;
    private LiveData<List<T>> observable;
//    private List<AbstractTitledMediaObject> dataList;

    public CollectionsFragment() {
        // Required empty public constructor
    }


    public static<T> CollectionsFragment newInstance(String categoryTitle, String type, LiveData<List<T>> observable, Runnable updateFunction, Function<T, AbstractTitledMediaObject> getFunction) {
        CollectionsFragment<T> fragment = new CollectionsFragment();
        fragment.categoryTitle = categoryTitle;
        fragment.type = type;
        fragment.observable = observable;
        fragment.updateFunction = updateFunction;
        fragment.getFunction = getFunction;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView title = view.findViewById(R.id.tv_title);
//        TextView edit = view.findViewById(R.id.tv_edit);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        CollectionsRecyclerViewAdapter adapter = new CollectionsRecyclerViewAdapter(getContext(), type, type.equals("movie") ? MovieDetailActivity.class : ShowDetailActivity.class);
        recyclerView.setAdapter(adapter);


        updateFunction.run();
        observable.observe(this, list -> {
            if (list != null) {
                adapter.setList(list.stream().map(getFunction).filter(Objects::nonNull).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }
        });

        ImageRepository.getInstance().getImageCache().observe(this, map -> adapter.notifyDataSetChanged());

        title.setText(categoryTitle);
//        edit.setOnClickListener(editView -> {
//            // TODO: go to a new activity where you can update your lists (watchlist, collection etc...)
//        });

    }
}