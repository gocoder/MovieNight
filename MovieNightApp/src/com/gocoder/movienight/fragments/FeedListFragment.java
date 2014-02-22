package com.gocoder.movienight.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.adapters.ItemMoviesAdapter;
import com.gocoder.movienight.models.MovieModel;

import java.util.ArrayList;

public class FeedListFragment extends Fragment {
    ItemMoviesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        ArrayList<MovieModel> movies = new ArrayList<MovieModel>();
        final ListView lvFeeds = (ListView) getActivity().findViewById(R.id.lvFeeds);
        adapter = new ItemMoviesAdapter(getActivity(), movies);
        lvFeeds.setAdapter(adapter);
    }

    public ItemMoviesAdapter getAdapter() {
        return adapter;
    }

}
