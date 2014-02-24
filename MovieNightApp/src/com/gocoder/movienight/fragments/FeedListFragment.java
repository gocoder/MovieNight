package com.gocoder.movienight.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.gocoder.movienight.R;
import com.gocoder.movienight.activities.MovieIntent;
import com.gocoder.movienight.adapters.ItemMoviesAdapter;
import com.gocoder.movienight.helpers.EndlessScrollListener;
import com.gocoder.movienight.models.MovieModel;

import java.util.ArrayList;

public abstract class FeedListFragment extends Fragment {
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
        final ArrayList<MovieModel> movies = new ArrayList<MovieModel>();
        final ListView lvFeeds = (ListView) getActivity().findViewById(R.id.lvFeeds);


        lvFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent movieIntent = new Intent(getActivity(), MovieIntent.class);
                if ((Long) view.getTag() == -1L) {
                    Toast.makeText(getActivity(), "Unable to get Movie", Toast.LENGTH_SHORT).show();
                }
                movieIntent.putExtra("movieID", movies.toString());

                movieIntent.putExtra("position", Integer.toString(position));
                startActivity(movieIntent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);

                //getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        lvFeeds.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (page == 1) {
                    return;
                }
                loadMoreData(page);
            }
        });

        adapter = new ItemMoviesAdapter(getActivity(), movies);
        lvFeeds.setAdapter(adapter);


    }

    public abstract void loadMoreData(int page);


    public ItemMoviesAdapter getAdapter() {
        return adapter;
    }


    public boolean repeatedlist(ArrayList<MovieModel> movies, ItemMoviesAdapter adapter) {
        return !(adapter.getCount() == 0 || movies.get(0).getId() != adapter.getItem(0).getId());
    }
}
