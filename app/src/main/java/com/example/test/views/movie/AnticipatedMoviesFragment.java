package com.example.test.views.movie;

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
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ViewModelFactory;

import java.util.stream.Collectors;

public class AnticipatedMoviesFragment extends Fragment {

    public AnticipatedMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TrendingMoviesFragment.
     */
    public static AnticipatedMoviesFragment newInstance() {
        AnticipatedMoviesFragment fragment = new AnticipatedMoviesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.layout_horizontal_scroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView textView = view.findViewById(R.id.category_title);

        MovieViewModel movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
        MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(this.getContext(), movieViewModel.getAnticipatedMovies().getValue().stream().map(x -> x.getMovie()).collect(Collectors.toList()));

        textView.setText("Anticipated");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        movieViewModel.updateAnticipatedMovies();
        movieViewModel.getAnticipatedMovies().observe(this.getViewLifecycleOwner(), movies -> {
            adapter.setMediaObjects(movieViewModel.getAnticipatedMovies().getValue().stream().map(x -> x.getMovie()).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());
    }
}
