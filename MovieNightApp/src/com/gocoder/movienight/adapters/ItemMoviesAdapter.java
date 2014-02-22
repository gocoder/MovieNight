package com.gocoder.movienight.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.models.MovieModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ItemMoviesAdapter extends ArrayAdapter<MovieModel> {
    public ItemMoviesAdapter(Context context, ArrayList<MovieModel> aMovies) {
        super(context, 0, aMovies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_feed_movie, null);
        }
        MovieModel movie = getItem(position);
        assert view != null;
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvCriticsScore = (TextView) view.findViewById(R.id.tvCriticsScore);
        TextView tvCast = (TextView) view.findViewById(R.id.tvCast);
        ImageView ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImage);
        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText("Score: " + movie.getRatings().getCritics_score() + "%");
        tvCast.setText(movie.getCast());
        view.setTag(movie.getId());
        //System.out.println("imageurl=" + movie);
        ImageLoader.getInstance().displayImage(movie.getPosters().getProfile(), ivPosterImage);
        // Return the completed view to render on screen
        return view;

		
		/*
        // Get the data item for this position
		ItemMovie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
        	LayoutInflater inflater = LayoutInflater.from(getContext());
        	convertView = inflater.inflate(R.layout.item_feed_movie, null);
        }
        // Lookup views within item layout
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvCriticsScore = (TextView) convertView.findViewById(R.id.tvCriticsScore);
        TextView tvCast = (TextView) convertView.findViewById(R.id.tvCast);
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText("Score: " + movie.getCriticsScore() + "%");
        tvCast.setText(movie.getCastList());
        ImageLoader.getInstance().displayImage(movie.getPosterUrl(), ivPosterImage);
        // Return the completed view to render on screen
        return convertView;
        */

    }

}
