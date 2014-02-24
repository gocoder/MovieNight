package com.gocoder.movienight.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.gocoder.movienight.R;
import com.gocoder.movienight.activities.MovieInfoActivity;
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
        lvFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	Log.d("DEBUG","Launching MovieIfoActivity");
                Intent movieIntent = new Intent(getActivity(), MovieInfoActivity.class);
                if ((Long) view.getTag() == -1L) {
                	Log.d("DEBUG","Unable to get Movie");
                    Toast.makeText(getActivity(), "Unable to get Movie", Toast.LENGTH_SHORT);
                }
                //Log.d("DEBUG","MovieID="+adapter.getItem(position).toString());
                movieIntent.putExtra("movieID", adapter.getItem(position).toString());
                startActivity(movieIntent);
                Log.d("DEBUG","Launched MovieIfoActivity");
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        adapter = new ItemMoviesAdapter(getActivity(), movies);
        lvFeeds.setAdapter(adapter);
    }

    public ItemMoviesAdapter getAdapter() {
        return adapter;
    }

}
