package com.gocoder.movienight.models;

/**
 * Created by ashishn on 2/10/14.
 */
public class Ratings {
    private String critics_rating;
    private int critics_score;
    private String audience_rating;
    private int audience_score;

    public int getAudience_score() {
        return audience_score;
    }

    public String getAudience_rating() {
        return audience_rating;
    }

    public int getCritics_score() {
        return critics_score;
    }

    public String getCritics_rating() {
        return critics_rating;
    }
}
