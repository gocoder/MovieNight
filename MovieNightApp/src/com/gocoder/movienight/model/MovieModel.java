package com.gocoder.movienight.model;

import java.util.ArrayList;

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


    public long getId() {
        return id;
    }


}
