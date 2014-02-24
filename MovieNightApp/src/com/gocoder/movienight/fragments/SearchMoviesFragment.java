package com.gocoder.movienight.fragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.gocoder.movienight.client.RottenTomatoesClient;
import com.gocoder.movienight.models.MovieModel;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arunsund on 2/23/14.
 */
public class SearchMoviesFragment extends FeedListFragment {
    private String query;

    public SearchMoviesFragment(String query) {
        this.query = query;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMoreData(0);

    }

    protected void showSearchError() {
        Toast.makeText(getActivity(), "No movies matched your search", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMoreData(int page) {
        new RottenTomatoesClient().searchMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items;
                try {
                    items = body.getJSONArray("movies");
                    if (items.length() > 0) {
                        Log.d("DEBUG", "Movie Search Items=" + items.length());
                        ArrayList<MovieModel> movies = MovieModel.fromJson(items);
                        if (movies.size() > 0) {
                            getAdapter().addAll(movies);
                        } else {
                            showSearchError();
                        }
                    } else {
                        showSearchError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this.query, page);
    }
}