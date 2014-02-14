package com.gocoder.movienight.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class ItemMovie {
	private String title;
	private int year;
	private String synopsis;
	private String posterUrl;
	private int criticsScore;
	private ArrayList<String> castList;

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public int getCriticsScore() {
		return criticsScore;
	}

	public String getCastList() {
		return TextUtils.join(", ", castList);
	}
	public static ItemMovie fromJson(JSONObject jsonObject) {
        ItemMovie im = new ItemMovie();
        try {
            im.title = jsonObject.getString("title");
            im.year = jsonObject.getInt("year");
            im.synopsis = jsonObject.getString("synopsis");
            im.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            im.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            im.castList = new ArrayList<String>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                im.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return im;
    }
	public static ArrayList<ItemMovie> fromJson(JSONArray jsonArray) {
        ArrayList<ItemMovie> movies = new ArrayList<ItemMovie>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemdata = null;
            try {
            	itemdata = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            ItemMovie movie = ItemMovie.fromJson(itemdata);
            if (movie != null) {
                movies.add(movie);
            }
        }

        return movies;
    }
}