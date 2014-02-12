package com.gocoder.movienight.client;

import java.io.FileInputStream;
import java.util.Properties;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RottenTomatoesClient {
	private final String API_KEY_KEY = "8m35686f5quhtg9yuvg48sjk";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;

    public RottenTomatoesClient() {
        this.client = new AsyncHttpClient();
    }
    
    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }
    public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/box_office.json");
        RequestParams params = new RequestParams("apikey", API_KEY_KEY);
        client.get(url, params, handler);
    }

}