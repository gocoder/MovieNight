package com.gocoder.movienight.fragments;

import android.os.Bundle;
import android.util.Log;
import com.gocoder.movienight.client.RottenTomatoesClient;
import com.gocoder.movienight.models.MovieModel;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InTheatreMovieFeedFragment extends FeedListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMoreData(1);
    }

    @Override
    public void loadMoreData(int page) {
        if (page <= 16) {
            return;
        }
        new RottenTomatoesClient().getInTheatreMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items;
                try {
                    items = body.getJSONArray("movies");
                    Log.d("DEBUG", "In Theatre items=" + items.length());
                    ArrayList<MovieModel> movies = MovieModel.fromJson(items);
                    getAdapter().addAll(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, page);

    }
}
