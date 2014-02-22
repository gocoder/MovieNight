package com.gocoder.movienight.models;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by ashishn on 2/10/14.
 */
public class Cast {
    private String name;
    private ArrayList<String> characters;

    public String getName() {
        return name;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
