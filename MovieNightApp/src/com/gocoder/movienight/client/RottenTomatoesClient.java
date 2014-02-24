package com.gocoder.movienight.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RottenTomatoesClient {
    private final String API_KEY_KEY = "8m35686f5quhtg9yuvg48sjk";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;

    JsonParser parser = new JsonParser();

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

    public void getInTheatreMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/in_theaters.json");
        RequestParams params = new RequestParams("apikey", API_KEY_KEY);
        client.get(url, params, handler);
    }

    public void searchMovies(JsonHttpResponseHandler handler, String query) {
        String url = getApiUrl("movies.json");
        RequestParams params = new RequestParams("apikey", API_KEY_KEY);
        params.put("q", query);
        client.get(url, params, handler);
    }


    public void getTrailer(String url) throws IOException {
        final RequestParams params = new RequestParams("apikey", API_KEY_KEY);


        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                JsonObject _json = parser.parse(response).getAsJsonObject();
                String trailer_url = _json.get("links").getAsJsonObject().get("alternate").toString();
                trailer_url = trailer_url.replaceAll("\\s+", "");
                trailer_url = trailer_url.replace("\"", "");
                client.get(trailer_url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            playTrailer(s);
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void playTrailer(String xmlData) throws ParserConfigurationException, IOException, SAXException {

        //System.out.println("xmldata=" + xmlData);


    }

}