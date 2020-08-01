package com.example.test.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.adapter.CollectionsRecyclerViewAdapter;
import com.example.test.adapter.ShowsRecyclerViewAdapter;
import com.example.test.model.AbstractTitledMediaObject;
import com.example.test.model.show.AnticipatedShow;
import com.example.test.model.show.RecommendedShow;
import com.example.test.model.show.Show;
import com.example.test.model.show.TrendingShow;
import com.example.test.repo.ImageRepository;
import com.example.test.viewmodel.ShowViewModel;
import com.example.test.views.movie.MovieDetailActivity;
import com.example.test.views.show.ShowDetailActivity;

import java.util.ArrayList;
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
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false);
    }


//    private List<Show> getList(String category, ShowViewModel showViewModel) {
//        List<Show> showList = new ArrayList<>();
//
//        if (category.equals("Recommended")) {
//            showViewModel.updateRecommendedShows("weekly");
//            showList = showViewModel.getRecommendedShows().getValue().stream().map(RecommendedShow::getShow).collect(Collectors.toList());
//            return showList;
//        }
//
//        if (category.equals("Popular")) {
//            showViewModel.updatePopularShows();
//            showList = showViewModel.getPopularShows().getValue();
//            return showList;
//        }
//        if (category.equals("Trending")) {
//            showViewModel.updateTrendingShows();
//            showList = showViewModel.getTrendingShows().getValue().stream().map(TrendingShow::getShow).collect(Collectors.toList());
//            return showList;
//        }
//        if (category.equals("Anticipated")) {
//            showViewModel.updateAnticipatedShows();
//            showList = showViewModel.getAnticipatedShows().getValue().stream().map(AnticipatedShow::getShow).collect(Collectors.toList());
//            return showList;
//        }
//
//        return showList;
//    }
//
//    private void setListener(String category, ShowViewModel showViewModel, ShowsRecyclerViewAdapter adapter) {
//
//        if (category.equals("Recommended")) {
//            showViewModel.getRecommendedShows().observe(this.getViewLifecycleOwner(), shows -> {
//                adapter.setMediaObjects(shows.stream().map(RecommendedShow::getShow).collect(Collectors.toList()));
//                adapter.notifyDataSetChanged();
//            });
//            return;
//        }
//
//        if (category.equals("Popular")) {
//            showViewModel.getPopularShows().observe(this.getViewLifecycleOwner(), shows -> {
//                adapter.setMediaObjects(shows);
//                adapter.notifyDataSetChanged();
//            });
//            return;
//        }
//        if (category.equals("Trending")) {
//            showViewModel.getTrendingShows().observe(this.getViewLifecycleOwner(), shows -> {
//                adapter.setMediaObjects(shows.stream().map(TrendingShow::getShow).collect(Collectors.toList()));
//                adapter.notifyDataSetChanged();
//            });
//            return;
//        }
//        if (category.equals("Anticipated")) {
//            showViewModel.getAnticipatedShows().observe(this.getViewLifecycleOwner(), shows -> {
//                adapter.setMediaObjects(shows.stream().map(AnticipatedShow::getShow).collect(Collectors.toList()));
//                adapter.notifyDataSetChanged();
//            });
//            return;
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView title = view.findViewById(R.id.tv_title);
        TextView edit = view.findViewById(R.id.tv_edit);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CollectionsRecyclerViewAdapter adapter = new CollectionsRecyclerViewAdapter(getContext(), type, type.equals("movie") ? MovieDetailActivity.class : ShowDetailActivity.class);
        recyclerView.setAdapter(adapter);


        updateFunction.run();
        observable.observe(this, list -> {
            if (list != null) {
                adapter.setList(list.stream().map(getFunction).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }
        });

        ImageRepository.getInstance().getImageCache().observe(this, map -> adapter.notifyDataSetChanged());

        title.setText(categoryTitle);
        edit.setOnClickListener(editView -> {
            // TODO: go to a new activity where you can update your lists (watchlist, collection etc...)
        });

    }
}