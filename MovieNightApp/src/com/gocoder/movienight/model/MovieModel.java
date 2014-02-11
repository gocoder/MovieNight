package com.gocoder.movienight.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ashishn on 2/10/14.
 */
public class MovieModel {
    private long id;
    private String title;
    private int year;
    private ArrayList<String> genres;
    private String mpaa_rating;
    private int runtime;
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
    private Links link;


    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public int getRuntime() {
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

    public ArrayList<Cast> getAbridged_directors() {
        return abridged_directors;
    }

    public String getStudio() {
        return studio;
    }

    public HashMap<String, String> getAlternate_ids() {
        return alternate_ids;
    }

    public Links getLink() {
        return link;
    }
}
