package com.gocoder.movienight.models;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ashishn on 2/10/14.
 */
public class MovieModel {
    private long id;
    private String title;
    private String year;
    private ArrayList<String> genres;
    private String mpaa_rating;
    private String runtime;
    private String critics_consensus;
    private ReleaseDate release_dates;
    private Ratings ratings;
    private String synopsis;
    private String thumbnail;
    private String profile;
    private String detailed;
    private String original;
    private ArrayList<Cast> abridged_cast;
    private ArrayList<Cast> abridged_directors;
    private String studio;
    private HashMap<String, String> alternate_ids;

    public Posters getPosters() {
        return posters;
    }

    private Posters posters;
    private Links links;


    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getCritics_consensus() {
        return critics_consensus;
    }

    public ReleaseDate getRelease_dates() {
        return release_dates;
    }

    public Ratings getRatings() {
        return ratings;
    }
    
    public int getUsersStars() {
        return ((ratings.getAudience_score()/20) +1);
    }
    
    public int getCriticsStars() {
        return ((ratings.getCritics_score()/20) +1);
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getProfile() {
        return profile;
    }

    public String getDetailed() {
        return detailed;
    }

    public String getOriginal() {
        return original;
    }

    public ArrayList<Cast> getAbridged_cast() {
        return abridged_cast;
    }

    public String getCast() {
        ArrayList<Cast> cast = abridged_cast;
        StringBuffer result = new StringBuffer();
        String delim = "";
        for (Cast people : cast) {
            result.append(delim);
            result.append(people.getName());
            delim = ",";
        }
        return result.toString();
    }

    public ArrayList<Cast> getAbridged_directors() {
        return abridged_directors;
    }

    public String getStudio() {
        return studio;
    }

    public HashMap<String, String> getAlternate_ids() {
        return alternate_ids;
    }

    public Links getLinks() {
        return links;
    }


    public static MovieModel fromJson(JSONObject jsonObject) {
        return fromJson(jsonObject.toString());
    }


    public static ArrayList<MovieModel> fromJsonList(String jsonObject) {

        Type listType = new TypeToken<ArrayList<MovieModel>>() {
        }.getType();

        ArrayList<MovieModel> result = new Gson().fromJson(jsonObject, listType);

        return result;

    }


    public static MovieModel fromJson(String jsonString) {
        MovieModel result = null;
        try {
            result = new Gson().fromJson(jsonString, MovieModel.class);
        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
        } catch (JsonParseException ex) {
            ex.printStackTrace();
        }
        return result;
    }


    public static ArrayList<MovieModel> fromJson(JSONArray jsonArray) {
        ArrayList<MovieModel> movies = new ArrayList<MovieModel>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemdata;
            try {
                itemdata = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            MovieModel movie = MovieModel.fromJson(itemdata);
            if (movie != null) {
                movies.add(movie);
            }
        }

        return movies;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
