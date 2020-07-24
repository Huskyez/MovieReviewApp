package com.example.test.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.adapter.PopularMoviesRecyclerViewAdapter;
import com.example.test.viewmodel.MovieViewModel;
import com.example.test.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularMoviesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;

    public PopularMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PopularMoviesFragment.
     */
    public static PopularMoviesFragment newInstance() {
        PopularMoviesFragment fragment = new PopularMoviesFragment();
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


        TextView textView = view.findViewById(R.id.category_title);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        MovieViewModel movieViewModel = new ViewModelFactory().create(MovieViewModel.class);
        PopularMoviesRecyclerViewAdapter adapter = new PopularMoviesRecyclerViewAdapter(this.getContext(), movieViewModel.getPopularMovies().getValue());

        textView.setText(getString(R.string.popular));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        movieViewModel.updatePopularMovies();
        movieViewModel.getPopularMovies().observe(this.getViewLifecycleOwner(), movies -> {
            adapter.setMediaObjects(movieViewModel.getPopularMovies().getValue());
            adapter.notifyDataSetChanged();
        });
        movieViewModel.getImageCache().observe(this.getViewLifecycleOwner(), map -> adapter.notifyDataSetChanged());
    }
}