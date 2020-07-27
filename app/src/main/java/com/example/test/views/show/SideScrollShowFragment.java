package com.example.test.views.show;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.adapter.MoviesRecyclerViewAdapter;
import com.example.test.adapter.ShowsRecyclerViewAdapter;
import com.example.test.model.show.AnticipatedShow;
import com.example.test.model.show.Show;
import com.example.test.model.show.TrendingShow;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ShowViewModel;
import com.example.test.viewmodel.ViewModelFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SideScrollShowFragment extends Fragment {


    private String categoryTitle;

    public SideScrollShowFragment() {

    }

//    private void setTitle(String title) {
//        this.categoryTitle = title;
//    }

    public static SideScrollShowFragment newInstance(String title) {
        SideScrollShowFragment sideScrollShowFragment = new SideScrollShowFragment();
        sideScrollShowFragment.categoryTitle = title;
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        sideScrollShowFragment.setArguments(args);
        return sideScrollShowFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        categoryTitle = "UNKNOWN";
//        if (getArguments() != null) {
//            categoryTitle = savedInstanceState.getString("title");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_horizontal_scroll, container, false);
    }

    private List<Show> getList(String category, ShowViewModel showViewModel) {
        List<Show> showList = new ArrayList<>();

        if (category.equals("Popular")) {
            showViewModel.updatePopularShows();
            showList = showViewModel.getPopularShows().getValue();
            return showList;
        }
        if (category.equals("Trending")) {
            showViewModel.updateTrendingShows();
            showList = showViewModel.getTrendingShows().getValue().stream().map(x -> x.getShow()).collect(Collectors.toList());
            return showList;
        }
        if (category.equals("Anticipated")) {
            showViewModel.updateAnticipatedShows();
            showList = showViewModel.getAnticipatedShows().getValue().stream().map(x -> x.getShow()).collect(Collectors.toList());
            return showList;
        }

        return showList;
    }

    private void setListener(String category, ShowViewModel showViewModel, ShowsRecyclerViewAdapter adapter) {
        if (category.equals("Popular")) {
            showViewModel.getPopularShows().observe(this.getViewLifecycleOwner(), shows -> {
                adapter.setMediaObjects(shows);
                adapter.notifyDataSetChanged();
            });
            return;
        }
        if (category.equals("Trending")) {
            showViewModel.getTrendingShows().observe(this.getViewLifecycleOwner(), shows -> {
                adapter.setMediaObjects(shows.stream().map(TrendingShow::getShow).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            });
            return;
        }
        if (category.equals("Anticipated")) {
            showViewModel.getAnticipatedShows().observe(this.getViewLifecycleOwner(), shows -> {
                adapter.setMediaObjects(shows.stream().map(AnticipatedShow::getShow).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            });
            return;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView textView = view.findViewById(R.id.category_title);
        textView.setText(categoryTitle);

        ShowViewModel showViewModel = new ViewModelFactory().create(ShowViewModel.class);
        ShowsRecyclerViewAdapter adapter = new ShowsRecyclerViewAdapter(this.getContext(), getList(categoryTitle, showViewModel));
        setListener(categoryTitle, showViewModel, adapter);
        showViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }

}