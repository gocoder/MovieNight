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


}
