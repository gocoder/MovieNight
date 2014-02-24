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

public class HomeMovieFeedFragment extends FeedListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RottenTomatoesClient().getBoxOfficeMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items;
                try {
                    items = body.getJSONArray("movies");
                    Log.d("DEBUG", "box office items=" + items.length());
                    ArrayList<MovieModel> movies = MovieModel.fromJson(items);
                    getAdapter().addAll(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
